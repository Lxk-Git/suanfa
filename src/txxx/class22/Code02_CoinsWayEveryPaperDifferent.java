package txxx.class22;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/16 21:27
 */
public class Code02_CoinsWayEveryPaperDifferent {


    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int aim) {
        if (aim < 0) {//没钱了，直接返回零
            return 0;
        }
        if (index == arr.length) {//只剩下最后一个了index == arr.length是越界了
            return aim == 0 ? 1 : 0;//aim是不是剩下0，是就是一种方法，不是就是0
        } else {
            //就两种情况，拿index和不拿index
            return process(arr, index + 1, aim) + process(arr, index + 1, aim - arr[index]);
        }
    }


    //dp,先花图
    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int n = arr.length;
        int[][] dp = new int[n+1 ][aim+1 ];
        dp[n][0] = 1;//因为需要拿n的数据数据，所以为了不越界，要加1，当然加2加三都可以
        for (int index = n - 1; index >= 0; index--) {//从下到上
            for (int rest = 0; rest <= aim; rest++) {//从左到右 rest是小于等于aim
                //注意这个rest - arr[index] >= 0 正确的时候才能加
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
