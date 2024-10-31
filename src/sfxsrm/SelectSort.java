package sfxsrm;

import java.util.Arrays;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/13 21:30
 */
public class SelectSort {
    public static void main(String[] args) {
        int arr [] = {7,6,5,4,3,2,1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 第一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，然后再从剩余的未排序元素中寻找到最小（大）元素，然后放到已排序的序列的末尾
     * 选择排序 从小到大
     * 时间复杂度O（N^2),空间复杂读O（1)，不稳定的排序算法
     * 1.选择排序就是将数组中最小的与第一位交换位置，依次第二小的与第二位交换位置。
     * 2.第一个for循环就是交换的下标从第一个开始开始的。
     * 3.第二个for循环就是找到目前数组中最小数字的下标。
     * @param arr
     */
    public static void selectSort(int[] arr){
        if(arr.length == 0 || arr.length < 2){
            return;
        }

        for(int i = 0 ; i < arr.length-1 ; i++){
            int minIndex = i;
            for(int j = i+1;j < arr.length; j++){
                //i到arr.length最小的选出来
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            //再用最前面的i和选到最小的交换位置
            swap(arr,i,minIndex);
        }
    }


    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}