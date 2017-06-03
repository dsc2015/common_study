package spring_study;/**
 * Created by dushuangcheng on 2016/10/17.
 */

import com.jd.study.common.spring_study.SubAbstractClass;
import com.jd.study.common.spring_study.spring_annotation.FunctionComponent;
import com.jd.study.common.spring_study.spring_annotation.FunctionInterface;
import com.jd.study.common.spring_study.spring_annotation.FunctionInterfaces;
import com.jd.study.common.spring_study.spring_annotation.Person;
import com.jd.study.common.spring_study.spring_construct.PostConstructClass;
import com.jd.study.common.spring_study.spring_dependency.DemoService;
import com.jd.study.common.spring_study.spring_dependency.NoneIocClass;
import com.jd.study.common.spring_study.spring_extends.Child;
import com.jd.study.common.spring_study.spring_extends.SubClass;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author dushuangcheng
 * @create 2016-10-17 9:23
 *
 * 1,当装配多于一个bean的时候是会报错的，直接使用Autowired而不使用qualifier,是不是按默认的接口名的小写?
 * 2,多个实现类之间可以用Qualifier进行区分，Qualifier注解是跟autoAware注解一起使用的。
 * 3,ApplicationContext是属于spring-context中的内容，而它实现了spring-beans包下的相关的接口从而实现了getBean的方法，获取到bean。
 * 4，spring获取到bean首先是要读取配置文件的xmlBeanFactory中的XmlReader来操作的，从而实现对类路径下的资源进行加载。
 * 5,抽象类中属性依赖非抽象类的对象，抽象类的子类实现抽象类，并实现相关的方法，并把子类放入spring容器中进行管理是可行的。
 * 6，如果一个类并未纳入ioc容器，但是他通过autoAware注入一个属性，是否可行呢？
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class SpringAnnotationTest {
    //private ApplicationContext ioc=new ClassPathXmlApplicationContext("spring-config.xml");
    @Autowired
    private Person person;
    @Autowired
    private FunctionComponent functionComponent;
    @Autowired
    @Qualifier("functionInterface2")
    private FunctionInterface functionInterface;
    @Autowired
    //@Qualifier("functionInterfaces")
    private FunctionInterfaces functionInterfaces;
    @Autowired
    private Child child;
    @Autowired
    private SubClass subClass;
    @Autowired
    private SubAbstractClass subAbstractClass;

    @Autowired
    private DemoService demoService;


    @Autowired
    private PostConstructClass postConstructClass;

  /*  @Autowired
    private NoneIocClass noneIocClass;*/

    /**
     * @description  测试抽象类
     * @author dushuangcheng
     * @create 2017/1/10
     */
    @Test
    public void getSubAbstractClass(){
        subAbstractClass.getName();
    }

  /*  @Test
    public void getBeanTest(){
        System.out.println(ioc+"==================================="+functionInterface);
        FunctionInterface functionInterface2 = (FunctionInterface) ioc.getBean("functionInterface2");
        System.out.println("=================================>"+functionInterface2);
    }*/
    /**
     *
     * 测试继承关系之间的类 非抽象类
     */
    @Test
    public void extendsTest(){
        String name1 = child.getName1(2);
        System.out.println("==================>"+name1);
    }
    /**
     * 抽象父类，方法非抽象方法的时候
     * 结果输出为：
     * 子类初始化开始。。。。
       子类初始化开始。。。。

     会被加载两次
     */
    @Test
    public void abstractTest(){

    }

    @Test
    public void NoneIocDependency(){
       NoneIocClass n=new NoneIocClass();
        System.out.println(n.getName());
    }

    @Test
    public void testPostConstruct(){
        String str="1:3";
        System.out.println(str.substring(2));

        String gameAreaBet="0:0,1:0,2:0,3:1,4:1,5:0,6:0,7:0";
        String[] areaBet = gameAreaBet.split(",");
        for(String str1:areaBet){
            System.out.println(str1);
        }
        long[] longAreaBet = new long[areaBet.length];
        for (int i = 0; i < longAreaBet.length; i++) {
            System.out.println("--"+areaBet[i].substring(2));
            longAreaBet[i] = Long.parseLong(areaBet[i].substring(2));
        }

        for(long longs:longAreaBet){
            System.out.println("__________"+longs);
        }

    }
}
