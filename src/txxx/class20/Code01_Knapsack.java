package txxx.class20;

import java.util.ArrayList;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/12 20:56
 */
public class Code01_Knapsack {
    //w是总量，v是价值，bag是总量，不能超过这个总量，能拿到最大的价值是多少。
    public static int maxValue1(int[] w, int[] v, int bag) {
        if (w == null || v == null || bag < 0) {
            return -1;
        }
        return process1(w, v, 0, bag);
    }

    //这个index代表下标
    //先考虑base case就是归来的情况。
    //当index == w.length就结束了，越界了，返回为0
    //当rest剩余的价值小于0的时候，就证明是失效的情况了。比如w是7，v是15，bag是6，当拿到7的时候，6-7得到-1，那么就是失效的，不可以用这个7
    //然后就剩下两张情况可以选择了：
    //1.当前index的值不拿。
    //2.当前index的值拿。
    private static int process1(int[] w, int[] v, int index, int rest) {
        if (index == w.length) {
            return 0;
        }
        if (rest < 0) {
            return -1;
        }
        int p1 = process1(w, v, index + 1, rest);//当前index的值不拿
        int p2 = 0;
        //当前index的值要拿
        if ((rest - w[index]) >= 0) {
            p2 = v[index] + process1(w, v, index + 1, rest - w[index]);
        }
        return Math.max(p1, p2);
    }

    //老师写的
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数！
        return process(w, v, 0, bag);
    }

    // index 0~N
    // rest 负~bag
    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }


    //dp
    //从暴力递归想动态规划怎么写
    //暴力递归中只是有index和bag的值变化了，所以创建一个w.length+1和bag+1的二维数组
    //然后主函数process的index都是依赖子函数process的index+1，所以只要index+1的值知道了，index的值就能知道
    //再看base case中index == w.length得到最后一行是0，rest < 0是-1，就是在0到rest范围值是有效，小于0的范围无效
    //最后要得到的答案也在process(w, v, 0, bag)中可以得出是要dp[0][bag]
    public static int dp(int[] w, int[] v, int bag) {
        int n = w.length;
        int[][] dp = new int[n + 1][bag + 1];
        for (int index = n - 1; index >= 0; index--) {//因为index的值依赖index+1，而且知道第n行的数据是0，所有从n-1开始算
            for (int rest = 0; rest <= bag; rest++) {//rest为剩余重量
//                int p1 = process(w, v, index + 1, rest);直接复制
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                //当前index的值要拿
                if ((rest - w[index]) >= 0) {
//                    p2 = v[index] + process1(w,v,index +1,rest-w[index]);
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1, p2);//把值存进二维数组
            }
        }
        return dp[0][bag];
}


    public static void main(String[] args) {
/*        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue1(weights, values, bag));
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));*/


        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("21");
        strings.remove("2");
        System.out.println(strings.toString());
    }


}
