package dcgpt.class03;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/21 15:16
 */
// 本题测试链接 : https://leetcode.com/problems/largest-1-bordered-square/
public class Code03_Largest1BorderedSquare {

    public static int largest1BorderedSquare(int[][] m) {
        //把从右向左累加和做一个前缀和的二维数组
        int[][] right = new int[m.length][m[0].length];
        //把从下向上累加和做一个前缀和的二维数组
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
        //要的是正方形，那么肯定要的是短的那条边
        for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
            //要每个正方形都要遍历到，就是一共是3*3的正方形，要1*3,2*3,3*3,1*2,2*2,3*2,1*1,2*1,3*1的都要遍历到
            //再看前缀和中right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size && down[i][j + size - 1] >= size，就说明符合条件
            if (hasSizeOfBorder(size, right, down)) {
                return size * size;
            }
        }
        return 0;
    }

    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        for (int i = r - 2; i != -1; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for (int i = c - 2; i != -1; i--) {
            if (m[r - 1][i] == 1) {
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }
        for (int i = r - 2; i != -1; i--) {
            for (int j = c - 2; j != -1; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }



    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size
                        && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }


    public static int largest1BorderedSquare1(int[][] m) {
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBorderMap1(m, right, down);
        for (int size = Math.min(m.length, m[0].length); size > 0; size--) {
            if (hasSizeOfBorder1(size, right, down)) {
                return size * size;
            }
        }

        return 0;
    }
    private static void setBorderMap1(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        for (int i = r - 2; i >= 0; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for (int i = c - 2; i >= 0; i--) {
            if (m[r - 1][i] == 1) {
                down[r - 1][i] = 1;
                right[r - 1][i] = right[r - 1][i + 1] + 1;
            }
        }
        for (int i = r - 2; i >= 0; i--) {
            for (int j = c - 2; j >= 0; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }
    private static boolean hasSizeOfBorder1(int size, int[][] right, int[][] down) {
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size && down[i][size + j - 1] >= size && right[i + size - 1][j] >= size) {
                    return true;
                }
            }
        }
        return false;
    }
}
