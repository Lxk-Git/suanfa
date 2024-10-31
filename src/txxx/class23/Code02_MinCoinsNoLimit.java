package txxx.class23;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/19 9:14
 */
public class Code02_MinCoinsNoLimit {
    public static int minCoins(int[] arr,int aim){
        return process(arr,0,aim);
    }
    //尝试方法就是取第一张，第二张，第三张一个个去尝试，比如第一次可以选所有张，但是选了第一张，第二次可以选所有张，就是这样尝试，最小的值就是答案
    //先看base case 当index == arr.length的时候，看aim是不是等于0了，是的话就不用在加张数了，不是就设置成无穷大
    //然后就是对index每一个都尝试，尝试可以弄多少张，加上每一张有个条件，就是zhang*arr[index]<=rest(剩余的)，不符合就是无穷大张
    //当前index选了zhang张，看他下面的还能选多少张，得到next,如果是可行的结果，next就不会是Integer.MAX_VALUE，就用zhang加上next
    //再对拿到的结果和min做比较，就会得到最小的min
    private static int process(int[] arr, int index, int rest) {
        if(index == arr.length){
            return rest ==0 ? 0 : Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for(int zhang = 0;zhang*arr[index]<=rest;zhang++){
            int next = process(arr,index+1,rest-(zhang*arr[index]));
            if(next!=Integer.MAX_VALUE){
                min= Math.min(min,(zhang+next));
            }
        }
        return min;
    }
    //dp，就改成有for的dp
    //很明显，带两个可变参数
    public static int dp(int[] arr,int aim){
        int N= arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 0;
        for(int i = 0;i<=aim;i++){
            dp[N][i] = Integer.MAX_VALUE;
        }
        for(int index = N-1;index>=0;index--){
            for(int rest = 0;rest<=aim;rest++){
                int min = Integer.MAX_VALUE;
                for(int zhang = 0;zhang*arr[index]<=rest;zhang++){
                    int next = dp[index+1][rest-(zhang*arr[index])];
                    if(next!=Integer.MAX_VALUE){
                        dp[index][rest]= Math.min(min,(zhang+next));
                    }
                }
            }
        }
        return dp[0][aim];
    }




    public static void main(String[] args) {
        int[] arr = {10,5};
        int i = minCoins(arr, 20);
        int i2 = dp(arr,20);
        System.out.println(i);
        System.out.println(i2);

    }
}
