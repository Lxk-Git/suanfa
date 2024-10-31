package txxx.class05;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/13 23:24
 */
public class Code05_BiggerThanRightTwice {

    public static int reversePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }
    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }
    public static int merge(int[] arr, int L, int m, int r) {   
        int ans = 0;
/*        //写在这里才是一目了然
        int windowR = m + 1;
        for (int i = L; i <= m; i++) {
            while (windowR <= r && (long) arr[i] > (long) arr[windowR] * 2) {//L和r是递归变化的
                windowR++;//得到的数据正确就往右加上1
            }
            ans += windowR - m - 1;//上面符合就加上1了，不符合就没有加上1，就windowR - m - 1就是0.
        }*/
        int windowR=m+1;
        for(int i=L;i<m;i++){
            if(windowR<=r && arr[i] >arr[windowR]*2){//左边大于右边，窗口右移
                windowR++;
            }
            ans +=windowR-m-1;
        }

        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
/*            //是一个[M+1,windowR)左闭合右开的区间，
            int windowR = m + 1;//这就代表是右边第一个windowR++和windowR - m - 1代表右开
            for (int j = L; j <= m; j++) {//增加了一个变量j，新手还是这样写比较好,L和r是递归变化的
                while (windowR <= r && (long) arr[j] > (long) arr[windowR] * 2) {
                    windowR++;//得到的数据正确就往右加上1
                }
                ans += windowR - m - 1;//上面符合就加上1了，不符合就没有加上1，就windowR - m - 1就是0.
            }*/
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

}
