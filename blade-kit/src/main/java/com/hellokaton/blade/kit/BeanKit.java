/**
 * Copyright (c) 2022, katon (hellokaton@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hellokaton.blade.kit;

import com.hellokaton.blade.exception.BeanCopyException;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author biezhi
 * @date 2018/4/9
 */
@UtilityClass
public class BeanKit {

    public static <T> T copy(Object origin, Class<T> destCls) {
        T dest = ReflectKit.newInstance(destCls);
        copy(origin, dest);
        return dest;
    }

    public static void copy(Object origin, Object dest) {
        String      fileName, str, getName, setName;
        List<Field> fields = new ArrayList<>();
        Method      getMethod;
        Method      setMethod;
        try {
            Class<?> c1 = origin.getClass();
            Class<?> c2 = dest.getClass();

            Class<?> c1Superclass = c1.getSuperclass();
            Class<?> c2Superclass = c2.getSuperclass();

            List<Field> fs1 = new ArrayList<>(Arrays.asList(c1.getDeclaredFields()));
            while (!c1Superclass.equals(Object.class)) {
                List<Field> parentFields = Arrays.asList(c1Superclass.getDeclaredFields());
                fs1.addAll(parentFields);
                c1Superclass = c1Superclass.getSuperclass();
            }

            List<Field> fs2 = new ArrayList<>(Arrays.asList(c2.getDeclaredFields()));

            while (!c2Superclass.equals(Object.class)) {
                List<Field> parentFields = Arrays.asList(c2Superclass.getDeclaredFields());
                fs2.addAll(parentFields);
                c2Superclass = c2Superclass.getSuperclass();
            }

            // two class attributes exclude different attributes, leaving only the same attributes.
            for (Field aFs2 : fs2) {
                for (Field aFs1 : fs1) {
                    if (aFs1.getName().equals(aFs2.getName())) {
                        fields.add(aFs1);
                        break;
                    }
                }
            }

            if (fields.size() > 0) {
                for (Field f : fields) {
                    fileName = f.getName();
                    // capitalize the first letter of the property name.
                    str = fileName.substring(0, 1).toUpperCase();
                    // getXXX and setXXX
                    getName = "get" + str + fileName.substring(1);
                    setName = "set" + str + fileName.substring(1);
                    try {
                        getMethod = c1.getMethod(getName);
                        setMethod = c2.getMethod(setName, f.getType());
                        if (null != getMethod && null != setMethod) {
                            Object o = getMethod.invoke(origin);
                            if (null != o) {
                                setMethod.invoke(dest, o);
                            }
                        }
                    } catch (NoSuchMethodException e) {
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCopyException(e);
        }
    }
    /**
     *
     * @param origin the origin object
     * @param dest  the destination object
     * @param ignoreProperties the fields you want to ignore
     * @author Yihao Shen
     */
    public static void copy(Object origin, Object dest, String... ignoreProperties) {
        if (origin == null || dest == null) {
            throw new IllegalArgumentException("The source object and destination object must not be null.");
        }

        List<String> ignoreList = Arrays.asList(ignoreProperties);

        Class<?> originClass = origin.getClass();
        Class<?> dstClass = dest.getClass();

        Field[] srcFields = originClass.getDeclaredFields();
        // copy field
        for (Field srcField : srcFields) {
            if (!ignoreList.contains(srcField.getName())) {
                try {
                    Field dstField = null;
                    dstField = dstClass.getDeclaredField(srcField.getName());
                    if (srcField.getType().equals(dstField.getType())) {
                        srcField.setAccessible(true);
                        dstField.setAccessible(true);
                        dstField.set(dest, srcField.get(origin));
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
