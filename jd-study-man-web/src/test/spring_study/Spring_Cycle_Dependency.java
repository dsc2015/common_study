package spring_study;/**
 * Created by dushuangcheng on 2017/1/10.
 */

import com.jd.study.common.spring_study.spring_cycle_dependency.TestClassA;
import com.jd.study.common.spring_study.spring_cycle_dependency.TestClassB;
import com.jd.study.common.spring_study.spring_cycle_dependency.TestClassC;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author dushuangcheng
 * @create 2017-01-10 10:55
 * 1,循环依赖的问题，循环依赖存在两种1，构造器注入循环依赖，这样的依赖是无法解决的，这样的循环依赖会报错，这个需要在代码中
 * 进行避免；setter方法注入的形式，spring中有专门的代码去处理这样的，而且是只能处理singleleton的bean之间的依赖关系，对于scope为prototype的bean无法完成循环依赖注入
 * 因为没有cache
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class Spring_Cycle_Dependency {
    @Autowired
    private TestClassA testClassA;
    @Autowired
    private TestClassB testClassB;
    @Autowired
    private TestClassC testClassC;

    @Test
    public void testInit(){

    }
}
