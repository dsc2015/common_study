package spring_study;/**
 * Created by dushuangcheng on 2017/2/3.
 */

import com.jd.study.common.post_construct.AbstractClassWithPostC;
import com.jd.study.common.post_construct.SubClassOne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * @author dushuangcheng
 * @create 2017-02-03 17:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class SpringPostCTest {
    @Autowired
    private SubClassOne subClassOne;

    @Test
    public void testOne(){
        Map<String, Object> map = AbstractClassWithPostC.getMap();
        System.out.println(map);
    }
}
