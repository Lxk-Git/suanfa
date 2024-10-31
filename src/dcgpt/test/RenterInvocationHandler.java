package dcgpt.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: LiuXingKun
 * @Date: 2023/5/7 23:03
 */
public class RenterInvocationHandler<T> implements InvocationHandler {
    //被代理类的对象
    private T target;

    public RenterInvocationHandler(T target){
        this.target = target;
    }

    /**
     * proxy:代表动态代理对象
     * method：代表正在执行的方法
     * args：代表调用目标方法时传入的实参
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //代理过程中插入其他操作
        System.out.println("租客和中介交流");
        Object result = method.invoke(target, args);
        return result;
    }

}
