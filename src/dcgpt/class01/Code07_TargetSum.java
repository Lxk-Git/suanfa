package dcgpt.class01;

import java.util.HashMap;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/1 21:50
 */
public class Code07_TargetSum {
    public static int targetSum1(int[] arr, int target) {
        return process1(arr, 0, target);
    }

    private static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        //加上一个数                                          减去一个数
        return process1(arr, index + 1, rest - arr[index]) + process1(arr, index + 1, rest + arr[index]);
    }

    //记忆化搜索，傻缓存，需要加一个hashMap
    public static int targetSum2(int[] arr, int target) {
        return process2(arr, 0, target, new HashMap<>());
    }

    //HashMap<Integer,HashMap<Integer,Integer>>
    // dp弄成这样是因为：第一个Integer是index,第二个Integer是rest,第三个Integer是rest-arr[index]加上rest+arr[index]
    private static int process2(int[] arr, int index, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp) {
        //有index，且有rest,直接返回dp里面的数据
        if (dp.containsKey(index) && dp.get(index).containsKey(rest)) {
            return dp.get(index).get(rest);
        }
        //不在缓存里面
        int ans = 0;
        //加上一个数                                          减去一个数
        ans = process2(arr, index + 1, rest - arr[index], dp) + process2(arr, index + 1, rest + arr[index], dp);
        //不在缓存中，把结果加入缓存
        if (!dp.containsKey(index)) {
            dp.put(index, new HashMap<>());
        }
        dp.get(index).put(rest, ans);
        //再返回结果
        return ans;
    }

    // 优化点一 :
    // 你可以认为arr中都是非负数
    // 因为即便是arr中有负数，比如[3,-4,2]
    // 因为你能在每个数前面用+或者-号
    // 所以[3,-4,2]其实和[3,4,2]达成一样的效果
    // 那么我们就全把arr变成非负数，不会影响结果的
    // 优化点二 :
    // 如果arr都是非负数，并且所有数的累加和是sum
    // 那么如果target<sum，很明显没有任何方法可以达到target，可以直接返回0
    // 优化点三 :
    // arr内部的数组，不管怎么+和-，最终的结果都一定不会改变奇偶性
    // 所以，如果所有数的累加和是sum，
    // 并且与target的奇偶性不一样，没有任何方法可以达到target，可以直接返回0
    // 优化点四 :
    // 比如说给定一个数组, arr = [1, 2, 3, 4, 5] 并且 target = 3
    // 其中一个方案是 : +1 -2 +3 -4 +5 = 3
    // 该方案中取了正的集合为P = {1，3，5}
    // 该方案中取了负的集合为N = {2，4}
    // 所以任何一种方案，都一定有 sum(P) - sum(N) = target
    // 现在我们来处理一下这个等式，把左右两边都加上sum(P) + sum(N)，那么就会变成如下：
    // sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
    // 2 * sum(P) = target + 数组所有数的累加和
    // sum(P) = (target + 数组所有数的累加和) / 2
    // 也就是说，任何一个集合，只要累加和是(target + 数组所有数的累加和) / 2
    // 那么就一定对应一种target的方式
    // 也就是说，比如非负数组arr，target = 7, 而所有数累加和是11
    // 求有多少方法组成7，其实就是求有多少种达到累加和(7+11)/2=9的方法
    // 优化点五 :
    // 二维动态规划的空间压缩技巧
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum < target || ((sum & 1) ^ (target & 1)) != 0 ? 0 : subset2(nums, ((sum + target) >> 1));
    }

    //先从递归开始,类似背包问题
    private static int subset2(int[] arr, int rest) {
        return process3(arr, 0, rest);

    }

    private static int process3(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        return process3(arr, index + 1, rest) + process3(arr, index + 1, rest - arr[index]);
    }


    public static int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum < target || ((sum & 1) ^ (target & 1)) != 0 ? 0 : subset3(nums, ((sum + target) >> 1));
    }

    private static int subset3(int[] arr, int rest) {
        if (arr == null || arr.length < 0 || rest < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][rest + 1];
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= rest; j++) {
                dp[i][j] = -1;
            }
        }
        return process4(arr, 0, rest, dp);
    }

    private static int process4(int[] arr, int index, int rest, int[][] dp) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        int ans = process4(arr, index + 1, rest, dp) + process4(arr, index + 1, rest - arr[index], dp);
        dp[index][rest] = ans;
        return ans;
    }

    //dp
    public static int findTargetSumWays3(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum < target || ((sum & 1) ^ (target & 1)) != 0 ? 0 : subset4(nums, ((sum + target) >> 1));
    }

    private static int subset4(int[] arr, int k) {
        if (k < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][k + 1];
        dp[N][0] = 1;
        for (int i = 1; i <= k; i++) {
            dp[N][i] = 0;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; k >= rest; rest++) {
                dp[index][rest] = dp[index + 1][rest];//不能写在下面的if中是因为dp[index+1][rest]和rest-arr[index]>=0这个条件无关
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index + 1][rest - arr[index]];
                }
            }
        }
        return dp[0][k];
    }

    //空间压缩
    public static int findTargetSumWays4(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum < target || ((sum & 1) ^ (target & 1)) != 0 ? 0 : subset5(nums, ((sum + target) >> 1));
    }
    //就用一个k列的数组
    private static int subset5(int[] arr, int k) {
        if (k < 0) {
            return 0;
        }
        int[] dp = new int[k + 1];
        //k==0的时候是一种方法
        dp[0] = 1;
        for(int index = arr.length-1;index>=0;index--){
            //用j 也就是rest减去每次arr进来的数值，加上本身dp[j]就得到新的dp[j]
            for(int j = k;j>=arr[index];j--){//j>=arr[index]才是有效的，这个时候dp[j]加上p[j-arr[index]]才行
                dp[j] += dp[j-arr[index]];
            }
        }
        return dp[k];
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1};
        int i = subset5(arr, 4);
        System.out.println(i);
    }


}
