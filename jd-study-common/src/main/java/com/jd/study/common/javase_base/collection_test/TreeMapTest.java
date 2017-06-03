package com.jd.study.common.javase_base.collection_test;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 1,key若按照自然排序，用到comparable接口；定制排序用到comporator接口，必须实现，否则报错：
 * ==========================================================================================
 * final int compare(Object k1, Object k2) {
 * //在这里强制转为comparable接口类型的对象，如果没实现则会报classcast的异常。
 * return comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2): comparator.compare((K)k1, (K)k2);
 * }
 * <p/>
 * 关于comparable 这个接口定义在java.lang包下的
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 1,为什么称为自然排序？
 * 在jdk中的大部分内置的类都实现了这个接口，这个实现是于对象的equals方法的比较结果是保持一致的。
 * 所有实现 Comparable 的 Java 核心类都具有与 equals 一致的自然(内部)排序，但是bigDecimal除外，精度不同的视为是相等的。也就是equals返回true的情况下
 * 在本例子中使用的排序中：由于跟equals没有保持一致，所以才会出现多个对象加入到map中但是实际上只有一个对象在map中的情况。
 * <p/>
 * compareTo()方法返回值为0.
 * 1，a.compareTo(b)>0,a比b"大"。
 *
 * @author dushuangcheng
 * @create 2017-06-01 11:14
 */
public class TreeMapTest {
    public static void main(String[] args) {
        Map<Person, Integer> persons = new TreeMap<>();
        try {
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //byte[] bytes = new byte[1000000000];
        Map<Person, Integer> personIntegerMap = TreeMapTest.gerPersons(persons, 50000);
        TreeMapTest.searchMap(personIntegerMap);
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Map<Person, Integer> gerPersons(Map<Person, Integer> persons, int count) {
        if (persons == null) {
            return null;
        }
        for (int i = 0; i < count; i++) {
            Person p = new Person();
            p.setAge(i + 20);
            p.setName("per_" + i);
            p.setWeight(50.00 + i);
            persons.put(p, i);
        }
        return persons;
    }

    public static void searchMap(Map<Person, Integer> persons) {
        if (persons == null || persons.size() == 0) {
            return;
        }
        Set<Map.Entry<Person, Integer>> entryPersons = persons.entrySet();
        for (Map.Entry<Person, Integer> entry : entryPersons) {
            System.out.println("person:" + entry.getKey() + "===================================" + entry.getValue());
        }
    }

    static class Person implements Comparable<Person> {
        private String name;
        private Integer age;
        private double weight;

        public Person() {
        }

        public Person(String name, Integer age, double weight) {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", weight=" + weight +
                    '}';
        }

        /* @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;

             Person person = (Person) o;

             if (Double.compare(person.weight, weight) != 0) return false;
             if (age != null ? !age.equals(person.age) : person.age != null) return false;
             if (name != null ? !name.equals(person.name) : person.name != null) return false;

             return true;
         }

         @Override
         public int hashCode() {
             int result;
             long temp;
             result = name != null ? name.hashCode() : 0;
             result = 31 * result + (age != null ? age.hashCode() : 0);
             temp = Double.doubleToLongBits(weight);
             result = 31 * result + (int) (temp ^ (temp >>> 32));
             return result;
         }
 */
        @Override
        public int compareTo(Person person) {
            if (person.equals(this)) {
                return 0;
            }
            //order by name
            return -this.getName().compareTo(person.getName());
            //return (int) (this.getName().compareTo(person.getName())+this.getAge().compareTo(person.getAge())+(this.getWeight()-this.getWeight()));
           /* boolean isName = this.getName().equals(person.getName());
            boolean isAge=(this.getAge().intValue()==person.getAge().intValue());
            boolean isWeight=(this.getWeight()==person.getWeight());*/
        }
        /**
         * about a string comparable method
         */
        public int compareToTo(String anothorString) {
            return 0;
        }

       /* public V put(K key, V value) {
            Entry<K,V> t = root;
            if (t == null) {
                compare(key, key); // type (and possibly null) check

                root = new Entry<>(key, value, null);
                size = 1;
                modCount++;
                return null;
            }
            int cmp;
            Entry<K,V> parent;
            // split comparator and comparable paths
            Comparator<? super K> cpr = comparator;
            if (cpr != null) {
                do {
                    parent = t;
                    cmp = cpr.compare(key, t.key);
                    if (cmp < 0)
                        t = t.left;
                    else if (cmp > 0)
                        t = t.right;
                    else
                        return t.setValue(value);
                } while (t != null);
            }
            else {
                if (key == null)
                    throw new NullPointerException();
                Comparable<? super K> k = (Comparable<? super K>) key;
                do {
                    parent = t;
                    cmp = k.compareTo(t.key);
                    if (cmp < 0)
                        t = t.left;
                    else if (cmp > 0)
                        t = t.right;
                    else
                        return t.setValue(value);
                } while (t != null);
            }
            Entry<K,V> e = new Entry<>(key, value, parent);
            if (cmp < 0)
                parent.left = e;
            else
                parent.right = e;
            fixAfterInsertion(e);
            size++;
            modCount++;
            return null;
    }*/
    }
}
