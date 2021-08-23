package com.machine.manager;


import com.machine.manager.json.JsonRootBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <pre>
 *     author : guoxiaojun5
 *     e-mail : guoxiaojun5@jd.com
 *     time   : 2021/07/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Test {
    public static void main(String[] args) {
        List<Stu> list = new ArrayList<>();

        Stu stu = new Stu();
        stu.setName("za1");
        stu.setAge(1);
        stu.setSex("m");
        list.add(stu);

        System.out.println("stu== " + stu.toString());

        for(int a = 0; a< 5; a++){
            Stu d = new Stu();
            d.setSex("m");
            d.setAge(a);
            d.setName("za"+1);
            System.out.println("d== " + d.toString());
            if(!list.contains(d)){
                list.add(d);
            }

        }
        System.out.println("list== " + list.size());
    }


    static class Stu{
        int age;
        String name;
        String sex;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Stu)) return false;
            Stu stu = (Stu) o;
            return age == stu.age && name.equals(stu.name) && sex.equals(stu.sex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name, sex);
        }

        @Override
        public String toString() {
            return "Stu{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }
}
