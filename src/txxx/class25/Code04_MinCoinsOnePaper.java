package txxx.class25;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/22 13:19
 */
public class Code04_MinCoinsOnePaper {
    public static int minCoins(int[] arr,int num){
        if(arr == null || arr.length<1||num<1){
            return 0;
        }
        return process(arr,0,num);
    }

    private static int process(int[] arr, int index, int rest) {
        //如果rest<0那就说明这种方法无效
        if(rest<0){
            return Integer.MAX_VALUE;
        }
        if(index == arr.length){
            return rest == 0 ? 0 : Integer.MAX_VALUE;//rest==0说明不要了，不然就是无效
        }
        int p1 = process(arr,index+1,rest);
        int p2 = process(arr,index+1,rest-arr[index]);
        if(p2!= Integer.MAX_VALUE){
            p2++;
        }
        return Math.min(p1,p2);
    }


    //dp要注意rest<0的条件可以用for循环里面的if(rest>=arr[index])代替

    public static int dp(int[] arr,int num){
        if(arr == null || arr.length<1||num<1){
            return 0;
        }
        int N= arr.length;
        int[][] dp = new int[N+1][num+1];
        dp[N][0] = 0;
        for(int i=1;i<=num;i++){
            dp[N][i] = Integer.MAX_VALUE;
        }

        for(int index = N-1;index>=0;index--){
            for(int rest=0;rest<=num;rest++){
                int p1 = dp[index+1][rest];
                int p2 = Integer.MAX_VALUE;
                if(rest>=arr[index]){
                    p2 = dp[index+1][rest-arr[index]];
                }
                if(p2!= Integer.MAX_VALUE){
                    p2++;
                }
                dp[index][rest]= Math.min(p1,p2);
            }
        }
        return dp[0][num];
    }

    // 为了测试
    public static int[] randomArray(int N, int maxValue) {
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
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2 ) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");

    }
}
