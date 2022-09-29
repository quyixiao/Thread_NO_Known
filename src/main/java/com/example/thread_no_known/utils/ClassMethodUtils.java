package com.example.thread_no_known.utils;

import com.jvm.ClassParser;
import com.jvm.classfile.*;
import com.jvm.utils.*;

import java.io.IOException;
import java.io.InputStream;

public class ClassMethodUtils {

    public static String getMethodReturnType(String className,String methodName ,int line) {
        try {
            String resourcePath = ClassUtils.convertClassNameToResourcePath(className) + ClassUtils.CLASS_FILE_SUFFIX;
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            // 获取class的输入流
            InputStream is = ClassParser.getInputStream(classLoader, resourcePath);
            // 解析class方法
            ClassFile classFile = ClassFile.Parse(ClassReaderUtils.readClass(is));
            for (MemberInfo memberInfo : classFile.getMethods()) {
                MethodDescriptorParser parser = new MethodDescriptorParser();
                MethodDescriptor parsedDescriptor = parser.parseMethodDescriptor(memberInfo.Descriptor());
                StringBuilder parameterTypeStr = new StringBuilder();
                String byteMethodName = memberInfo.Name();          //方法名
                if (!byteMethodName.equals(methodName)) {           //如果方法名不相等，则重新查找
                    continue;
                }
                for (String parameterType : parsedDescriptor.parameterTypes) {
                    String d = ClassNameHelper.toStandClassName(parameterType);
                    parameterTypeStr.append(d);
                }
                AttributeInfo attributeInfos[] = memberInfo.getAttributes();
                // 获取Code下的第一个LineNumberTable
                for (AttributeInfo codeAttribute : attributeInfos) {
                    if (codeAttribute instanceof CodeAttribute) {
                        AttributeInfo codeAttributeInfos[] = ((CodeAttribute) codeAttribute).getAttributes();
                        for (AttributeInfo attributeInfo : codeAttributeInfos) {
                            if (attributeInfo instanceof LineNumberTableAttribute) {
                                LineNumberTableEntry[] lineNumberTableEntries = ((LineNumberTableAttribute) attributeInfo).getLineNumberTables();
                                LineNumberTableEntry firstLine = lineNumberTableEntries[0];
                                LineNumberTableEntry lastLine = lineNumberTableEntries[lineNumberTableEntries.length - 1];
                                if (line >= firstLine.getLineNumber().Value() && line <= lastLine.getLineNumber().Value()) {
                                    return parsedDescriptor.returnType;
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
