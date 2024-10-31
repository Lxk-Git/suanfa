package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/25 14:23
 */
public class BitAddMinusMultiDiv01 {
    //加法
    //就是a^b无进位再加上 a&b<<1，因为不能用加号，所以用a^b作为新的a,a&b<<1作为新的b,一直递归到 a&b<<1 为0,再无进位相加就得到结果了。
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;//相当于新的a
        }

        return sum;
    }
    //减法
    //减法的重点就是 a + (-b)，但是不能用加号也不能用负号 ，-b 可以换成 b取反加上1
    //先算-b, ~b+1 = -b 这个牵扯到原码反码补码的事情，先不管
    public static int negNum(int n){
        return add(~n,1);
    }
    //然后再用a + (-b) 就是a + (~b+1)
    public static int minus(int a,int b){
        return add(a,negNum(b));
    }
    //乘法 a*b就是当b不为0的时候，当b与1相&不为0就代表a可以增加一次，然后b往右不带符号位移1位，a往左边位移一位。
    public static int multi(int a,int b){
        int res = 0;
        while (b!=0){
            if((b&1)!=0){
                res = add(res,a);
            }
            a <<= 1;
            b >>>=1;
        }
        return res;
    }
    //除法
    //判断是不是负数
    public static boolean isNeg(int n) {
        return n < 0;
    }
    public static int div(int a, int b) {//重点就是取绝对值
        int x = isNeg(a) ? negNum(a) : a;//取绝对值
        int y = isNeg(b) ? negNum(b) : b;//取绝对值
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {//i = minus(i, 1) 就是i--
            if ((x >> i) >= y) {//如果x右移i位是大于等于y的
                res |= (1 << i);//
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }
    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                int c = div(add(a, 1), b);
                return add(c, div(minus(a, multi(c, b)), b));
            }
        } else {
            return div(a, b);
        }
    }

}
