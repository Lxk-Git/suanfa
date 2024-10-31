package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/16 20:38
 */
public class Random {
    //Math.random是等概率出现[0,1)区间的数据
    //问题，怎么[0,6)等概率返回
    //第一步 f1方法，要等概率出现[1,5)范围的数据
    public static int f1(){
        return (int) (Math.random() * 5 ) + 1;
    }
    //第二步 f2方法，将1,2,3,4,5分成只能等概率返回0和1
    public static int f2(){
        int ans = 0;
        do{
            ans = f1();
        }while (ans == 3);//重点，等于3的时候就直接重新循环，这样就能等概率打到1,2,4,5上！！！
        return ans < 3 ? 0 : 1;
    }
    //第三步 f3方法，将二进制的000~111等概率返回，也就是0,7等概率返回
    public static int f3(){
        return (f2()<< 2) +(f2()<< 1)+(f2());//用位运算，0左移两位，一位，零位都是000，1位移1位是2,2位就是4，他们排列组合就能得到000~111
    }
    //第四步 f4方法，和f2相似，将多余的7重复循环，一直打到0,6上就好了
    public static int f4(){
        int ans = 0;
        do {
            ans = f3();
        }while (ans == 7 );
        return ans;
    }

    //问题，怎么让17~56等概率返回，就是返回0~39的数据等概率返回，再加上17就好了
    public static int b(){
        int dd = (f2() << 5) +(f2() << 4) + (f2() << 3) + (f2() << 2) + (f2() << 1) + f2();
        return dd;
    }
    public static double c(){
        double ans = 0;
        do{
            ans = b();
        }while (ans>39);
        return ans;
    }
    public static double d(){
        return c()+17;
    }


    // 你只能知道，x会以固定概率返回0和1，但是x的内容，你看不到！
    public static int x() {
        return Math.random() < 0.84 ? 0 : 1;
    }
    // 然后要能够等概率返回0和1
    public static int y() {
        int ans = 0;
        do {
            ans = x();
        } while (ans == x());
        return ans;
    }


    public static void main(String[] args) {
        System.out.println("测试");
        int testTimes = 1000000;
        int[] counts = new int[65];
        for (int i = 0; i < testTimes; i++) {
            int ans = (int) d(); // [0,K-1]
            counts[ans]++;
        }
        for (int i = 17; i < 57; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }
    }
}
