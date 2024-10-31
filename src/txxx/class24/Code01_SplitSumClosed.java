package txxx.class24;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/20 10:59
 */
public class Code01_SplitSumClosed {
    //这个题目就是要选择到的数据累加和小于等于整个arr累加和的一半
    //那就是整个数组从0到arr.length-1,要这个数index+1,rest-arr[index]；不要这个数index+1，rest不变
    //题目要求返回的是分成两个集合中较小的那个，但是方法是把集合的累加和的一半先求到，然后是应该返回最接近集合一半的数据，才是两个集合中较小的那个
    public static int splitSum(int[] arr){
        if(arr == null || arr.length<1){
            return 0;
        }
        int sum = 0;
        for(int i:arr){
            sum +=i;
        }
        return process(arr,0,sum/2);
    }

    private static int process(int[] arr, int index, int rest) {
        //base case
        //当index == arr.length,没有可以选的数据了，就返回0
        if(index == arr.length){
            return 0;
        }else {
            int p1 = process(arr,index+1,rest);//不要
            int p2 = 0;
            if(arr[index] <= rest){
                p2 = arr[index] + process(arr,index+1,rest-arr[index]);
            }
            return Math.max(p1,p2);
        }
    }


    public static int dp(int[] arr){
        if(arr == null || arr.length<1){
            return 0;
        }
        int sum = 0;
        for(int i:arr){
            sum +=i;
        }
        int k = sum/2;
        int n = arr.length;
        int[][] dp = new int[n+1][k+1];
        for(int j = 0;j<=k;j++){
            dp[n][j] = 0;
        }
        for(int index = n-1;index>=0;index--){
            for(int rest = 0;rest<=k;rest++){
                int p1 = dp[index+1][rest];//不要
                int p2 = 0;
                if(arr[index] <= rest){
                    p2 = arr[index] + dp[index+1][rest-arr[index]];
                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][k];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = splitSum(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");*/

        int[] arr = {100,1,1,1};
        int i = splitSum(arr);
        System.out.println(i);
    }
}
