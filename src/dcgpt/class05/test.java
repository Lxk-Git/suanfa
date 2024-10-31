package dcgpt.class05;

import  java.lang.Class;
import java.util.Date;

public class test {
    public static void main(String[] args) throws Exception{
        Class<Date> dateClass = Date.class;
        System.out.println("方式1      :     "+dateClass);
        Date date2 = dateClass.newInstance();
        System.out.println(date2);
        System.out.println("====================");
        Date date=new Date();
        System.out.println("方式2       :    "+date.getClass());
        Class<? extends Date> aClass = date.getClass();
        Date date1 = aClass.newInstance();
        System.out.println(date1);
        System.out.println("======================================");
        //通过反射机制，获取Class，通过Class来实例化对象
        Class<?>  cl=Class.forName("java.util.Date");
        System.out.println("方式3        :   "+cl);
        //newInstance() 这个方法会调用Date这个类的无参数构造方法，完成对象的创建。
        // 重点是：newInstance()调用的是无参构造，必须保证无参构造是存在的！
        Object object=cl.newInstance();
        System.out.println(object);

    }
}
