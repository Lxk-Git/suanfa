package txxx.class05;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/13 23:19
 */
public class Code05_ReversePair {
    //右边有多少个数比自己小，数量累加
    public static int reversePairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1); //就是除以2
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    //现在写一个和前面不一样的merge
    //从后往前加数字
    public static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = help.length - 1;
        int p1 = M;
        int p2 = R;
        int res = 0;
        while (p1 >= L && p2 > M) {//是大于M，要注意边界
            res += arr[p1] > arr[p2] ? (p2 - M) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }

        while (p1 >= L) {
            help[i--] = arr[p1--];
        }
        while (p2 > M) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    public static int merge2(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = help.length - 1;
        int p1 = M;
        int p2 = R;
        int res = 0;
        while (p1 >= L && p2 > M) {
            res += arr[p1] > arr[p2] ? (p2 - M) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] :arr[p2--];
        }
        while (p1 >= L) {
            help[i--] = arr[p1--];
        }
        while (p2 > M) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }


}
