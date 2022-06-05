package com.machine.manager;


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
public class TestOld {
    public static int maxDepth(String s) {
        char[] cs = s.toCharArray();
        //定义嵌套深度和最大嵌套深度
        int depth = 0, maxDepth = 0;
        //遍历
        for(char c: cs){
            //左括号，嵌套深度加一并和最大值比较，大于最大值则替换最大值
            if(c == '('){
                depth++;
                if(depth > maxDepth){
                    maxDepth = depth;
                }
            }
            //右括号，嵌套深度减一
            if(c == ')'){
                depth--;
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {

        String sq = "(1+(2*3)+((8)/4))+1";
        maxDepth(sq);
//        List<Stu> list = new ArrayList<>();
//
//        Stu stu = new Stu();
//        stu.setName("za1");
//        stu.setAge(1);
//        stu.setSex("m");
//        list.add(stu);
//
//        System.out.println("stu== " + stu.toString());
//
//        for(int a = 0; a< 5; a++){
//            Stu d = new Stu();
//            d.setSex("m");
//            d.setAge(a);
//            d.setName("za"+1);
//            System.out.println("d== " + d.toString());
//            if(!list.contains(d)){
//                list.add(d);
//            }
//
//        }
//        System.out.println("list== " + list.size());


        FixSizeLinkedList<String> list = new FixSizeLinkedList<>(4);

        System.out.println("集合大小：" + list.size());

        list.add("12345");
        list.add( "1234");

        System.out.println("2集合大小：" + list.size());

        list.add( "123");
        list.add( "12");
        list.add( "1");
        list.add( "0");


        for (String s : list) {
            System.out.println(s);
        }
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
