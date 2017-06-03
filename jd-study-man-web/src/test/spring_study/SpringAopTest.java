package spring_study;/**
 * Created by dushuangcheng on 2017/1/10.
 */

import com.jd.study.common.spring_study.spring_aop.jdk_proxy.Function;
import com.jd.study.common.spring_study.spring_aop.jdk_proxy.FunctionImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author dushuangcheng
 * @create 2017-01-10 15:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class SpringAopTest {
    private ApplicationContext ioc=new ClassPathXmlApplicationContext("/spring-config.xml");

    @Test
    public void initTest(){
        Function functionImpl = (Function) ioc.getBean("functionImpl");
        String name = functionImpl.getName();
        System.out.println(name);

        functionImpl.calculate(10,0);
    }


}
