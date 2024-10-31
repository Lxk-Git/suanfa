package txxx.class25;

import java.util.LinkedList;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/21 22:56
 */
public class Code03_GasStation {
    //让gas减去cost的值
    //然后创建一个2倍gas.length的数组，多出来的那部分也是填写gas减去cost的值
    //将2倍的数组做前缀和
    //现在得到一个新的数组
    //老的数组长度是N，新的数组长度是2N，
    // 以老的数组下标为0的数字开始做2N数组窗口的L，R是N-1，然后看这个数组中最小的数是多少，用最小的数减去L前面的数，如果是正数，就说明能从这个点出发走完全程
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] good = goodArray(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] goodArray(int[] g, int[] c) {
        int N = g.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = g[i] - c[i];
            arr[i + N] = g[i] - c[i];
        }
        for (int i = 1; i < M; i++) {
            arr[i] += arr[i - 1];
        }
        LinkedList<Integer> w = new LinkedList<>();//双端队列
        for (int i = 0; i < N; i++) {//创建一个窗口，把最小的放进去
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {//把0到N-1，N个数据中最小的值放入双端队列。
                w.pollLast();
            }
            w.addLast(i);
        }
        boolean[] ans = new boolean[N];//记录0到N-1中每个数据是不是符合条件
        //
        for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
            if (arr[w.peekFirst()] - offset >= 0) {//第一次offset是0，第二次是arr[0],最小值减去L前面的一个数判断是不是有效的起点
                ans[i] = true;
            }
            if (w.peekFirst() == i) {//把和i相同的数据做过期处理
                w.pollFirst();
            }
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {//j+1新进入窗口
                w.pollLast();
            }
            w.addLast(j);
        }
        return ans;
    }

    public static boolean[] goodArray2(int[] g, int[] c){
        int N = g.length;
        int M = N << 1;
        int[] arr = new int[M];
        for(int i =0;i<N;i++){
            arr[i] = g[i] - c[i];
            arr[i+N] = g[i] - c[i];
        }
        //前缀和
        for(int j =1;j<M;j++){
            arr[j] = arr[j] + arr[j-1];
        }
        //创建一个从小到大排列的双端队列
        LinkedList<Integer> linkedList = new LinkedList<>();
        //先把0到N-1的数据先放入双端队列
        for(int i = 0;i<N;i++){
            while (!linkedList.isEmpty() && arr[linkedList.peekLast()] >= arr[i]){
                linkedList.pollLast();
            }
            linkedList.addLast(i);
        }
        boolean[] ans = new boolean[N];
        //对每个长度为N的窗口找到最小值，并减去L前面的数字，看是不是大于>0，是就是true，L是0的时候，减去的是0
        for(int offset = 0,i=0,j=N;j<M;offset=arr[i++],j++){
            if(arr[linkedList.peekFirst()] - offset >0){
                ans[i] = true;
            }
            //设置过期
            if(linkedList.peekFirst() == i){
                linkedList.pollFirst();
            }
            //把R向右移
            while (!linkedList.isEmpty() && arr[linkedList.peekLast()] >= arr[j]){
                linkedList.pollLast();
            }
            linkedList.addLast(j);
        }
        return ans;
    }

}
