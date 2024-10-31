package txxx.class19;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/11 22:21
 */
public class Code01_RobotWalk {
    //4个参数，M是机器人在的位置，P是最后要停留的位置，K是一共能走多是步，N是一行有多少个位置。
    public static int ways1(int M,  int K,int P, int N) {
        if (M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return -1;
        }
        return process1(M,  K,P, N);
    }

    //先看递归结束的条件，就是当K==0的时候，M==P就是一个成功的案例，如果不等于就不是。
    //然后看M是1的时候，那就只能走2，并且只剩下K-1步。
    //同理M是N的时候，那就只能走N-1，也只剩下K-1步。
    //不咋这两位置，可以去M-1，剩下K-1步;可以去M+1,剩下K-1步。
    private static int process1(int M,  int K, int P, int N) {
        if (K == 0) {
            return M == P ? 1 : 0;
        }
        if(M == 1){
            return process1(2,K-1,P,N);
        }
        if(M == N){
            return process1(N-1,K-1,P,N);
        }
        return process1(M-1,K-1,P,N) + process1(M+1,K-1,P,N);
    }

    //找有重复的地方，出现了重复的地方就可以用缓存来优化
    //在这个算法中，只有M和K的值是变化的，然后可以将他们做成二维数组dp
    public static int ways2(int M,  int K,int P, int N) {
        if (M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return -1;
        }
        //因为M能走的范围是1-N，K能走0-K步。都是可以等于N和等于K的
        int[][] dp = new int[N+1][K+1];
        //这里初始化
        for(int i =0;i<=N;i++){
            for(int j=0;j<=K;j++){
                dp[i][j] = -1;//初始化为-1；
            }
        }

        return process2(M,  K,P, N,dp);
    }

    private static int process2(int M,  int K, int P,int N, int[][] dp) {
        if(dp[M][K] !=-1){//如果机器人在的位置M和走过的步数K在缓存表里面，那就证明走过了，直接返回
            return dp[M][K];
        }
        //如果不在缓存表里面就开始算
        int ans = 0;//设置这个是结果
        if(K == 0){
            ans = M == P ? 1 : 0;
        }else if(M == 1){
            ans = process2(2,K-1,P,N,dp);
        }else if(M == N){
            ans = process2(N-1,K-1,P,N,dp);
        }else {
            ans  = process2(M+1,K-1,P,N,dp) + process2(M-1,K-1,P,N,dp);
        }
        dp[M][K] = ans;//把结果存入缓存。
        return ans;
    }

    //第三步,动态规划
    //记忆化搜索DP，从顶向下搜索
    //这个dp的范围是从主函数的参数得来的。
    //在这个dp中每个值的依赖位置可以从暴力递归的代码中得到
    public static int ways3(int m,int k,int p ,int n){
        int[][] dp =new int[n+1][k+1];
        //可以得到初始的时候这个dp中哪个位置为1
        dp[p][0]=1;
        //然后对这个dp中的值进行补充，需要拿到的结果就是dp[m][k]的值
        for(int rest=1;rest<=k;rest++){//避免越界rest从1开始
            //如果是dp第一行的数据，依赖如下,参见暴力递归M=1的时候
            dp[1][rest] = dp[2][rest-1];
            //如果dp不是第一行，也不是第N行的数据，参见暴力递归，得到如下依赖
            //避免越界cur从2开始,不能到n
            for(int cur =2;cur<n;cur++){
                dp[cur][rest] = dp[cur-1][rest-1]+dp[cur+1][rest-1];
            }
            //如过dp是最后N行，参见暴力递归
            dp[n][rest] = dp[n-1][rest-1];
        }
        return dp[m][k];
    }


    public static void main(String[] args) {
        System.out.println(ways1(2,  6,4,5));
        System.out.println(ways2(2,  6,4,5));
        System.out.println(ways3(2,  6,4,5));
    }
}
