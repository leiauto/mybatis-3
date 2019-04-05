package com.lfq;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {


  /**
   * 接口
   */
  interface Subject{
    void sayHi();
    void sayHello();
  }

  /**
   * 实现类
   */
  static class SubjectImpl implements Subject{

    @Override
    public void sayHi() {
      System.out.println("hi");
    }

    @Override
    public void sayHello() {
      System.out.println("hello");
    }
  }


  /**
   * 动态代理类
   */
  static class ProxyInvocationHandler implements InvocationHandler{

    private Subject target;

    public ProxyInvocationHandler(Subject target) {
      this.target=target;
    }

    /**
     * proxy:代理类代理的真实代理对象com.sun.proxy.$Proxy0
     * method:我们所要调用某个对象真实的方法的Method对象
     * args:指代代理对象方法传递的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.print("say:");
      return method.invoke(target, args);
    }
    
  }
  
  public static void main(String[] args) {

    Subject subject=new SubjectImpl();

    /**
     * loader：一个classloader对象，定义了由哪个classloader对象对生成的代理类进行加载
     * interfaces：一个interface对象数组，表示我们将要给我们的代理对象提供一组什么样的接口，如果我们提供了这样一个接口对象数组，
     * 那么也就是声明了代理类实现了这些接口，代理类就可以调用接口中声明的所有方法。
     * h：一个InvocationHandler对象，表示的是当动态代理对象调用方法的时候会关联到哪一个InvocationHandler对象上，并最终由其调用。
     */
    Subject subjectProxy=(Subject)Proxy.newProxyInstance(
            subject.getClass().getClassLoader(),
            subject.getClass().getInterfaces(),
            new ProxyInvocationHandler(subject));

    subjectProxy.sayHi();
    subjectProxy.sayHello();
    
  }
}
