package com.lwh147.practice.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @description: 泛型toString方法
 * @author: lwh
 * @create: 2020/11/26 22:13
 * @version: v1.0
 **/
public class ObjectAnalyzer {
    /**
     * 保存已经访问过的对象
     **/
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        // 是否已经访问
        if (visited.contains(obj)) {
            return "...";
        }
        // 未访问，现在要访问，加入已访问列表
        visited.add(obj);
        // 获取对象的class
        Class<?> cl = obj.getClass();
        // 如果是String类型直接强转返回
        if (cl == String.class) {
            return (String) obj;
        }
        // 判断是不是数组对象
        if (cl.isArray()) {
            StringBuilder result = new StringBuilder();
            // 获取数组的元素种类进行打印
            result.append(cl.getComponentType().getName()).append("[]{");
            // 遍历打印
            for (int i = 0; i < Array.getLength(obj); i++) {
                // 不是第一个元素，打印分隔符
                if (i > 0) {
                    result.append(", ");
                }
                // 反射，获取指定下标的元素对象
                Object value = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()) {
                    // 元素种类是基本数据类型的处理方法，直接添加
                    result.append(value);
                } else {
                    // 元素种类是对象，递归调用自身进行打印
                    result.append(toString(value));
                }
            }
            return result.append("}").toString();
        }
        // 不是数组，构建类的打印结果
        StringBuilder result = new StringBuilder(cl.getName());
        result.append("@").append(Integer.toHexString(cl.hashCode()));
        // 循环打印该类所有的超类的field
        while (cl != Object.class && cl != null) {
            // 获取所有声明的域
            Field[] fields = cl.getDeclaredFields();
            // 没有域，不打印
            if (fields.length == 0) {
                break;
            }
            // 设置域的访问权限为true，反射机制的默认行为会受限于java的访问机制
            AccessibleObject.setAccessible(fields, true);
            result.append("{");
            // 遍历域，打印
            for (Field f : fields) {
                // 非静态域才打印
                if (!Modifier.isStatic(f.getModifiers())) {
                    // 分隔符
                    if (!result.toString().endsWith("{")) {
                        result.append(", ");
                    }
                    result.append(f.getName()).append("=");
                    try {
                        Class<?> type = f.getType();
                        Object value = f.get(obj);
                        if (type.isPrimitive()) {
                            result.append(value);
                        } else {
                            result.append(toString(value));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            result.append("}");
            cl = cl.getSuperclass();
        }

        return result.toString();
    }
}
