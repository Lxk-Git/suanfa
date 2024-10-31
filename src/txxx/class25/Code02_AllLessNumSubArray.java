package txxx.class25;

import sfxsrm.LinkTable;

import java.util.LinkedList;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/21 18:17
 */
public class Code02_AllLessNumSubArray {


    //暴力方法
    //L从0开始，R也从0开始，两个for循环，R到4了，L还是0；再加一个for循环，让0到4里面再每个小的子序列选出最大最小值，相减是不是小于等于num
    public static int allLess(int[] arr,int num){
        if(arr==null || arr.length ==0||num<0){
            return 0;
        }
        int counts = 0;
        for(int L = 0;L<arr.length;L++){
            for(int R = L;R<arr.length;R++){
                int max = arr[L];
                int min = arr[L];
                for(int i = L+1;i<=R;i++){//用第L个数来比较，i当然要从L+1开始（从L开始也行）
                    max = Math.max(max,arr[i]);
                    min = Math.min(min,arr[i]);
                }
                if(max-min<=num){
                    counts++;
                }
            }
        }
        return counts;
    }
    //一个L到R范围中最大值和最小值相减>sum，这个数组中所有的L到R-1范围的都符合最大值减去最小值<=sum
    public static int allLess2(int[] arr,int sum){
        if(arr==null || arr.length ==0||sum<0){
            return 0;
        }
        //创建一个大到小的双端队列maxLinked
        LinkedList<Integer> maxLinked = new LinkedList<>();
        //创建一个小到大的双端队列minLinked
        LinkedList<Integer> minLinked = new LinkedList<>();
        int counts = 0;
        int R = 0;
        for(int L=0;L<arr.length;L++){
            while (R<arr.length){//要一直对R进行while循环，到L到R范围不再符合最大值减去最小值<=sum就停止，这样，L到R-1就是符合的
                while (!maxLinked.isEmpty() && arr[maxLinked.peekLast()] <= arr[R]){
                    maxLinked.pollLast();
                }
                maxLinked.addLast(R);
                //从小到大，如果队列中的数据大于arr[R]的数据就被弹出，R进去
                while (!minLinked.isEmpty() && arr[minLinked.peekLast()] >= arr[R]){
                    minLinked.pollLast();
                }
                minLinked.addLast(R);
                if(arr[maxLinked.peekFirst()] - arr[minLinked.peekFirst()] >sum){
                    break;
                }else {
                    R++;
                }
            }
            counts += R-L;
            //处理过期的情况
            if(maxLinked.peekFirst() == L){
                maxLinked.pollFirst();
            }
            if(minLinked.peekFirst() == L){
                minLinked.pollFirst();
            }
        }
        return counts;
    }


    // 暴力的对数器方法
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = allLess2(arr, sum);
            int ans2 = right(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
