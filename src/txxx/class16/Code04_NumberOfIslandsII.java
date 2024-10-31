package txxx.class16;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/3 10:48
 */
public class Code04_NumberOfIslandsII {
    //题目，先创建一个m*n的二维数组a，但是一开始里面的数字都是0，然后传入一个新的n*2的二维数组positions，里面说明在原来的数组a中，什么地方变成1，最后连成岛，求每一步有多少个岛。
    public static List<Integer> numIslands(int m, int n, int[][] positions){
        UnionFind unionFind = new UnionFind(m, n);
        List<Integer> ans = new ArrayList<>();//用一个list集合来存每一次有多少个岛！
        for(int[] position:positions){
            ans.add(unionFind.connect(position[0],position[1]));
        }
        return ans;
    }
    //先创建一个类
    public static class UnionFind {
        public int[] parents;
        public int[] size;
        public int[] help;
        public int col;
        public int row;
        public int sets;

        //此时的初始化方法，是将所有的位置初始化为0
        public UnionFind(int m, int n) {
            this.row = m;
            this.col = n;
            int len = m * n;
            this.sets = 0;
            parents = new int[len];
            size = new int[len];//这就代表这初始化是将所有的位置都变成了0
            help = new int[len];
        }

        public int index(int r, int c) {
            return r * col + c;
        }

        //因为是每次从positions数组中传入数据到原来的数组，所以需要写一个单独的插入数据变成岛的方法。
        public int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {//说明传进来的数字是第一次初始化为1，如果已经是1了就不用弄了
                parents[index] = index;
                size[index] = 1;//把那个位置变成1
                sets++;
                //每次传递进来一个数，也要看看他的上下左右是什么,传进来的时候控制要不要合并
                union(r, c, r - 1, c);
                union(r, c, r + 1, c);
                union(r, c, r, c - 1);
                union(r, c, r, c + 1);
            }
            return sets;
        }

        private void union(int r, int c, int r1, int c1) {//并
            //边界问题
            if (r < 0 || r == row || r1 < 0 || r1 == row || c < 0 || c == col || c1 < 0 || c1 == col) {
                return;
            }
            int i1 = index(r, c);
            int i2 = index(r1, c1);
            if (size[i1] == 0 || size[i2] == 0) {//如果两个点有一个为0，直接放弃合并，这样就不会减少sets的值,少了这一步会导致1和0相交，岛的数量会不对
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {//父节点不是一个，才要合并
                if(size[f1] >= size[f2]){
                    size[f1] += size[f2];
                    parents[f2] = f1;
                }else {
                    size[f2] += size[f1];
                    parents[f1] = f2;
                }
                sets--;
            }
        }

        public int find(int cur) {//查
            int hi = 0;
            while (cur != parents[cur]) {
                help[hi++] = cur;
                cur = parents[cur];
            }
            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = cur;
            }
            return cur;
        }
    }
}
