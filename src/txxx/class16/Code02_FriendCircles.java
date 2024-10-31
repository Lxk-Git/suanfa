package txxx.class16;


/**
 * @Author: LiuXingKun
 * @Date: 2023/2/2 9:30
 */
public class Code02_FriendCircles {

    //一群人，如果两两认识就是一个朋友圈，如果0认识2,2认识4,1不认识4,{0,2,4}就是一个朋友圈。
    //假如一共有N个人，可以看成(N-1)*(N-1)的二维数组，如果相互认识就是1，不认识就是0,问有多少个朋友圈。
    public static int findCircleNum(int[][] M) {
        int N = M.length;
        // {0} {1} {2} {N-1}
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) { // i和j互相认识
                    unionFind.union(i, j);
                }
            }
        }
//        unionFind.find(4);
        return unionFind.sets();
    }

    public static class UnionFind {
        // parent[i] = k ： i的父亲是k
        private int[] parent;
        // size[i] = k ： 如果i是代表节点，size[i]才有意义，否则无意义
        // i所在的集合大小是多少
        private int[] size;
        // 辅助结构
        private int[] help;
        // 一共有多少个集合
        private int sets;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        // 从i开始一直往上，往上到不能再往上，代表节点，返回
        // 这个过程要做路径压缩
        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }
        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }
        public int sets() {
            return sets;
        }
    }


    public static int findCircleNum1(int[][] M){
        int N = M.length;
        UnionFind1 find1 = new UnionFind1(N);
        for(int i = 0;i<N;i++){
            for(int j = 0;j<N;j++){
                if(M[i][j] == 1){
                    find1.union1(i,j);
                }
            }
        }
        return find1.size;
    }

    public static class UnionFind1{
        private int[] parents;//用来存节点的父节点是谁，i的父亲节点k
        private int[] sizes;//每个节点对应有多少个,i的节点有多少个，可以统计一个朋友圈有多少人
        private int[] helps;//这个是辅助结构，在findParent方法中可以用来查询父节点，并且压缩路径
        private int size;//有多少个集合，开始有N个，合成一个朋友圈就减去一个集合，最后剩下的就是一共有多少个朋友圈


        //init初始化,这个初始化还是很重要的
        public UnionFind1(int N){
            parents = new int[N];
            sizes = new int[N];
            helps = new int[N];
            size = N;
            for(int i = 0;i<N;i++){
                parents[i] = i;
                sizes[i] = 1;
            }
        }
        //先来看findParent方法,找节点的最高父节点是谁
        public int findParent1(int i){
            int hi = 0;
            while (i != parents[i]){
                helps[hi++] = i;
                i = parents[i];
            }
            for(hi--;hi>=0;hi--){//这里做了路径压缩
                parents[helps[hi]] = i;
            }
            return i;
        }


        //接下来就是合并的事情了
        public void union1(int i,int j){
            int node1 = findParent1(i);
            int node2 = findParent1(j);
            if(node1!=node2){
                if(sizes[node1] >= sizes[node2]){
                    sizes[node1] += sizes[node2];
                    parents[node2] = node1;
                }else{
                    sizes[node2] += sizes[node1];
                    parents[node1] = node2;
                }
                size--;
            }
        }

    }

    public static void main(String[] args) {
        int[][] M = {{1,0,1,0,1},{0,1,0,1,0},{1,0,1,0,0},{0,1,0,1,0},{1,0,0,0,1}};
        int num = findCircleNum1(M);
        System.out.println(num);
    }


}
