package com.grass.test;

import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Modifier;

import com.grass.bean.ActivityDataStore;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

/**
 * Created by grass on 15/12/20.
 */
public class testjavaPoet {

    private static final String ACTIVITY_STORE = "ActivitysStore";

    private static final String CODE_TEMPLETE = "$T.getInstance().add($S,$S,$S)";

    public static void main(String[] args) {
        CodeBlock codeBlock = CodeBlock.builder().addStatement(CODE_TEMPLETE, ActivityDataStore.class,"honey", "des",
                "com"
                + ".baidu.com"
                + ".Activity")
                .build();
        TypeSpec activityStore = TypeSpec.classBuilder(ACTIVITY_STORE)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL).addStaticBlock(codeBlock)
                .build();
        JavaFile javaFile = JavaFile.builder("com.grass", activityStore).build();
        try {
            javaFile.writeTo(new File("/Users/grass/Documents/studio/github/GrassSampleAnnotationProcessor/src/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
