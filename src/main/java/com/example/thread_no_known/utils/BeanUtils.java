
package com.example.thread_no_known.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


@Slf4j
public abstract class BeanUtils {


    /**
     * 将 bean 中值不为 null 的属性值全部打印出来.
     * <p>例子：</p>
     * <pre>
     * MemberInfo memberInfo = new MemberInfo();
     * memberInfo.setId(650000);
     * memberInfo.setMemberName(&quot;TyroneChan&quot;);
     * String dump = dumpNotNull(memberInfo);
     * </pre>
     * <p>dump 的值为：<br/>
     * com.binguo.model.domain.MemberInfo{id = 650000, memberName = "TyroneChan", }</p>
     *
     * @param bean Object 对象
     * @return
     */
    public static String dumpNotNull(Object bean) {
        return dump(bean, false);
    }

    /**
     * 调试, 打印出给定 Bean 的所有属性的取值.
     *
     * @param bean Bean 对象
     * @return
     */
    private static String dump(Object bean, boolean includeNullValue) {
        PropertyDescriptor[] descriptors = getAvailablePropertyDescriptors(bean);
        final String beanClassName = bean.getClass().getName();
        final StringBuffer values = new StringBuffer();

        for (int i = 0; descriptors != null && i < descriptors.length; i++) {
            Method readMethod = descriptors[i].getReadMethod();
            try {
                Object propValue = readMethod.invoke(bean, null);
                final String propName = descriptors[i].getName();
                final String propType = descriptors[i].getPropertyType().getName();
                if (null == propValue && !includeNullValue) {
                    continue;
                }
                values.append(propName).append(" = ");
                if ("java.lang.String".equals(propType)) {
                    values.append("\"");
                    values.append(propValue);
                    values.append("\"");
                } else {
                    values.append(propValue);
                }
                values.append(", ");
            } catch (Exception e) {
                log.error("error occurs ", e);
            }
        }

        if (values.length() == 0) {
            values.append(" ");
        } else {
            values.deleteCharAt(values.length() - 2);//删除最后的逗号
        }

        final StringBuffer sb = new StringBuffer();
        sb.append(beanClassName);
        sb.append("{");
        sb.append(values);
        sb.append("}");
        return sb.toString();
    }

    /**
     * 从 bean 中读取有效的属性描述符.
     * <p/>
     * NOTE: 名称为 class 的 PropertyDescriptor 被排除在外.
     *
     * @param bean Object - 需要读取的 Bean
     * @return PropertyDescriptor[] - 属性列表
     */
    private static PropertyDescriptor[] getAvailablePropertyDescriptors(Object bean) {
        try {
            // 从 Bean 中解析属性信息并查找相关的 write 方法
            BeanInfo info = Introspector.getBeanInfo(bean.getClass());
            if (info != null) {
                PropertyDescriptor pd[] = info.getPropertyDescriptors();
                Vector<PropertyDescriptor> columns = new Vector<PropertyDescriptor>();

                for (PropertyDescriptor aPd : pd) {
                    String fieldName = aPd.getName();

                    if (fieldName != null && !fieldName.equals("class")) {
                        columns.add(aPd);
                    }
                }

                PropertyDescriptor[] arrays = new PropertyDescriptor[columns.size()];

                for (int j = 0; j < columns.size(); j++) {
                    arrays[j] = columns.get(j);
                }
                return arrays;
            }
        } catch (Exception ex) {
            log.error("error occurs ", ex);
            return null;
        }
        return null;
    }


    public static HashMap<String, Object> beanToMap(Object obj) {
        HashMap<String, Object> params = new HashMap<String, Object>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }


    public static Map<String, String> objMapToStrMap(Map<String, Object> strMap) {
        Map<String, String> map = new HashMap<String, String>();
        Iterator<Map.Entry<String, Object>> it = strMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            if (entry.getValue() != null) {
                map.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return map;
    }


    /**
     * 将map转换成Bean，Bean的属性名与map的key值对应时不区分大小写，并对map中key做忽略OMIT_REG正则处理
     *
     * @param <E>
     * @param cla
     * @param map
     * @return
     */
    public static <E> E toBean(Class<E> cla, Map<String, Object> map) {
        // 创建对象
        E obj = null;
        try {
            obj = cla.newInstance();
        } catch (Exception e) {


            return null;
        }
        // 进行值装入
        Method[] ms = cla.getMethods();
        for (Method method : ms) {
            String name = method.getName();
            String mname = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
            if (mname.startsWith("set") || mname.startsWith("Set")) {
                Class[] clas = method.getParameterTypes();
                String a = mname.substring(3, mname.length());
                a = captureName(a);
                Object v = map.get(a);
                if (v != null && clas.length == 1) {
                    try {
                        method.invoke(obj, v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }


    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);
    }


}
