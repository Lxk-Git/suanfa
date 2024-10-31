package txxx.class23;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/17 16:55
 */
public class Code01_KillMonster {
    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long kill = process(K, M, N);
        return ((double) kill / (double) all);
    }

    // 返回砍死的情况数！
    public static long process(int times, int M, int hp) {
        if (times == 0) {//能砍的刀数为0了，hp<=0就是一种成功的方案
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {//砍的刀数目还没有到0刀，hp已经小于等于0了，那这个分支下面的所有可能都是成功的方案就有Math.pow(M + 1, times)个，times是还剩下可以砍的刀数
            return (long) Math.pow(M + 1, times);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(times - 1, M, hp - i);//再把所有的可能累加，就是能成功的结果
        }
        return ways;
    }

    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);//注意这里就是对应第22行hp <= 0
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0) {//这里我懂了，和上面分析的一样，如果直接就小于0了，就是else的情况了
                        ways += dp[times - 1][hp - i];
                    } else {
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long kill = dp[K][N];
        return  ((double) kill / (double) all);
    }






    public static void main(String[] args) {
        double right = right(2, 2, 2);
        System.out.println(right);
    }
}
