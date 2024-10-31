package txxx.class05;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/13 23:17
 */
public class Code05_SmallSum2 {
    //小和问题，算右边有多少个数比自己大，有一个就加上自己本身，求和
    public static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;//help数组用的
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        while (p1 <= M && p2 <= R) {
            //R - p2 + 1看左边有多少个符合情况，再乘上当前对比的数字。
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;//R - p2 + 1 中p2是会向右移动的,p1也会向右移动
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];//等于的时候让p2移动，这样才能统计出正确的右边大于左边的数
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        //     左边               +      右边                  +整体
        return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    public static int smallSum(int[] arr, int L, int R) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, L, R);
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int i = smallSum(arr, 0, 4);
        System.out.println(i);
    }
}
