package dcgpt.class05;

import com.sun.deploy.util.StringUtils;

/**
 * @Author: LiuXingKun
 * @Date: 2023/4/8 13:11
 */
public class Code03_EditCost {
    public static int minCost1(String s1, String s2, int ac, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length + 1;
        int M = str2.length + 1;
        int[][] dp = new int[N][M];
        // dp[0][0] = 0
        //i是行，j是列
        for (int i = 1; i < N; i++) {
            dp[i][0] = dc * i;
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = ac * j;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = dp[i - 1][j - 1] + (str1[i - 1] == str2[j - 1] ? 0 : rc);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ac);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[N - 1][M - 1];
    }
    public static int minCost2(String s1, String s2, int ac, int dc, int rc) {
        if(s1 == null || s2 == null){
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N= str1.length;
        int M = str2.length;

        int[][] dp = new int[N+1][M+1];
        dp[0][0] = 0;
        for(int i =1;i<=N;i++){
            dp[i][0] = dc * i;
        }
        for(int j =1;j<=M;j++){
            dp[0][j] = ac * j;
        }

        for(int i =1;i<=N;i++){
            for(int j=1;j<=M;j++){
                //这里有4种可能，要选择付出代价最小的
                if(str1[i-1]==str2[j-1]){
                    dp[i][j] =dp[i-1][j-1];
                }else {
                    dp[i][j] =dp[i-1][j-1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ac);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[N][M];
    }
    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost1(str1, str2, 5, 3, 2));
        System.out.println(minCost2(str1, str2, 5, 3, 2));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 3, 2, 4));
        System.out.println(minCost2(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 1, 7, 5));
        System.out.println(minCost2(str1, str2, 1, 7, 5));

        str1 = "abcdf";
        str2 = "";
        System.out.println(minCost1(str1, str2, 2, 9, 8));
        System.out.println(minCost2(str1, str2, 2, 9, 8));

    }

}
