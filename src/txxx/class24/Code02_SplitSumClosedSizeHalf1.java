package txxx.class24;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/19 22:50
 */
//明天再敲一次
public class Code02_SplitSumClosedSizeHalf1 {


    //是偶数的时候，比如有8个，全部的和为100，那就只要是能够达到4个数的时候小于等于50就可以，选接近50的那个，那么就和第一题差不多，不过要注意只能选择4个。
    //是奇数的时候，比如有7个，全部的和为100，那么要3个和4个数的时候累加和小于等于50，选最接近50的那个


    //这里做重要的是要多一个参数picks，选择的个数不能超过他
    public static int right(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if ((arr.length & 1) == 0) {
            return process(arr, 0, arr.length / 2, sum / 2);
        } else {
            return Math.max(process(arr, 0, arr.length / 2, sum / 2), process(arr, 0, arr.length / 2 + 1, sum / 2));
        }
    }

    private static int process(int[] arr, int index, int picks, int rest) {
        if (index == arr.length) {
            return picks == 0 ? 0 : -1;//当数组中的元素没得了，picks也没有了，那就是一种可行的办法，就是没得选了，否则就不是。
        }
        int p1 = process(arr, index + 1, picks, rest);//不要的情况，index+1,picks不变，rest也不变
        int p2=-1;
        int next = -1;
        if(rest >= arr[index]){
            next = process(arr, index + 1, picks - 1, rest - arr[index]);//picks - 1越界也不怕，会结束
        }
        if(next!=-1){
            p2 = arr[index] + next;
        }
        return Math.max(p1, p2);
    }

    //dp，三维数组
    //picks做层
    public static int dp(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int len = arr.length;
        int p = (len +1 )/2;
        int[][][] dp = new int[len+1][sum+1][p+1];
        for (int i = 0; i <= len; i++) {//先把所有的位置设置成-1，也就是失效的情况
            for (int j = 0; j <= p; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][k][j] = -1;
                }
            }
        }
        for(int rest = 0;rest<=sum;rest++){
            dp[len][rest][0] = 0;
        }
        for(int index = len-1;index>=0;index--){
            for(int rest = 0;rest<=sum;rest++){
                for(int picks = 0;picks<=p;picks++){//picks从第0层开始
                    int p1 = dp[index + 1][rest][picks];
                    int p2=-1;
                    int next = -1;
                    if(picks-1>=0 && rest >= arr[index]){//picks-1>=0是因为不能越界，数组要注意越界的问题
                        next = dp[index + 1][rest-arr[index]][picks-1];
                    }
                    if(next!=-1){
                        p2 = arr[index]+next;
                    }
                    dp[index][rest][picks] = Math.max(p1, p2);
                }
            }
        }
        if ((len & 1) == 0) {
            return dp[0][sum][len/2];
        } else {
            return Math.max(dp[0][sum][len/2],dp[0][sum][len/2+1]);
        }
    }
    //test

    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum >>= 1;
        int N = arr.length;
        int M = (arr.length + 1) >> 1;
        int[][][] dp = new int[N][M + 1][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= sum; k++) {
                dp[i][0][k] = 0;
            }
        }
        for (int k = 0; k <= sum; k++) {
            dp[0][1][k] = arr[0] <= k ? arr[0] : Integer.MIN_VALUE;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i + 1, M); j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (k - arr[i] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k - arr[i]] + arr[i]);
                    }
                }
            }
        }
        return Math.max(dp[N - 1][M][sum], dp[N - 1][N - M][sum]);
    }



    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            int ans3 = dp2(arr);
            if (ans1 != ans2 ) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
    /*public static void main(String[] args) {
        int[] arr = {10, 2, 3};
        int right = right(arr);
        int dp = dp(arr);
        System.out.println(right);
        System.out.println(dp);
    }*/
}
