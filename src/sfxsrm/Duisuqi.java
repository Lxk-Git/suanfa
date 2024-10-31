package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/17 0:10
 */
public class Duisuqi {

    //首先生成一个随机长度，随机数字的数组
    public static int[]  randomArr(int romLen,int romValue){
        romLen = (int)(Math.random() * romLen);
        int[] arr = new int[romLen];
        for(int i = 0; i < romLen; i++){
            arr[i] = (int)(Math.random() * romValue);
        }
        return arr;
    }
    //之后要用一个数组去复制一个内存空间不一样的等长等值数组
    public static int[] copyArr(int[] arr1){
        int[] arr2 = new int[arr1.length];
        for(int i = 0; i < arr1.length; i++){
            arr2[i]= arr1[i];
        }
        return arr2;
    }
    //最后对比两个数组是不是一样的
    public static boolean equalValues(int[] arr1,int[] arr2){
        for (int i = 0; i< arr1.length; i++){
            if(arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }
    //改进方法
    public static boolean equalValues2(int[] arr1){
        if(arr1.length < 2){
            return true;
        }
        int max=arr1[0];
        for (int i = 1; i< arr1.length; i++){
            if(max > arr1[i]){
                return false;
            }
            max = Math.max(max,arr1[i]);
        }
        return true;
    }

    /**
     * 选择排序 从小到大
     * 时间复杂度O（N^2),空间复杂读O（1)，稳定的排序算法
     * 1.选择排序就是将数组中最小的与第一位交换位置，依次第二小的与第二位交换位置。
     * 2.第一个for循环就是交换的下标从第一个开始开始的。
     * 3.第二个for循环就是找到目前数组中最小数字的下标。
     * @param arr
     */
    public static void selectSort(int[] arr){
        if(arr.length == 0 || arr.length < 2){
            return;
        }

        for(int i = 0 ; i < arr.length -1 ; i++){
            int minIndex = i;
            for(int j = i+1;j < arr.length; j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //冒泡排序
    public static void bubble(int[] arr){
        for(int i = 0; i < arr.length; i++){//一开始没有一个数字是有序的，当一个有序了就继续往上排序。
            for(int j = 0; j < arr.length - 1 -i; j++){//不管有多少个数字已经是有序的，每次还是重复从下标是0的开始排序，直到把最大（小）的数字放在最后。
                if(arr[j]>arr[j+1]){                    //arr.length - 1 -i 代表着有多少个数字有序了，有序的数字当然不需要比较了。
                    swap(arr,j,j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int romLen = 50;
        int romValue = 500;
        int testCounts = 5000;
        for (int i =0; i <testCounts; i++){
            int[] arr1 = randomArr(romLen,romValue);
            int[] arr2 = copyArr(arr1);
            selectSort(arr1);
            bubble(arr2);
            if(!equalValues2(arr1)){
                System.out.println("排序有错！");
            }
//            if(equalValues(arr1,arr2)){
//                System.out.println("排序有错");
//            }
        }
    }
}
