
package com.hannesdorfmann.annotationprocessing101.factory.processor;

import com.hannesdorfmann.annotationprocessing101.factory.annotation.Factory;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;

import org.apache.commons.lang3.StringUtils;

/**
 * 保存关于带有@factory注释的类的信息
 *
 * @author Hannes Dorfmann
 */
public class FactoryAnnotatedClass {

    private TypeElement annotatedClassElement;
    //指定的类型合法全名
    private String qualifiedGroupClassName;
    //指定的类型的简单名字
    private String simpleFactoryGroupName;
    private String id;

    /**
     * 抛出ProcessingException，如果来自注释的id（）是null
     */
    public FactoryAnnotatedClass(TypeElement classElement) throws ProcessingException {
        this.annotatedClassElement = classElement;
        Factory annotation = classElement.getAnnotation(Factory.class);
        id = annotation.id();

        if (StringUtils.isEmpty(id)) {
            throw new ProcessingException(classElement,
                    "id() in @%s for class %s is null or empty! that's not allowed",
                    Factory.class.getSimpleName(), classElement.getQualifiedName().toString());
        }

        // 得到完整的QualifiedTypeName
        try {
            Class<?> clazz = annotation.type();
            qualifiedGroupClassName = clazz.getCanonicalName();
            simpleFactoryGroupName = clazz.getSimpleName();
        } catch (MirroredTypeException mte) {
            DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
            TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
            qualifiedGroupClassName = classTypeElement.getQualifiedName().toString();
            simpleFactoryGroupName = classTypeElement.getSimpleName().toString();
        }
    }

    /**
     * 获取在{@link Factory#id()}中指定的id
     * return the id
     */
    public String getId() {
        return id;
    }

    /**
     * 获取在{@link Factory#type()}指定的类型合法全名
     *
     * @return qualified name
     */
    public String getQualifiedFactoryGroupName() {
        return qualifiedGroupClassName;
    }

    /**
     * 获取在{@link Factory#type()}{@link Factory#type()}指定的类型的简单名字
     *
     * @return qualified name
     */
    public String getSimpleFactoryGroupName() {
        return simpleFactoryGroupName;
    }

    /**
     * 获取被@Factory注解的原始元素
     */
    public TypeElement getTypeElement() {
        return annotatedClassElement;
    }
}
