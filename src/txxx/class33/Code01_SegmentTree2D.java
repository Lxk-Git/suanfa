package txxx.class33;


//现在换成2维的

public class Code01_SegmentTree2D {

    //0位置是不用的
    public static class IndexTree2D {
        public int[][] tree;//indexTree二维的
        public int[][] nums;//他是用来备用的
        public int n;
        public int m;

        //输入一个二维数组
        public IndexTree2D(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            n = matrix.length;
            m = matrix[0].length;
            tree = new int[n + 1][m + 1];
            nums = new int[n][m]; //nums初始化的时候，所以位置都是0；
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    add(i, j, matrix[i][j]);
                }
            }

        }

        public int sum(int row, int col) {
            int res = 0;
            for (int i = row + 1; i > 0; i -= i & (-i)){
                for (int j = row + 1; j > 0; j -= j & (-j)){
                    res += tree[i][j];
                }
            }
            return res;
        }

        public void add(int row, int col, int val) {
            if (n == 0 || m == 0) {
                return;
            }
            //那个位置的值变了
            int add1 = val - nums[row][col];
            nums[row][col] = val;
            //一个位置的值变化了，会影响到那些位置的值？
            for (int i = row + 1; i <= n; i += i & (-i)) {
                for (int j = col + 1; j < m; j += j & (-j)) {
                    //被影响的位置全部都要加上add1.
                    tree[i][j] += add1;
                }
            }
        }
        //算二维数组中tree[row1][col1]到tree[row2][col2]的值
        //就是sum(row2, col2)的和减去sum(row1 - 1, col2)左边，减去sum(row2, col1 - 1)上边，但是多减去了sum(row1 - 1, col1 - 1)，再加回来就好了
        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (n == 0 || m == 0) {
                return 0;
            }
            return sum(row2, col2) - sum(row1 - 1, col2) - sum(row2, col1 - 1) + sum(row1 - 1, col1 - 1);
        }
    }




}
