package txxx.class23;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/17 10:39
 */
public class Code05_BobDie {


    public static double livePosibility1(int row, int col, int rest, int N, int M) {
        return process1(row, col, rest, N, M) / Math.pow(4, rest);//因为在格子中有4个方向可以走，剩余rest步
    }

    private static double process1(int row, int col, int rest, int n, int m) {
        //越界
        if (row < 0 || row == n || col < 0 || col == m) {
            return 0;
        }
        //没有越界，步数为0了
        if (rest == 0) {
            return 1;
        } else {
            double left = process1(row, col - 1, rest - 1, n, m);
            double right = process1(row, col + 1, rest - 1, n, m);
            double up = process1(row - 1, col, rest - 1, n, m);
            double down = process1(row + 1, col, rest - 1, n, m);
            return left + right + up + down;
        }
    }

    //dp  三个可变参数，rest依赖rest-1,那就是一层一层来填写
    public static double dp(int row, int col, int rest, int N, int M) {

        double[][][] dp = new double[N][M][rest + 1];
        for (int r = 0; r < N; r++) {//在格子中的任何地方只要rest是0都是一个成功的一步
            for (int c = 0; c < M; c++) {
                dp[r][c][0] = 1;
            }
        }
        for (int k = 1; k <= rest; k++) {//是从第0层开始往上的，所以这里要把rest放在最外面，每一层每一层先填完
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    double left = f(dp, i, j - 1, k - 1, N, M);
                    double right = f(dp, i, j + 1, k - 1, N, M);
                    double up = f(dp, i - 1, j, k - 1, N, M);
                    double down = f(dp, i + 1, j, k - 1, N, M);
                    dp[i][j][k] = left + right + up + down;
                }
            }
        }
        return dp[row][col][rest] / Math.pow(4, rest);
    }

    public static double f(double[][][] dp, int row, int col, int rest, int N, int M) {
        //越界
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        return dp[row][col][rest];
    }
    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(dp(6, 6, 10, 50, 50));

    }

}
