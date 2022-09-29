package com.example.thread_no_known.myresp;

import com.example.thread_no_known.dto.DataDto;
import com.example.thread_no_known.utils.ClassMethodUtils;
import com.linzi.classloading.enhancers.LocalvariablesNamesEnhancer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
public class R {

    public static Map<String, Object> getResParam(Object... args) {
        Map<String, Object> result = new HashMap();
        Object[] var6 = args;
        int var5 = args.length;
        for (int var4 = 0; var4 < var5; ++var4) {
            Object o = var6[var4];
            List<String> names = LocalvariablesNamesEnhancer.LocalVariablesNamesTracer.getAllLocalVariableNames(o);
            Iterator var9 = names.iterator();
            while (var9.hasNext()) {
                String name = (String) var9.next();
                result.put(name, o);
            }
        }
        return result;
    }

    public static <T> T result(Object... args) {
        Object result = null;
        try {
            Throwable throwable = new Throwable();
            StackTraceElement[] stackTraceElements = throwable.getStackTrace();
            String className = stackTraceElements[1].getClassName();
            String methodName = stackTraceElements[1].getMethodName();
            int line = stackTraceElements[1].getLineNumber();
            Map<String, Object> resp = getResParam(args);
            String returnType = ClassMethodUtils.getMethodReturnType(className, methodName, line);
            if("Ljava/util/Map;".equals(returnType)){
                return (T)resp;
            }
            returnType = returnType.substring(1);
            returnType = returnType.substring(0, returnType.length() - 1);

            returnType = returnType.replaceAll("/", ".");
            Class clazz = Class.forName(returnType);
            result = clazz.newInstance();
            Field fields[] = clazz.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    if(field.getName().startsWith("$")){
                        continue;
                    }
                    field.setAccessible(true);
                    field.set(result, resp.get(field.getName()));
                }
            }
            return (T) result;
        } catch (Exception e) {
            log.error("异常",e);
        }
        return null;
    }

    public static DataDto testDateDto() {
        int i = 1;
        int b = 2;
        return result("a,", "b");
    }


    public static void main(String[] args) {
        System.out.println(testDateDto());
    }


}
