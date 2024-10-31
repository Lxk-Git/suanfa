package txxx.class22;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/16 22:19
 */
public class Code03_CoinsWayNoLimit {

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    // arr[index....] 所有的面值，每一个面值都可以任意选择张数，组成正好rest这么多钱，方法数多少？
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) { // 没钱了
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {//重点是zhang* arr[index]<= rest,这样才不越界，才能rest-(zhang * arr[index])
            ways += process(arr, index + 1, rest - (zhang * arr[index]));
        }
        return ways;
    }

    //dp
    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        dp[n][0] = 1;
        for(int index = n-1;index>=0;index--){
            for(int rest = 0;rest<=aim;rest++){
                int ways = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways+= dp[index + 1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        dp[n][0] = 1;
        for(int index = n-1;index>=0;index--){
            for(int rest = 0;rest<=aim;rest++){
                //空间位置优化,这个可以根据画图得到
                if(rest-arr[index]>=0){
                    dp[index][rest] = dp[index+1][rest]+dp[index][rest-arr[index]];
                }
            }
        }
        return dp[0][aim];
    }



}
