package dcgpt.class04;

/**
 * @Author: LiuXingKun
 * @Date: 2023/4/4 15:13
 */
public class Code03_SubMatrixMaxSum {
    //技巧：就是0-0行，0-1行，0-2行，0-3行，压缩对比，1-1行，1-2行，1-3行一样
    //比如0-0行就是一个数组求最大子数组累加和，0-1行就是0行和1行两行加在一起成为一行再求子数组最大累加和
    //循环下去最大累加和矩阵的值就在其中
    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        // O(N^2 * M)
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            // i~j
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    s[k] += m[j][k];
                }
                max = Math.max(max, maxSubArray(s));
            }
        }
        return max;
    }

    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }


    public static int maxSum2(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int N = arr.length;
        int M = arr[0].length;
        for (int i = 0; i < N; i++) {
            int[] s = new int[M];
            for (int j = i; j < M; j++) {
                for (int k = 0; k < M; k++) {
                    s[k] += arr[j][k];
                }
                max = Math.max(max, maxSubArray2(s));
            }
        }
        return max;
    }

    private static int maxSubArray2(int[] arr) {
        if (arr == null || arr.length < 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;

    }

    private static int maxSubArray3(int[] arr) {
        if (arr == null || arr.length < 0) {
            return 0;
        }
        int max = arr[0];
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            //这里要用当前数arr[i]和前面的累加和(arr[i] + pre)对比，谁大用谁
            pre = Math.max(arr[i], arr[i] + pre);
            max = Math.max(max, pre);
        }
        return max;
    }


    // 本题测试链接 : https://leetcode-cn.com/problems/max-submatrix-lcci/
    public static int[] getMaxMatrix(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int i = 0; i < N; i++) {
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                cur = 0;
                int begin = 0;
                for (int k = 0; k < M; k++) {
                    s[k] += m[j][k];
                    cur += s[k];
                    if (max < cur) {
                        max = cur;
                        a = i;
                        b = begin;
                        c = j;
                        d = k;
                    }
                    if (cur < 0) {
                        cur = 0;
                        begin = k + 1;
                    }
                }
            }
        }
        return new int[]{a, b, c, d};
    }

    public static void main(String[] args) {
        int[] arr = {2, -1, 3};
        int i = maxSubArray3(arr);
    }
}
