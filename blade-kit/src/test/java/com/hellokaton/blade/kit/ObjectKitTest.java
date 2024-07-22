package com.hellokaton.blade.kit;

import org.junit.Test;

public class ObjectKitTest {
    @Test
    public void testObjectKit(){
        System.out.println(ObjectKit.safeToString(new int[]{1, 2, 3, 4, 5}));
        int[] a1 = new int[]{1,2,3};
        int[] a2 = new int[]{1,2,3};
        boolean arrayEqual = ObjectKit.safeArrayEquals(a1, a2);
        System.out.println(arrayEqual);
    }
}
