package txxx.class09;


/**
 * @Author: LiuXingKun
 * @Date: 2022/12/29 13:50
 */
public class Code09_CountSort {
    //计数排序,数据规模有限
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] arr2 = new int[max + 1];//创建一个长度为arr最大值加1的数组，这个数组可以将arr中所有的数据都存下来
        for (int i = 0; i < arr.length; i++) {
            arr2[arr[i]]++;//arr中的数字就存在arr2对应的下标，这个下标大于0就证明arr中有这个数字。
        }
        int i = 0;
        for (int j = 0; j < arr2.length; j++) {
            while (arr2[j]-- > 0) {//arr2[j]的数要大于0就证明arr中有j这个数。
                arr[i++] = j;
            }
        }
    }

    public static void countSort2(int[] arr) {
        if (arr == null || arr.length < 0) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] arr2 = new int[max + 1];
        for (int j = 0; j < arr2.length; j++) {
            arr2[arr[j]]++;
        }
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            while (arr2[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }
}
