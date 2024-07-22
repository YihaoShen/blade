package com.hellokaton.blade.kit;

import com.hellokaton.blade.kit.model.MyPerson;
import com.hellokaton.blade.kit.model.Person;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author biezhi
 * @date 2018/4/9
 */
public class BeanKitTest {

    @Test
    public void testCopy() {
        Person source = new Person("jack", "nu", 22);
        Person dest   = new Person();
        BeanKit.copy(source, dest);
        Assert.assertEquals(source.toString(), dest.toString());

        Person dest2 = BeanKit.copy(source, Person.class);
        Assert.assertEquals(source.toString(), dest2.toString());

        MyPerson myPerson = BeanKit.copy(source, MyPerson.class);
        Assert.assertNotNull(myPerson.getName());
    }

    @Test
    public void testCopyWithIgnores(){
        Student s1 = new Student("cherry",18,"xxx@putlook.com");
        Student s2 = new Student();
        BeanKit.copy(s1, s2, "email");
        System.out.println(s2);
    }


    static class Student{
        private String name;
        private int age;
        private String email;

        public Student() {
        }

        public Student(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

}
