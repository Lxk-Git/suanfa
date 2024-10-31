package txxx.class39;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/28 17:10
 */
public class Code04_MoneyProblem {

    // int[] d d[i]：i号怪兽的武力
    // int[] p p[i]：i号怪兽要求的钱
    // ability 当前你所具有的能力
    // index 来到了第index个怪兽的面前

    // 目前，你的能力是ability，你来到了index号怪兽的面前，如果要通过后续所有的怪兽，
    // 请返回需要花的最少钱数
    public static long process1(int[] d, int[] p, int ability, int index) {
        if (index == d.length) {
            return 0;
        }
        if (ability < d[index]) {
            return p[index] + process1(d, p, ability + d[index], index + 1);
        } else { // ability >= d[index] 可以贿赂，也可以不贿赂
            return Math.min(
                    p[index] + process1(d, p, ability + d[index], index + 1),
                    0 + process1(d, p, ability, index + 1));
        }
    }

    public static long func1(int[] d, int[] p) {
        return process1(d, p, 0, 0);
    }


    public static long func11(int[] d, int[] p) {
        return process11(d, p, 0, 0);
    }

    //如果怪兽的个数不是很多，武力值的总额也不是很大，那么就能用怪兽武力做j,怪兽个数做i，创建一个dp二维表
    //题目要求是个人的能力比怪兽的武力值大就可以贿赂，也可以不贿赂，他的武力值就变成了个人的能力。比怪兽武力小就一定要贿赂，怪兽的武力就变成了人的能力。这里最关键的变量就是怪兽的武力。
    //这个暴力递归就容易了，一共2个大情况，大情况一：如果能力小于怪兽武力，没办法只能花钱贿赂。大情况二：如果能力大于怪兽武力，可以贿赂也可以不贿赂，但是到底是贿赂还是不贿赂得看最后是不是花的钱最小。
    //情况二举个例子：
    //   d[5,2,3,10]
    //   p[4,1,2,6]
    //     0,1,2,3
    //第,0位肯定是要贿赂的，花了4，第1和2可以贿赂或者不贿赂，但是如果不贿赂，要打最后一个能力10的怪兽就要话6，但是第1和2贿赂了，加起来就有10了，比起前面的方法花了4+6=10，后面4+1+2=7是少了的。
    private static long process11(int[] d, int[] p, int index, int ability) {
        //base case 如果到最后一个了，说明不要花钱了
        if (index == d.length) {
            return 0;
        }
        if (ability < d[index]) {
            return p[index] + process11(d, p, index + 1, ability + d[index]);
        } else {//可以贿赂也可以不贿赂，但是要选择最好得到结果最小的
            return Math.min((p[index] + process11(d, p, index + 1, ability + d[index])),//贿赂
                    process11(d, p, index + 1, ability));//不贿赂
        }
    }


    //暴力递归修改为动态规划
    public static long dp11(int[] d, int[] p) {
        int sum = 0;
        for (int num : d) {
            sum += num;
        }
        int[][] dp = new int[d.length + 1][sum + 1];
        for (int index = d.length - 1; index >= 0; index++) {
            for (int ability = 0; ability <= sum; ability++) {
                // 如果这种情况发生，那么这个hp必然是递归过程中不会出现的状态
                // 既然动态规划是尝试过程的优化，尝试过程碰不到的状态，不必计算
                if (ability + d[index] > sum) {
                    continue;
                }
                if(ability<d[index]){
                    dp[index][ability] = p[index] + dp[index+1][ability+d[index]];
                }else {
                    dp[index][ability] = Math.min((p[index] + dp[index+1][ability+d[index]]),(p[index] + dp[index+1][ability]));
                }
            }
        }
        return dp[0][0];
    }

    // 从0....index号怪兽，花的钱，必须严格==money
    // 如果通过不了，返回-1
    // 如果可以通过，返回能通过情况下的最大能力值
    public static long process2(int[] d, int[] p, int index, int money) {
        if (index == -1) { // 一个怪兽也没遇到呢
            return money == 0 ? 0 : -1;
        }
        // index >= 0
        // 1) 不贿赂当前index号怪兽
        long preMaxAbility = process2(d, p, index - 1, money);
        long p1 = -1;
        if (preMaxAbility != -1 && preMaxAbility >= d[index]) {
            p1 = preMaxAbility;
        }
        // 2) 贿赂当前的怪兽 当前的钱 p[index]
        long preMaxAbility2 = process2(d, p, index - 1, money - p[index]);
        long p2 = -1;
        if (preMaxAbility2 != -1) {
            p2 = d[index] + preMaxAbility2;
        }
        return Math.max(p1, p2);
    }

    public static int minMoney2(int[] d, int[] p) {
        int allMoney = 0;
        for (int i = 0; i < p.length; i++) {
            allMoney += p[i];
        }
        int N = d.length;
        for (int money = 0; money < allMoney; money++) {
            if (process2(d, p, N - 1, money) != -1) {
                return money;
            }
        }
        return allMoney;
    }

    public static long func2(int[] d, int[] p) {
        int sum = 0;
        for (int num : d) {
            sum += num;
        }
        long[][] dp = new long[d.length + 1][sum + 1];
        for (int cur = d.length - 1; cur >= 0; cur--) {
            for (int hp = 0; hp <= sum; hp++) {
                // 如果这种情况发生，那么这个hp必然是递归过程中不会出现的状态
                // 既然动态规划是尝试过程的优化，尝试过程碰不到的状态，不必计算
                if (hp + d[cur] > sum) {
                    continue;
                }
                if (hp < d[cur]) {
                    dp[cur][hp] = p[cur] + dp[cur + 1][hp + d[cur]];
                } else {
                    dp[cur][hp] = Math.min(p[cur] + dp[cur + 1][hp + d[cur]], dp[cur + 1][hp]);
                }
            }
        }
        return dp[0][0];
    }

    public static long func3(int[] d, int[] p) {
        int sum = 0;
        for (int num : p) {
            sum += num;
        }
        // dp[i][j]含义：
        // 能经过0～i的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 如果dp[i][j]==-1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
        int[][] dp = new int[d.length][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        // 经过0～i的怪兽，花钱数一定为p[0]，达到武力值d[0]的地步。其他第0行的状态一律是无效的
        dp[0][p[0]] = d[0];
        for (int i = 1; i < d.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // 可能性一，为当前怪兽花钱
                // 存在条件：
                // j - p[i]要不越界，并且在钱数为j - p[i]时，要能通过0～i-1的怪兽，并且钱数组合是有效的。
                if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }
                // 可能性二，不为当前怪兽花钱
                // 存在条件：
                // 0~i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
                if (dp[i - 1][j] >= d[i]) {
                    // 两种可能性中，选武力值最大的
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        int ans = 0;
        // dp表最后一行上，dp[N-1][j]代表：
        // 能经过0～N-1的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 那么最后一行上，最左侧的不为-1的列数(j)，就是答案
        for (int j = 0; j <= sum; j++) {
            if (dp[d.length - 1][j] != -1) {
                ans = j;
                break;
            }
        }
        return ans;
    }

    public static int[][] generateTwoRandomArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] arrs = new int[2][size];
        for (int i = 0; i < size; i++) {
            arrs[0][i] = (int) (Math.random() * value) + 1;
            arrs[1][i] = (int) (Math.random() * value) + 1;
        }
        return arrs;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 20;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generateTwoRandomArray(len, value);
            int[] d = arrs[0];
            int[] p = arrs[1];
            long ans1 = func1(d, p);
            long ans2 = func2(d, p);
            long ans3 = func3(d, p);
            long ans4 = minMoney2(d, p);
            if (ans1 != ans2 || ans2 != ans3 || ans1 != ans4) {
                System.out.println("oops!");
            }
        }

    }

}
