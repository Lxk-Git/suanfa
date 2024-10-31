package dcgpt.class01;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/1 22:25
 */
public class Code05_LongestIncreasingPath {
    public static int path1(int[][] matrix){
        int ans = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        for(int i=0;i<row;i++){
            for(int j = 0;j<col;j++){
                ans = Math.max(ans,process1(matrix,i,j));
            }
        }
        return ans;
    }

    private static int process1(int[][] m, int i, int j) {
        //上下左右四个路走
        //向上，不能越界，并且往那个地方走的数值要大于当前的数值
        int up = i > 0 && m[i][j] < m[i - 1][j] ? process1(m, i - 1, j) : 0;
        int down = i < (m.length - 1) && m[i][j] < m[i + 1][j] ? process1(m, i + 1, j) : 0;
        int left = j > 0 && m[i][j] < m[i][j - 1] ? process1(m, i, j - 1) : 0;
        int right = j < (m[0].length - 1) && m[i][j] < m[i][j + 1] ? process1(m, i, j + 1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    //记忆化搜索
    public static int path2(int[][] matrix){
        int ans = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j = 0;j<col;j++){
                ans = Math.max(ans,process2(matrix,i,j,dp));
            }
        }
        return ans;
    }

    private static int process2(int[][] m, int i, int j, int[][] dp) {
        //初始化就是0，如果不为零说明有过值
        if(dp[i][j] !=0){
            return dp[i][j];
        }
        //是0，就走
        int ans = 0;
        int up = i > 0 && m[i][j] < m[i - 1][j] ? process2(m, i - 1, j,dp) : 0;
        int down = i < (m.length - 1) && m[i][j] < m[i + 1][j] ? process2(m, i + 1, j,dp) : 0;
        int left = j > 0 && m[i][j] < m[i][j - 1] ? process2(m, i, j - 1,dp) : 0;
        int right = j < (m[0].length - 1) && m[i][j] < m[i][j + 1] ? process2(m, i, j + 1,dp) : 0;
        ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        //存进缓存里面
        dp[i][j] = ans;
        return ans;
    }
}
