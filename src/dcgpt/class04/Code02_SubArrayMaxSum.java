package dcgpt.class04;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/30 22:45
 */
// 本题测试链接 : https://leetcode.com/problems/maximum-subarray/
public class Code02_SubArrayMaxSum {
    //这个题目有个管用套路，就是以i结尾的时候，最大值是多少
    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            //注意加上这一步，可以使得前面累加变成负数的不计入以后计算中
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    public static int maxSubArray11(int[] arr) {
        if (arr == null || arr.length < 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans += arr[i];
            max = Math.max(max, ans);
            ans = ans < 0 ? 0 : ans;
        }
        return max;
    }

    public static int maxSubArray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 上一步，dp的值
        // dp[0]
        int pre = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            //把上一步拿到的最大值给到pre,
            pre = Math.max(arr[i], arr[i] + pre);
            //再用pre和max对比得到最大值
            max = Math.max(max, pre);
        }
        return max;
    }

    public static int maxSubArray22(int[] arr){
        if(arr==null || arr.length<0){
            return 0;
        }
        int max = arr[0];
        int pre = arr[0];
        for(int i = 0;i<arr.length;i++){
            pre = Math.max(arr[i],arr[i]+pre);
            max = Math.max(max,pre);
        }
        return max;
    }

}
