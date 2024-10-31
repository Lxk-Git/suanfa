package dcgpt.class01;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/28 16:13
 */
public class Code03_Near2Power {
    //用位运算
    //但是为了解决可能n的最近2的m次方就是自己，那么需要n--;
    //n |= n>>>1就是n =  n | (n>>>1);n与上n不带符号右移一位
    //下面类推
    //比如现在是25，减去1是24，二进制是11000，右移一位与就是11100，右移2位与就是11111
    //为了什么只要弄到16？因为int只有32位
    public static int near2Power(int n) {
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? -1 : n + 1;
    }
    public static void main(String[] args) {
        int cap = 25;
        System.out.println(near2Power(cap));
    }
}
