package com.grass.processor;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import com.grass.annotation.ActivityCase;

/**
 * Created by grass on 15/12/20.
 */
public class ActivityCaseAnnotionedClass {
    private TypeElement mAnnotationedClassElement;
    private String mDes;
    private String mName;
    private String className;
    private String mSimpleName;

    public ActivityCaseAnnotionedClass(TypeElement annotationedClassElement) {
        mAnnotationedClassElement = annotationedClassElement;
        // get annotation info
        ActivityCase annottion = mAnnotationedClassElement.getAnnotation(ActivityCase.class);
        mName = annottion.name();
        mDes = annottion.des();

        TypeMirror tm = mAnnotationedClassElement.asType();
        className = mAnnotationedClassElement.getQualifiedName().toString();
        mSimpleName = mAnnotationedClassElement.getSimpleName().toString();
    }

    public String getDes() {
        return mDes;
    }

    public TypeElement getAnnotationedClassElement() {
        return mAnnotationedClassElement;
    }

    public String getName() {
        return mName;
    }

    public String getSimpleName() {
        return mSimpleName;
    }

    public String getClassName() {
        return className;
    }
}
