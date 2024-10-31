package sfxsrm;

import java.util.Arrays;

/**
 * @Author: LiuXingKun
 * @Date: 2023/4/14 16:57
 */
public class InsertAlgorithm {

    /**
     * 插入排序是一种最简单的排序方法，它的基本思想是将一个记录插入到已经排好序的有序表中，从而一个新的、记录数增1的有序表。
     * 插入排序的位移排序
     * 插入排序，从小到大
     * 1.排序从第一个开始，有N个元素，一共排序N-1次
     * 2.默认第一个已经是排序好的了。
     * 3.第一个循环从下标1的开始，也就是第二个数字开始。
     * 4.第二个循环(i-1用j代替)，开始下标i和下标j比较，下标j是否大于下标i,是就将下标j的值给到（j+1）,在while循环中会出现两个一样的值，arr[j+1] = arr[j]
     *   此时为了达到替换数据的效果，需要j--，这样才能进行下一次比较。j--等于-1的时候就跳出循环，temp的值付给j+1也就是0
     * 5.第二个循环走完继续走第一个循环，两个循环结束就结束了。
     * 6.时间复杂度为O（n^2）
     * 稳定的排序算法
     * @param arr
     */
    public static int[] studyInsertSort(int[] arr) {
        System.out.println("数组长度为： " + arr.length);
        System.out.println("传入的原数组为： " + Arrays.toString(arr));
        System.out.println("开始排序-----");
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
            System.out.println("第  " + (i) + "  次排序结果： " + Arrays.toString(arr));
        }
        return arr;
    }



    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {6,78,6,53,34,2,1};
        studyInsertSort(arr);
        for(int i =0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
    }
}
