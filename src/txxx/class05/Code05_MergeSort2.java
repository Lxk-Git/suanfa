package txxx.class05;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/13 22:28
 */
public class Code05_MergeSort2 {
    //归并排序，用递归重点是先将所有的子序列有序，再将序列段有序
    //归并排序是建立在归并操作上的一种有效，稳定的排序算法，该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
    //将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。

    //从后往前写

    //先写怎么让每个子序列都有序
    //O(logN)
    public static void merge(int[] arr, int L, int M, int R) {
        //创建一个help数组来临时接收
        int[] help = new int[R - L + 1];//其实也可以设置一个等长arr.length -1的数组，就是有空间浪费。
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {//此时都没有越界，相等的时候先拿左边的数据
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //但是不是P1越界就是p2越界，谁不越界，就将不越界的所有元素都放到help数组中
        while (p1 <= M) {//此时是p2越界
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {//此时是p1越界
            help[i++] = arr[p2++];
        }

        //再将help中的数据拷贝会arr中
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];//每一次拷贝的时候不是所有的数据都拷贝，是拷贝已经排序好的数据，所以下标是从L+i开始的。
        }
    }

    //递归就是将每个子序列都有序了，在将序列组有序
    //O(N)
    public static void process(int[] arr, int L, int R) {
        if (L == R) {//递归结束条件
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }
    //O(N*logN)
    public static void mergeSort(int[] arr, int L, int R) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, L, R);
    }

    public static void mergeSort3(int[] arr, int L, int R) {
        if (arr == null || arr.length < 0) {
            return;
        }
        process2(arr, L, R);
    }

    private static void process2(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process2(arr, l, mid);
        process2(arr, mid + 1, r);
        merge2(arr, l, mid, r);
    }

    private static void merge2(int[] arr, int l, int mid, int r) {
        if (arr == null || arr.length < 0) {
            return;
        }
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = 0;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? p1++ : p2++;
        }
        while (p1<=mid){
            help[i++] = arr[p1++];
        }
        while (p2<=r){
            help[i++] = arr[p2++];
        }
        for(int j =0;j<help.length;j++){
            arr[l+j] = help[j];
        }
    }


    //归并排序，用迭代实现
    //迭代实现的重点就是用一个不断用变量的旧值递推出新值的过程。
    //这里的变量就是步长
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;//步长从1开始到mergeSize小于N/2结束
        while (mergeSize <= N / 2) {

            int L = 0;//每一个新步长，第一次都从0开始
            while (L < N) {//至少可以凑出左边
                if (mergeSize >= N - L) {//只有左边，没有右边的时候直接结束
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - (M + 1));
                merge(arr, L, M, R);
                L = R + 1;

            }
            mergeSize <<= 1;//乘以2
        }
    }


}
