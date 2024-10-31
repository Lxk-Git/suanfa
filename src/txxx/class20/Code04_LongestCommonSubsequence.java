package txxx.class20;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/13 16:16
 */
public class Code04_LongestCommonSubsequence {


    public static int longestCommonSubsequence1(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    // str1[0...i]和str2[0...j]，这个范围上最长公共子序列长度是多少？
    // 可能性分类:
    // a) 最长公共子序列，一定不以str1[i]字符结尾、也一定不以str2[j]字符结尾
    // b) 最长公共子序列，可能以str1[i]字符结尾、但是一定不以str2[j]字符结尾
    // c) 最长公共子序列，一定不以str1[i]字符结尾、但是可能以str2[j]字符结尾
    // d) 最长公共子序列，必须以str1[i]字符结尾、也必须以str2[j]字符结尾
    private static int process(char[] str1, char[] str2, int i, int j) {
        //先写边界条件
        //当i和j都是0的时候，这个时候都只有一个，相等就是1，不相等就是0
        if (i == 0 && j == 0) {//情况d
            return str1[i] == str2[j] ? 1 : 0;
        }else if(i==0){//如果i只剩下结尾了，j还有很多  情况b
            if(str1[i] == str2[j]){//结尾的数字和j中一个一样，返回1
                return 1;
            }else {//如果和j不相等，但是和str2[0...j]是不是相等，还要递归下去
                return process(str1, str2,i,j-1);
            }
        }else if(j==0){//情况c
            if(str1[i] == str2[j]){
                return 1;
            }else {
                return process(str1,str2,i-1,j);
            }
        }else {//情况a
            //此时又分为3种情况，
            //第一，i一定不合j相等，i-1
            //第二，j一定不合i相等，j-1
            //第三，i和j都相等1+（i-1,j-1） ，i和j都不相等就是0，
            int p1 = process(str1,str2,i-1,j);
            int p2 = process(str1,str2,i,j-1);
            int p3 = str1[i] == str2[j] ? (1 + process(str1,str2,i-1,j-1)) : 0;
            return Math.max(p1,Math.max(p2,p3));
        }
    }


    //dp
    //dp就跟着递归改就好了
    public static int longestCommonSubsequence2(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n][m];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for(int j = 0;j<m;j++){//列
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j-1];
        }
        for(int i = 0;i<n;i++){//行
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i-1][0];
        }
        for(int i = 1;i<n;i++){
            for(int j = 1;j<m;j++){
                int p1 = dp[i-1][j];
                int p2 = dp[i][j-1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i-1][j-1]) : 0;
                dp[i][j] = Math.max(p1,Math.max(p2,p3));
            }
        }
        return dp[n-1][m-1];
    }

}
