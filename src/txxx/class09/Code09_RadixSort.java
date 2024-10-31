package txxx.class09;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/29 14:42
 */
public class Code09_RadixSort {
    //基数排序（桶排序）
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxRadix(arr));
    }

    public static int maxRadix(int[] arr) {//计算最大的数是多少位
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;

    }

    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        int[] help = new int[R - L + 1];

        for (int d = 1; d <= digit; d++) {// 有多少位就进出几次
            //创建一个count数组，
            // 10个空间
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是(0和1)的数字有多少个
            // count[2] 当前位(d位)是(0、1和2)的数字有多少个
            // count[i] 当前位(d位)是(0~i)的数字有多少个
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            //再弄一个count一瞥，用来做前缀和
            for(i =1;i<count.length;i++){
                count[i] = count[i]+count[i-1];
            }
            for(i = R;i>=L;i--){//从右往左存数据进入help数组
                j = getDigit(arr[i],d);//把d位的数拿出来
                help[count[j]-1] = arr[i];//在count一瞥找到相对应的数值，然后减去一，把arr[i]的数放到help上
                count[j]--;//count一瞥上的这个位置的值减去一，因为可能有相等的，有for循环呢。
            }
            for(i = L,j=0;i<=R;i++,j++){
                arr[i] = help[j];
            }
        }
    }

    public static int getDigit(int x, int d) {//算个位十位百位的值是多少
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }


    public static void main(String[] args) {
        int[] arr = {1,10,6,5,11,3};
        radixSort(arr);
        System.out.println(arr.toString());
    }
}
