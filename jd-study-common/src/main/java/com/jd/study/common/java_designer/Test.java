package com.jd.study.common.java_designer;/**
 * Created by dushuangcheng on 2016/10/26.
 */

import com.jd.study.common.java_designer.DynamicProxy.Consumer_one;
import com.jd.study.common.java_designer.DynamicProxy.Consumer_two;
import com.jd.study.common.java_designer.DynamicProxy.DynamicProxy;
import com.jd.study.common.java_designer.DynamicProxy.ProxyFactory;
import com.jd.study.common.java_designer.StaticDesigner.FunctionInterface;
import com.jd.study.common.java_designer.StaticDesigner.FunctionProxy;
import sun.misc.ProxyGenerator;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author dushuangcheng
 * @create 2016-10-26 9:17
 * <p/>
 * 1，代理类是如何产生的？代理类的生成是在代码中调用Proxy类的newInstance(ClassLoader,Class<?>[] interfaces,InvocationHandler)
 * 需要一个targetObject的classLoader用于加载目标类的class文件，需要目标类的所有已经实现的接口，需要一个实现了InvocationHandler
 * 的实现类。这是自己实现的调用处理器主要用于对代理类在执行目标方法的时候动态地织入一些自己的实现。
 * 2，那么代理类是如何生成的呢？
 * 通过反编译获取到这个代理类的class文件，可以发现，其实这个代理类实现了目标类的所有的接口，同时继承了Proxy类
 * 也是证实了代理模式中代理类与被代理的类都要实现相同的接口，侧面证明了JDK的动态代理是基于接口的。
 *
 * 生成的代理类的结构如下：继承Proxy类实现相应的接口
 * public final class ProxyClass extends Proxy implements FunctionInterface
 *
 * 构造器中包含一个InvocationHandler类型的对象，这就是为什么要在方法中传入这个类型对象的原因。
 *
 * public ProxyClass(InvocationHandler paramInvocationHandler)
{
super(paramInvocationHandler);
}
 *
 * 所用的方法都是final类型的，这些方法中基本的有hashCode,toString等基本的方法，也包括接口中定义的方法，这些方法的实现
 * 是基于handler的invoke 进行调用的。
 *
 * 所以也就证明了为什么说使用代理对象调用目标方法都会是被代理类中的方法。
 *
 * 方法：public final void findHouse(String paramString, double paramDouble)
throws
{
try
{
this.h.invoke(this, m3, new Object[] { paramString, Double.valueOf(paramDouble) });
return;
}
catch (RuntimeException localRuntimeException)
{
throw localRuntimeException;
}
catch (Throwable localThrowable)
{
}
throw new UndeclaredThrowableException(localThrowable);
}
 *
 *
 *
 *缺点：被代理类都必须继承Proxy类，同时实现接口。具有一定的局限性，无法处理继承代理的问题。
 *
 *
 * 关于java的内存划分：
 * java把内存分两种：一种是栈内存，另一种是堆内存
(1)在函数中定义的基本类型变量和对象的引用变量都在函数的栈内存中分配；
(2)堆内存用来存放由new创建的对象和数组以及对象的实例变量。在函数（代码块）中定义一个变量时，java就在栈中为这个变量分配内存空间，当超过变量的作用域后，java会自动释放掉为该变量所分配的内存空间；在堆中分配的内存由java虚拟机的自动垃圾回收器来管理
堆和栈的优缺点：
堆的优势是可以动态分配内存大小，生存期也不必事先告诉编译器，因为它是在运行时动态分配内存的。
缺点就是要在运行时动态分配内存，存取速度较慢；栈的优势是，存取速度比堆要快，仅次于直接位于CPU中的寄存器。
另外，栈数据可以共享。但缺点是，存在栈中的数据大小与生存期必须是确定的，缺乏灵活性。
 *
 */
public class Test {
    public static void main(String[] args) throws Throwable {
        //获取代理对象
        FunctionProxy functionProxy = new FunctionProxy();
        functionProxy.findHouse("北京", 10000 - 0);
        functionProxy.getHouseInfo();

        //动态代理测试
        //如何去代理呢？
        //1,获取被代理类的实例
        FunctionInterface functionInterface = new Consumer_one();
        //2，获取被代理类的类加载器，这样方便在jvm能够加载被代理类的内部结构
        ClassLoader classLoader = functionInterface.getClass().getClassLoader();
        //3,获取被代理来的所有已经实现的接口
        Class<?>[] interfaces = functionInterface.getClass().getInterfaces();

        //4,用已经获取到的被代理对象构造代理对象
        InvocationHandler invocationHandler = new DynamicProxy(functionInterface);
        //5,返回值是被代理类的所有已经实现的接口的对象
        Object o = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        //6，对象类型的强制转化获取代理对象
        FunctionInterface dynamicProxy = (FunctionInterface) o;
        //代理对象的类型是： com.sun.proxy.$Proxy0
        System.out.println("代理对象的类型是： " + dynamicProxy.getClass().getName());

        dynamicProxy.findHouse("北京", 10000d);


        //todo:二
        //直接调用工厂类,获取代理对象，注意类型转换
        FunctionInterface proxyInstance = (FunctionInterface) new ProxyFactory(new Consumer_two()).getProxyInstance();
        System.out.println("proxyInstance=====" + proxyInstance);
        proxyInstance.findHouse("河南", 10000d);



        //获取生成的代理类，查看生成的代理类的结构。
        byte[] proxyClasses = ProxyGenerator.generateProxyClass("ProxyClass", new Class[]{FunctionInterface.class});
        System.out.println(proxyClasses.length);
       // System.out.println(proxyClasses);

       // File file = new File("D:\\Proxy");
        FileOutputStream fileOutputStream=new FileOutputStream("D:\\Proxy\\ProxyClass.txt");
        //BufferedInputStream bis=new BufferedInputStream(fileOutputStream);
        BufferedOutputStream bos=new BufferedOutputStream(fileOutputStream);
        for(int i=0;i<proxyClasses.length;i++){
            bos.write(proxyClasses, 0, proxyClasses.length);
        }

        bos.close();

    }
}
