package txxx.class16;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/2 17:16
 */
public class Code03_NumberOfIslands {

    //岛问题可以用并查集解决
    //注意点：需要把每个"1"的下标弄出来
    //然后求群到的时候，只要关注为“1”的上面和左边就可以，分为三步，第一，把第一列为“1”的合到一起；第二，把第一行为“1”的合到一起；第三，把剩下的所有是“1”的和前面得到的合并到一起。


    public static class UnionFind {
        private int[] parents;
        private int[] size;
        private int[] help;
        private int col;
        private int sets;

        private UnionFind(char[][] board) {
            col = board[0].length;//列
            int r = board.length;//行
            int len = col * r;//长度
            sets = 0;
            parents = new int[len];
            size = new int[len];
            help = new int[len];
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < r; j++) {
                    if (board[i][j] == '1') {
                        int index = index(i, j);
                        parents[index] = index;
                        size[index] = 1;
                        sets++;//只有为1的时候集合才加
                    }
                }
            }
        }

        private int index(int r, int c) {//把下标弄出来了
            return r * col + c;
        }

        public int find(int i) {
            int hi = 0;
            while (i != parents[i]) {
                help[hi++] = i;
                i = parents[i];
            }
            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }
            return i;
        }


        public void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] > size[f2]) {
                    size[f1] += size[f2];
                    parents[f2] = f1;//让数量少的点接到数量多的点上去
                } else {
                    size[f2] += size[f1];
                    parents[f1] = f2;
                }
                sets--;
            }
        }
    }

    public int numberOfIslands(char[][] board) {
        int col = board[0].length;//列
        int row = board.length;//行
        UnionFind unionFind = new UnionFind(board);
        for (int i = 1; i < col; i++) {//最上面那一行先处理
            if (board[0][i - 1] == '1' && board[0][i] == '1') {
                unionFind.union(0, i - 1, 0, i);
            }
        }
        for (int j = 1; j < row; j++) {//最旁边那一列也处理掉
            if (board[j - 1][0] == '1' && board[j][0] == '1') {
                unionFind.union(j - 1, 0, j, 0);
            }
        }
        for (int i = 1; i < col; i++) {
            for (int j = 1; j < row; j++) {
                if(board[i][j] == '1' && board[i][j-1] == '1'){//向右
                    unionFind.union(i,j, i, j-1);
                }
                if(board[i][j] == '1' && board[i-1][j] == '1'){//向左
                    unionFind.union(i,j, i-1, j);
                }
            }
        }
        return unionFind.sets;
    }
}
