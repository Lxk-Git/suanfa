package txxx.class21;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/15 8:59
 */
public class Code01_PalindromeSubsequence {

    //用的是范围上的尝试模型

    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }

    //当l和r相等的时候，就是只有一个的时候，那肯定是可以回文，得到1 这是base case
    //当l = r-1的时候，还有两个数字，如果l和r相等那就是2，否则就是1；这是base case
    //当l和r都不是回文，那么就可能l+1和r-1是，这就需要递归
    //当l是回文，r不是，那么要递归l和r-1
    //当r时回文，l不是，那么就要递归l+1和r
    //当l和r都是回文，那么就是2加上递归l+1和r-1，如果都不是那就是0，不要加上递归l+1和r-1，加上就和上面重复了，所以可以把第一步去除掉
    //取最大值
    private static int process(char[] str, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l == r - 1) {
            return str[l] == str[r] ? 2 : 1;
        }
        int p1 = process(str, l, r - 1);
        int p2 = process(str, l + 1, r);
        int p3 = str[l] == str[r] ? (2 + (process(str, l + 1, r - 1))) : process(str, l + 1, r - 1);
        return Math.max(p1, Math.max(p2, p3));
    }


    //dp
    //可变参数是l和r,范围很明显就是str.length
    //画图，建立空间感
    //如果L>r一定不行
    public static int dp(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int l = n - 3; n >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                int p1 = dp[l][r - 1];
                int p2 = dp[l + 1][r];
                int p3 = str[l] == str[r] ? (2 + dp[l + 1][r - 1]) : dp[l + 1][r - 1];
                dp[l][r] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[0][n - 1];
    }
}
