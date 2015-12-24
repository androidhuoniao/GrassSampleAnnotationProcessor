package com.grass.processor;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

/**
 * Created by grass on 15/12/20.
 */
public class ActivityCaseGroupedClasses {

    private static final String ACTIVITY_STORE = "ActivityCaseDataBuilder";
    private static ClassName ACTIVITYCASEDATASTORE = ClassName.get("com.grass.data", "ActivityCaseDataStore");
    private static final String CODE_TEMPLETE = "$T.getInstance().add($S,$S,$S);\n";
    private static final String PACKAGE_NAME="com.grass.data";
    private Map<String, ActivityCaseAnnotionedClass> map = new LinkedHashMap<>();

    public void add(ActivityCaseAnnotionedClass item) {
        if (item == null) {
            return;
        }

        ActivityCaseAnnotionedClass exsitingClass = map.get(item.getClassName());
        if (exsitingClass != null) {
            map.put(item.getClassName(), item);
        }
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void generateCode() {
        Set<Map.Entry<String, ActivityCaseAnnotionedClass>> set = map.entrySet();
        Iterator<Map.Entry<String, ActivityCaseAnnotionedClass>> iterator = set.iterator();
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry<String, ActivityCaseAnnotionedClass> next = iterator.next();
            ActivityCaseAnnotionedClass value = next.getValue();
            sb.append(String.format(CODE_TEMPLETE, ACTIVITYCASEDATASTORE, value.getName(), value.getDes(), value
                    .getClassName()));
        }
        CodeBlock block = CodeBlock.builder().add(sb.toString()).build();
        generateCode(block);
    }

    private void generateCode(CodeBlock block) {
        TypeSpec activityStore = TypeSpec.classBuilder(ACTIVITY_STORE)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL).addStaticBlock(block)
                .build();
        JavaFile javaFile = JavaFile.builder("com.grass.data", activityStore).build();
        try {
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
