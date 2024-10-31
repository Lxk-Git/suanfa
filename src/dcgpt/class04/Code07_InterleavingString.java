package dcgpt.class04;

/**
 * @Author: LiuXingKun
 * @Date: 2023/4/6 16:34
 */
// 本题测试链接 : https://leetcode.com/problems/interleaving-string/
public class Code07_InterleavingString {
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        if (str3.length != str1.length + str2.length) {
            return false;
        }
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str1.length; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int j = 1; j <= str2.length; j++) {
            if (str2[j - 1] != str3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (
                        //就是当str1[i - 1] == str3[i + j - 1]时候，还要看上一步的时候在dp[i - 1][j]中是T还是F，因为依赖上一步的值
                        //因为str1和str2是交叉的，所以有两种情况
                        (str1[i - 1] == str3[i + j - 1] && dp[i - 1][j])
                                ||
                                (str2[j - 1] == str3[i + j - 1] && dp[i][j - 1])
                ) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length][str2.length];
    }

    public static boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        if (str3.length != str1.length + str2.length) {
            return false;
        }
        boolean[][] dp = new boolean[str1.length+1][str2.length+1];
        dp[0][0] =true;//一定是true，因为第一个不是str1的就是str2的
        //现在处理str1作为dp的列,默认是false,所以不相等直接break就好了。
        for (int i = 1; i <= str1.length; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        //处理str2作为dp的行
        for (int j = 1; j <= str2.length; j++) {
            if (str2[j - 1] != str3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }

        for(int i =1;i<=str1.length;i++){
            for(int j =1;j<=str2.length;j++){
                if(
                        (str1[i-1] ==str3[i+j-1]&&dp[i-1][j])
                ||
                                (str2[j-1] ==str3[i+j-1]&&dp[i][j-1])
                ){
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length][str2.length];
    }



}
