package dcgpt.class02;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/18 22:35
 */
public class Code04_Drive {
    //动态规划问题，就是A点和B点的司机都是N人，然后一个N*2二维数字，去A点司机有多少钱，去B点司机有多少钱

    // income -> N * 2 的矩阵 N是偶数！
    // 0 [9, 13]
    // 1 [45,60]
    public static int maxMoney1(int[][] income) {
        if (income == null || income.length < 1 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length;
        //每个点能去的司机数目
        int M = N >> 1;
        //index从0到N,M表示去A点的司机(这个很重要！)
        return process1(income, 0, M);
    }


    private static int process1(int[][] income, int index, int rest) {
        //base case
        //index到了N，说明没有可以选的司机了
        if (index == income.length) {
            return 0;
        }
        //还剩下司机
        //A点司机最大能挣多少，
        //B点已经满了，A点还有位置（rest == income.length - index，这个情况才能说明B已经满了）,那么去A点一个人，rest就要减去一个
        if (rest == income.length - index) {
            return income[index][0] + process1(income, index + 1, rest - 1);
        }
        //B点司机最大能挣多少，
        //A点已经满了，去B点，rest一直都是0，不会变化
        if (rest == 0) {
            return income[index][1] + process1(income, index + 1, rest);
        }
        //A点，B点都能去的情况
        int p1 = income[index][0] + process1(income, index + 1, rest - 1);
        int p2 = income[index][1] + process1(income, index + 1, rest);
        //选最大的
        return Math.max(p1, p2);
    }

    //改傻缓存
    public static int maxMoney2(int[][] income) {
        if (income == null || income.length < 1 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length;
        //每个点能去的司机数目
        int M = N >> 1;
        //index从0到N,M表示去A点的司机(这个很重要！)
        int[][] dp = new int[N + 1][M + 1];
        return process2(income, 0, M, dp);
    }

    private static int process2(int[][] income, int index, int rest, int[][] dp) {
        if (dp[index][rest] != 0) {
            return dp[index][rest];
        }
        if (index == income.length) {
            return 0;
        }
        if (rest == income.length - index) {
            return income[index][0] + process2(income, index + 1, rest - 1, dp);
        }

        if (rest == 0) {
            return income[index][1] + process2(income, index + 1, rest, dp);
        }
        int p1 = income[index][0] + process2(income, index + 1, rest - 1, dp);
        int p2 = income[index][1] + process2(income, index + 1, rest, dp);

        dp[index][rest] = Math.max(p1, p2);
        return dp[index][rest];
    }

    //动态规划
    public static int maxMoney3(int[][] income) {
        if (income == null || income.length < 1 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length;
        //每个点能去的司机数目
        int M = N >> 1;
        //index从0到N,M表示去A点的司机(这个很重要！)
        int[][] dp = new int[N + 1][M + 1];
        //N的情况已经在base Case里面清楚了，所以最多从N-1开始
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= M; rest++) {
                if (rest == N - index) {
                    dp[index][rest] = income[index][0] + dp[index + 1][rest - 1];
                } else if (rest == 0) {
                    dp[index][rest] = income[index][1] + dp[index + 1][rest];
                } else {
                    int p1 = income[index][0] + dp[index + 1][rest - 1];
                    int p2 = income[index][1] + dp[index + 1][rest];
                    dp[index][rest] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][M];
    }


    // 返回随机len*2大小的正数矩阵
    // 值在0~value-1之间
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            int ans2 = maxMoney2(matrix);
            int ans3 = maxMoney3(matrix);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
