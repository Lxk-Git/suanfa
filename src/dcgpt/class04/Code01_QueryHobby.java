package dcgpt.class04;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/30 22:05
 */
import java.util.ArrayList;
import java.util.HashMap;

public class Code01_QueryHobby {

    /*
     * 今日头条原题
     *
     * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)。意思是在数组里下标0~3这个范围上，有几个2？返回2。
     * 假设给你一个数组arr，对这个数组的查询非常频繁，请返回所有查询的结果
     *
     */

    public static class QueryBox1 {
        private int[] arr;
        //这就是很简单的写法
        //把传递参数数组里面的数字全部移到arr中
        //递归这个范围内的所有数字，等于V的就加一
        public QueryBox1(int[] array) {
            arr = new int[array.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = array[i];
            }
        }

        public int query(int L, int R, int v) {
            int ans = 0;
            for (; L <= R; L++) {
                if (arr[L] == v) {
                    ans++;
                }
            }
            return ans;
        }
        //L代表最左边的位置，R代表最右边的位置，V代表要找的值
        public int query2(int L,int R,int V){
            int ans = 0;
            for(;L<=R;L++){
                if(arr[L] == V){
                    ans++;
                }
            }
            return ans;
        }
    }

    public static class QueryBox2 {
        private HashMap<Integer, ArrayList<Integer>> map;

        public QueryBox2(int[] arr) {
            map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (!map.containsKey(arr[i])) {
                    map.put(arr[i], new ArrayList<>());
                }
                map.get(arr[i]).add(i);
            }
        }

        public int query(int L, int R, int value) {
            if (!map.containsKey(value)) {
                return 0;
            }
            ArrayList<Integer> indexArr = map.get(value);
            // 查询 < L 的下标有几个
            int a = countLess(indexArr, L);
            // 查询 < R+1 的下标有几个
            int b = countLess(indexArr, R + 1);
            return b - a;
        }

        // 在有序数组arr中，用二分的方法数出<limit的数有几个
        // 也就是用二分法，找到<limit的数中最右的位置
        private int countLess(ArrayList<Integer> arr, int limit) {
            int L = 0;
            int R = arr.size() - 1;
            int mostRight = -1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (arr.get(mid) < limit) {
                    mostRight = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            return mostRight + 1;
        }

    }

    //第二个方法，就是先创建一个map,key是arr[i],值是list数组，里面存储的是i，而且相同的放在一个list里面，list是有序添加的，比如：{3, 2, 2, 3, 1}，map(2,list[1,2])。
    //接下来就是找题目要的（L,R）范围内，有多少个V。将V对应的范围从map中提取出来，使用二分法，查询范围indexArr中有多少个下标小于L，有多少个下标小于R+1.再用R+1查询到的数减去L查询到的数。
    //二分法就变成了查询一个有序数组中，小于limit的最右位置
    public static class QueryBox3 {
        private HashMap<Integer,ArrayList<Integer>> map;
        public QueryBox3(int[] arr) {
            map = new HashMap<>();
            for (int i = 0;i<arr.length;i++){
                if(!map.containsKey(arr[i])){
                    map.put(arr[i],new ArrayList<>());
                }
                //add加入的下标，所以最后list是有序的
                map.get(arr[i]).add(i);
            }
        }
        public int query3(int L,int R,int V){
            ArrayList<Integer> indexArr = map.get(V);
            int a = countLess2(indexArr,L);
            int b = countLess2(indexArr,R+1);
            return b-a;
        }

        private int countLess2(ArrayList<Integer> indexArr, int limit) {
            int L=0;
            int R=indexArr.size()-1;
            int ans=-1;
            while (L<=R){
                //mid是当前indexArr的下标
                int mid = L+((R-L) >>1);
                //indexArr.get(mid)是arr的下标
                if(indexArr.get(mid) <limit){
                    ans =mid;
                    L = mid+1;
                }else {
                    R = mid -1;
                }
            }
            //加1的原因是因为ans一开始等于-1,最差情况最后都应该是0，没有-1；
            return ans + 1;
        }
    }

    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 300;
        int value = 20;
        int testTimes = 1000;
        int queryTimes = 1000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(len, value);
            int N = arr.length;
            QueryBox1 box1 = new QueryBox1(arr);
            QueryBox2 box2 = new QueryBox2(arr);
            for (int j = 0; j < queryTimes; j++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                int L = Math.min(a, b);
                int R = Math.max(a, b);
                int v = (int) (Math.random() * value) + 1;
                if (box1.query(L, R, v) != box2.query(L, R, v)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test end");
    }

}
