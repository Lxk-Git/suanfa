package txxx.class06;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/15 15:30
 */
public class Code06_CountOfRangeSum {


    //一个数组arr[]，一个范围【lower,upper】,看这个数组中的随意个数累加，在这个范围之内有多少组数据

    //依旧先写merge部分的代码
    //前置知识
    //1.现在的merge中不再使用arr，而是使用前缀和，就是0到0的范围的和是多少，0-1,0-2....放进新数组，
    //2.上面的问题可以换成前缀和X中的数据有多少在【X-upper,X-lower】中，累加就是想要的结果
    //3.怎么算第二步，用两个变量windowL，windowR，他们在数组中位移得到窗口就能得出结果。为啥这样能得到不清楚！没看懂


    public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {//arr是方前缀和的arr,不是原来的arr了
        int ans = 0;
        int windowL = L;
        int windowR = L;
        for (int i = M + 1; i < R; i++) {
            long max = arr[i] - lower;
            long min = arr[i] - upper;
            //要确保windowR在windowL的右边
            while (windowL <= M && arr[windowL] < min) { //这里是小于就是不能让相等时候下标还能移动，否则，下面ans就算不出来了。
                windowL++;
            }
            while (windowR <= M && arr[windowR] <= max) {
                windowR++;
            }
            ans += windowR - windowL;
        }

        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }

        while (p2 <= M) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[i + L] = help[i];
        }
        return ans;
    }

    public static int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {//相等了就是结束递归的时候
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = L+((R-L)>>1);
        return process(sum,L,M,lower,upper)+process(sum,M+1,R,lower,upper)+merge(sum,L,M,R,lower,upper);
    }

    public static int countOfRangeSum(int[] arr,int lower, int upper){
        if(arr ==null || arr.length <2){
            return 0;
        }
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1;i<arr.length;i++){
            sum[i] = sum[i] + sum[i-1];
        }
        return process(sum,0,sum.length-1,lower,upper);
    }


}
