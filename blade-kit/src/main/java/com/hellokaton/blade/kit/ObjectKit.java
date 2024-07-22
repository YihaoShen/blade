package com.hellokaton.blade.kit;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Object kit
 *
 * @author Yihao Shen
 * @date 2024/07/22
 */
public class ObjectKit {

    /**
     * Determinning whether an object is empty
     * @param obj The object which be needed to determine
     * @return returning true if object is empty
     */
    public static boolean isEmpty(@NonNull Object obj){
        if (obj == null){
            return true;
        }
        if (obj instanceof Optional){
            return !(((Optional<?>) obj).isPresent());
        }
        if (obj instanceof Array){
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection){
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map){
            return ((Map<?, ?>) obj).isEmpty();
        }

        return false;
    }

    /**
     * Determines whether an element exists in an array.
     *
     * @param container array
     * @param element of comparing
     * @return Returns true if it exists, otherwise returns false.
     */
    public static boolean containsElement(@NonNull Object[] container, Object element){
        if (container.length == 0) {
            return false;
        }
        for (Object o:container){
            if (safeObjectEquals(o,element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Safely determine whether two objects are equal.
     * @param a The parameter of the first comparison
     * @param b 第The parameter of the second comparison
     * @return Returns true if it is equal, and false otherwise.
     */
    public static boolean safeObjectEquals(@NonNull Object a, @NonNull Object b){
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.equals(b)) {
            return true;
        }
        return false;
    }

    /**
     * Determining whether two array types are equal.
     * @param o1 The first array
     * @param o2 The second array
     * @return Returning true if two types are equal, otherwise returning false.
     */

    public static boolean safeArrayEquals(Object o1, Object o2){
        if (o1 instanceof int[] && o2 instanceof int[]) {
            return Arrays.equals((int[]) o1, (int[]) o2);
        }
        if (o1 instanceof float[] && o2 instanceof float[]) {
            return Arrays.equals((float[]) o1, (float[]) o2);
        }
        if (o1 instanceof double[] && o2 instanceof double[]) {
            return Arrays.equals((double[]) o1, (double[]) o2);
        }
        if (o1 instanceof char[] && o2 instanceof char[]) {
            return Arrays.equals((char[]) o1, (char[]) o2);
        }
        if (o1 instanceof byte[] && o2 instanceof byte[]) {
            return Arrays.equals((byte[]) o1, (byte[]) o2);
        }
        if (o1 instanceof long[] && o2 instanceof long[]) {
            return Arrays.equals((long[]) o1, (long[]) o2);
        }
        if (o1 instanceof short[] && o2 instanceof short[]) {
            return Arrays.equals((short[]) o1, (short[]) o2);
        }
        if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
            return Arrays.equals((boolean[]) o1, (boolean[]) o2);
        }
        return false;
    }

    /**
     * The safe `toString()` method
     * @param o o -->' o.toString()'
     * @return Returning true if the rewrite is successful
     */
    public static String safeToString(@NonNull Object o){
        if (o == null){
            return "null";
        }
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof Integer) {
            return Integer.toString((int)o);
        }
        if (o instanceof Float){
            return Float.toString((float)o);
        }
        if (o instanceof Double){
            return Double.toString((double)o);
        }
        if (o instanceof Character){
            return Character.toString((char)o);
        }
        if (o instanceof Short){
            return Short.toString((short)o);
        }
        if (o instanceof Boolean){
            return Boolean.toString((boolean) o);
        }
        if (o instanceof Byte){
            return Byte.toString((byte)o);
        }

        if (o instanceof Object[]) {
            return safeToString((Object[]) o);
        }

        if (o instanceof int[]) {
            return safeToString((int[]) o);
        }

        if (o instanceof short[]) {
            return safeToString((short[]) o);
        }
        if (o instanceof float[]) {
            return safeToString((float[]) o);
        }
        if (o instanceof double[]) {
            return safeToString((double[]) o);
        }
        if (o instanceof boolean[]) {
            return safeToString((boolean[]) o);
        }
        if (o instanceof char[]) {
            return safeToString((char[]) o);
        }
        if (o instanceof long[]) {
            return safeToString((long[]) o);
        }
        if (o instanceof byte[]) {
            return safeToString((byte[]) o);
        }
        return o.toString();
    }


    /**
     * The safe `toString()` method
     * @param array array --> `array.toString()`
     * @return Returning true if the rewrite is successful
     */
    public static String safeToString(@NonNull Object[] array){
        if (array == null) return "null";

        int len = array.length;
        if (len == 0){
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for(Object o:array){
            sb.append(String.valueOf(o));
        }
        return sb.toString();
    }

    public static String safeToString(@NonNull int[] array){
        if (array == null) return "null";

        int len = array.length;
        if (len == 0){
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for(int o:array){
            sb.append(String.valueOf(o));
        }
        return sb.toString();
    }

    public static String safeToString(@NonNull float[] array){
        if (array == null) return "null";

        int len = array.length;
        if (len == 0){
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for(float o:array){
            sb.append(String.valueOf(o));
        }
        return sb.toString();
    }

    public static String safeToString(@NonNull double[] array){
        if (array == null) return "null";

        int len = array.length;
        if (len == 0){
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for(double o:array){
            sb.append(String.valueOf(o));
        }
        return sb.toString();
    }

    public static String safeToString(@NonNull char[] array){
        if (array == null) return "null";

        int len = array.length;
        if (len == 0){
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for(char o:array){
            sb.append(String.valueOf(o));
        }
        return sb.toString();
    }

    public static String safeToString(@NonNull boolean[] array){
        if (array == null) return "null";

        int len = array.length;
        if (len == 0){
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for(boolean o:array){
            sb.append(String.valueOf(o));
        }
        return sb.toString();
    }

    public static String safeToString(@NonNull byte[] array){
        if (array == null) return "null";

        int len = array.length;
        if (len == 0){
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for(byte o:array){
            sb.append(String.valueOf(o));
        }
        return sb.toString();
    }

    public static String safeToString(@NonNull long[] array){
        if (array == null) return "null";

        int len = array.length;
        if (len == 0){
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for(long o:array){
            sb.append(String.valueOf(o));
        }
        return sb.toString();
    }

    public static String safeToString(@NonNull short[] array){
        if (array == null) return "null";

        int len = array.length;
        if (len == 0){
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for(short o:array){
            sb.append(String.valueOf(o));
        }
        return sb.toString();
    }
}
