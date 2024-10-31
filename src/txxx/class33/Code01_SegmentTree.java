package txxx.class33;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/12 16:30
 */

/**
 * 备注：0110前面的0是符号位，indexTree数组默认从1开始
 * 1.indexTree和别的数组有点不一样，他可以用来更快的做前缀和，支持区间查询，支持单点更新。
 * 按照一个arr[1,2,3,4,5,6,7,8,9],他生成的help(indexTree)[]应该是{1,3,3,10,5,11,7,36,9},
 * help他是这样，是因为他的生成策略，他的生成策略是一个位置变化了，那么被影响的位置一定是本身加上最右边的1，就是跟着变化的位置
 * 例如，1位置是0001，取反加1得到他最右边的1还是0001，然后他们相加得到下一个被影响的位置0010；类推0010最右边的1还是0010，受影响的位置是0100；再推就是1000
 * 换个0110，他被影响的位置0110+0010得到1000.
 * 2.indexTree的help数组做前缀和就简单：把从右到左的1拆掉，加上这个被拆掉的，再加上拆掉1的，到没有1就是0的时候结束。
 * 例如：help[0110] = help[0110]+help[0100]+help[0000]
 */
public class Code01_SegmentTree {

    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    //0位置是不用的
    public static class IndexTree2{
        public static int[] tree1;//help数组
        public static int n;//大小

        public IndexTree2(int size){
            n = size;
            tree1 = new int[n+1];
        }
        //前缀和,这个算前缀和是从往小加的
        // 1~index 累加和是多少？
        //前缀和，33位置的前缀和就是32的前缀和加上33的数
        //把从右到左的1拆掉，加上这个被拆掉的，再加上拆掉1的，到没有1就是0的时候结束。
        //help是indexTree数组，help[0110] = help[0110]+help[0100]+help[0000](最后就是0，因为默认下标成1开始)
        public static int sum(int index){
            int res = 0;
            while (index>0){
                //就是tree[index]+减去最右边1的tree[index`]，index`不等于0就一直做下去
                res += tree1[index];
                //比如0110减去最右边的1就是0100
                index -= index & -index;
                //再看arr数组值1到值6的累加和是21，在help数组中直接用11+10就得到了
            }
            return res;
        }
        //单点修改一个值,d就是要修改的值
        // index & -index : 提取出index最右侧的1出来，-index就是取反加一（~index+1）比如0110取反是0001，在加上1就是0010.得到了最右侧的1.
        // index :           0011001000
        // index & -index :  0000001000
//        有个位置的数字变化了，被牵连的位置会有哪些？
//        就是本身加上最右边的1，就是会被牵连的位置
        public static void add(int index,int d){
            //在n的范围内
            while (index<=n){
                tree1[index] += d;
                //被影响的位置
                index += index & -index;
            }
        }
    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree2 tree = new IndexTree2(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }

}
