package txxx.class25;

import java.util.LinkedList;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/21 10:51
 */
public class Code01_SlidingWindowMaxArray {
    //暴力方法
    public static int[] sliding1(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        //最后要得到的结果的个数一定是N-w+1个
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = 0;
            for (int i = L; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    //双链表方法
    public static int[] sliding2(int[] arr,int w){
        if(arr == null || w<1||arr.length<w){
            return null;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();//双端链表
        int[] res = new int[arr.length-w+1];
        int index = 0;
        for(int R=0;R<arr.length;R++){
            while (!linkedList.isEmpty() && arr[linkedList.peekLast()] <=arr[R]){//双端链表的尾部元素小于等于arr[R]的元素，那就弹出，while循环
                linkedList.pollLast();//从尾弹出
            }
            //否则就吧index下标加进去
            linkedList.add(R);
            //在双端列表中设置一个过期位置，随着窗口的变化二变化，比如现在w是3，R是0，他的过期位置就要是-3，R是3，过期位置就是0
            //如果双端队列的头位置和过期位置一样，就从头弹出
            if(linkedList.peekFirst() == R-w){
                linkedList.pollFirst();
            }
            //当R=w-1的时候说明窗口里面有w个元素了，R-w和R就会一直维持这个窗口有w个元素。
            if(R>=w-1){
                res[index++] = arr[linkedList.peekFirst()];
            }
        }
        return res;
    }







    // 暴力的对数器方法
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);

            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = sliding1(arr, w);
            int[] ans2 = right(arr, w);
            int[] ans3 = sliding2(arr, w);
            if (!isEqual(ans1, ans3)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
