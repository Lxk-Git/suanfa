package txxx.class06;

import java.util.Random;
import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/15 17:05
 */
public class Code06_PartitionAndQuickSort {
    //荷兰国旗问题
    //方法一：选择一个X，小于等于X放在左边，大于放在右边。
    //拟定一个小于的范围lessEqual，和一个指针index，如果X小于等于index指向的数据，当前数和lessEqual的下一个数交换，如果大于，index直接跳过
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        //这里选的数据就是arr[R]
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[R] >= arr[index]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, ++lessEqual, R);//arr[R]和小于等于区的后一个数交换。
        return lessEqual;
    }

    //方方法二:选择一个X，小于X放在左边，等于放在中间，大于放在右边。返回的是等于区间的索引区间
    ///拟定一个小于的范围less，大于的范围more，和一个指针index，如果X小于index指向的数据，当前数和less的下一个数交换；
    // 如果等于，index直接跳过
    // 如果大于，和more的前一个位置交换
    public static int[] partition2(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        //这里大于区域是从R开始，是因为在快速排序的时候，现在R位置上的数来划分整个区间
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[R] > arr[index]) {
                swap(arr, index++, ++less);
            } else if (arr[R] < arr[index]) {
                swap(arr, index, --more);//index在这个时候不动是因为index上面的数字是刚交换过来的
            } else {
                index++;
            }
        }
        //因此在这个地方需要将more的第一个位置和最后一个R位置的数交换
        swap(arr, more, R);
        return new int[]{less + 1, more};//这是一个闭区间
    }

    public static int[] partition3(int[] arr, int L, int R) {

        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, L};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < R) {
            if (arr[R] > arr[index]) {
                swap(arr, index++, ++less);
            } else if (arr[R] < arr[index]) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }


    //快排的重点就是这个荷兰国旗方法2的中间值就是有序的，这个时候就用递归让左边有序，右边也有序
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {//递归结束条件
            return;
        }
        //快速排序的重点就是选择一个随机index将它放在R位置上
        swap(arr, L + (int) (Math.random() * ((R - L) >> 1)), R);
        int[] M = partition2(arr, L, R);
        process(arr, L, M[0] - 1);
        process(arr, M[1] + 1, R);
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {//递归结束条件
            return;
        }
        swap(arr, L + (int) (Math.random() * ((R - L) >> 1)), R);
        int[] M = partition3(arr, L, R);
        process2(arr, L, M[0] - 1);
        process2(arr, M[1] + 1, R);
    }

    //用栈的迭代来实现快速排序，
    //先做一个op辅助类
    public static class op {
        public int l;
        public int r;

        public op(int left, int right) {
            this.l = left;
            this.r = right;
        }
    }

    //所有的排序都是在partition2方法里面做的
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        swap(arr, (int) (Math.random() * N), N - 1);
        int[] M = partition2(arr, 0, N - 1);
        int el = M[0];
        int er = M[1];
        Stack<op> stack = new Stack<>();
        stack.push(new op(0, el - 1));
        stack.push(new op(er + 1, N - 1));
        while (!stack.isEmpty()) {
            op pop = stack.pop();//先右边再左边
            while (pop.r > pop.l) {//右边全部排序好，不符合pop.r>pop.l就是相等的时候，就去排序右边
                swap(arr, pop.l + (int) (Math.random() * ((pop.r - pop.l) >> 1)), pop.r);
                M = partition2(arr, pop.l, pop.r);//注意看这里是用了几个有限的变量
                el = M[0];
                er = M[1];
                stack.push(new op(pop.l, el - 1));
                stack.push(new op(er + 1, pop.r));
            }
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 2, 5, 3, 7, 4};
        quickSort1(arr);
    }


    //全新
    //先做荷兰国旗问题，也就是小于x的放在左边，==x放在中间，大于x放在右边
    //要做的是x是可以等于多个的情况
    public static int[] partition4(int[] arr, int l,  int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[r] < arr[index]) {
                swap(arr, index++, ++less);
            } else if (arr[r] > arr[index]) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
        swap(arr, more, r);
        return new int[]{less+1, more};
    }
    public static void quickSort(int[] arr){
        if(arr==null || arr.length<1){
            return;
        }
        process33(arr,0,arr.length-1);
    }

    private static void process33(int[] arr, int l, int r) {
        if(l>=r){
            return;
        }
        swap(arr,l+(int) Math.random() * ((r-l)>>1),r);
        int[] ints = partition4(arr, l, r);
        process33(arr,l,ints[0]-1);
        process33(arr,ints[0]+1,r);
    }

}
