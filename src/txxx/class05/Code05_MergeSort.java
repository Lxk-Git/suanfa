package txxx.class05;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/13 22:28
 */
public class Code05_MergeSort {
    //先使每个子序列有序，再使子序列段间有序。
    // 递归方法实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    //递归左边部分，和右边部分，先让他们各自有序，再将整体变得有序
    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];//创建一个help数组
        int i = 0;
        int p1 = L;//左边部分的最左边的下标
        int p2 = M + 1;//右边部分的最左边的下标
        while (p1 <= M && p2 <= R) {//当不越界的时候
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 越界了的时候，要么p1越界了，要么p2越界了，就将不越界的数字全部放到help数组中
        while (p1 <= M) {//此时p2越界
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {//此时p1越界
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];  //复制回原来的数组
        }
    }

    //迭代实现   不断用变量的旧值递推新值的过程
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 重点是 步长
        int mergeSize = 1;
        while (mergeSize < N) {
            // 当前左组的，第一个位置
            int L = 0;
            while (L < N) {
                if (mergeSize >= N - L) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 用来防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }



}
