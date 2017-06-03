package spring_study;/**
 * Created by dushuangcheng on 2017/1/20.
 */

import IceUtilInternal.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Map;

/**
 * @author dushuangcheng
 * @create 2017-01-20 1:50
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test2.xml")*/
public class spring_postConstruct {
    @Test
    public void init(){
        ApplicationContext aop=new ClassPathXmlApplicationContext("spring-test2.xml");



       /* Map<String, String> map = StringUtil.stringCommaToTreeMap(gameAreaBet);
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            areaBet[i++] = Long.valueOf(entry.getValue());
        }*/

    }
}
