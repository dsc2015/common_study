package com.jd.study.common.java_designer.builderPattern;


/**
 * @author dushuangcheng
 * @description builder的设计模式
 * @create 2017/5/5
 */
public class BuilderPattern {
    public static void main(String[] args) {
        BuilderPattern dsc = new Builder("dsc", 10).number(100L).distance(1000L).build();
        System.out.println(dsc);
    }
    private String name;
    private Integer age;
    private Long distance;
    private Long number;

    //设计一个静态的内部类
    static class Builder {
        //所有类公有的属性
        private String name;
        private Integer age;

        //可以选择的属性值
        private Long distance;
        private Long number;
        //对必要参数的构造器

        public Builder(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        //通过方法构造进参数里面去
        public Builder distance(Long dis) {
            distance = dis;
            return this;
        }

        public Builder number(Long num) {
            number = num;
            return this;
        }

        //提供一个build方法构造出外围类的对象
        public BuilderPattern build() {
            return new BuilderPattern(this);
        }

    }
    private BuilderPattern (Builder builder){
        name=builder.name;
        age=builder.age;
        distance=builder.distance;
        number=builder.number;
    }

    @Override
    public String toString() {
        return "BuilderPattern{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", distance=" + distance +
                ", number=" + number +
                '}';
    }
}

