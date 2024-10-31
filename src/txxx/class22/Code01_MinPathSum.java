package txxx.class22;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/16 14:46
 */
public class Code01_MinPathSum {

    public static int minPathSum12(int[][] m){
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        return process(m,row-1,col-1);

    }
    private static int process(int[][] m, int row, int col) {
        if(row==0&&col==0){
            return m[0][0];
        }
        //只有一行
        if(row==0){
            int ways = 0;
            for(int j = 1; j<=col;j++){
                ways =process(m,row,j-1) +m[0][j];//process(m,row,j-1);代表左边的数据。ways代表他们累加
            }
            return  ways;
        }
        //只有一列
        if(col==0){
            int ways = 0;
            for(int i =1;i<=row;i++){
                ways = m[i][0] + process(m,i-1,col);// process(m,i-1,col)代表上面的数据。ways代表他们累加
            }
            return  ways;
        }
        //不只有一行或者一列
        int min = 0;
        for(int i = 1;i<=row;i++){
            for(int j = 1;j<=col;j++){
                int p1 = process(m,i,j-1);//往下
                int p2 = process(m,i-1,j);//往右边
                min=Math.min(p1,p2)+m[i][j];//谁小用谁，再加上m中的点
            }
        }
        return min;
    }

    public static int minPathSum22(int[][] m){
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for(int j = 1;j<col;j++){
            dp[0][j] = dp[0][j-1] + m[0][j];
        }
        for(int i = 1;i<row;i++){
            dp[i][0] = dp[i-1][0] + m[i][0];
        }

        for(int i =1;i<row;i++){
            for(int j=1;j<col;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+m[i][j];
            }
        }
        return dp[row-1][col-1];
    }

    public static int minPathSum32(int[][] m){
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col];
        dp[0] = m[0][0];
        for(int j = 1;j<col;j++){
            dp[j] = dp[j-1] + m[0][j];
        }
        for(int i =1;i<row;i++){
            dp[0] +=m[i][0];//把每一行的第一个数累加上来做新的dp[0]
            for(int j=1;j<col;j++){
                dp[j]=Math.min(dp[j-1],dp[j]) +m[i][j];
            }
        }
        return dp[col-1];
    }
    public static void main(String[] args) {
        int[][] m1 = {{4,5,10,12},{6,3,15,17},{19,1,21,28},{18,2,1,0}};
        System.out.println(minPathSum22(m1));
        System.out.println(minPathSum12(m1));
        System.out.println(minPathSum32(m1));

    }
}
