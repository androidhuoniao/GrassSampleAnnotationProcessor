package com.grass.processor;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import com.google.auto.service.AutoService;
import com.grass.annotation.ActivityCase;
import com.grass.annotation.FragmentCase;

/**
 * Created by grass on 15/12/20.
 */
@AutoService(Processor.class)
public class MyProcessortest extends AbstractProcessor {

    private Types mTypeUtils;
    private Elements mElementUtils;
    private Filer mFiler;
    private Messager mMessager;
    private ActivityCaseGroupedClasses mActivityCaseCollections = new ActivityCaseGroupedClasses();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();

    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(ActivityCase.class.getCanonicalName());
        annotations.add(FragmentCase.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(ActivityCase.class);
        for (Element element : set) {
            if (element.getKind() == ElementKind.CLASS) {
                ActivityCaseAnnotionedClass aClass = new ActivityCaseAnnotionedClass((TypeElement) element);
                if (!isVaildClass(aClass)) {
                    return true;
                }
                mActivityCaseCollections.add(aClass);
            } else {
                error(element, "only Classes can be annotated with @%s", ActivityCase.class.getSimpleName());
                return true;
            }

        }

        if (!mActivityCaseCollections.isEmpty()) {
            mActivityCaseCollections.generateCode();
        }
        return false;
    }

    private void error(Element e, String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }

    private boolean isVaildClass(ActivityCaseAnnotionedClass item) {
        TypeElement classElement = item.getAnnotationedClassElement();
        // 检查是不是一个可见类
        if (!classElement.getModifiers().contains(Modifier.PUBLIC)) {
            error(classElement, "The class %s is not public.",
                    classElement.getQualifiedName().toString());
            return false;
        }

        // 检查是否是一个抽象类
        if (classElement.getModifiers().contains(Modifier.ABSTRACT)) {
            error(classElement, "The class %s is abstract. You can't annotate abstract classes with @%",
                    classElement.getQualifiedName().toString(), ActivityCase.class.getSimpleName());
            return false;
        }

        return true;
    }
}
