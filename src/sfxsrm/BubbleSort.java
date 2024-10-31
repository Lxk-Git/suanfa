package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2023/4/14 16:35
 */
public class BubbleSort {
    /**
     * 冒泡排序的关键在于数组里面的数字两两比较，然后把最大（小）的放到最后，放到最后的默认就是排好序的了。
     * 冒泡排序排序过程中需要一个临时变量进行两两交换，所需要的额外空间为1，因此空间复杂度为O(1)。,冒泡排序的时间复杂度是O(N2)。
     *
     * 冒泡排序是稳定的算法，它满足稳定算法的定义。
     * 算法稳定性 -- 假设在数列中存在a[i]=a[j]，若在排序之前，a[i]在a[j]前面；并且排序之后，a[i]仍然在a[j]前面。相同元素的前后顺序并没有改变。则这个排序算法是稳定的！
     *
     * 1.两两对比，如果前一个比后一个大（小），就将两个的位置对换。
     * 2.一直重复这个动作，从第一对一直到最后一对，最后将最大（小）的元素放在数组的末尾。此时最后一个数字就是有序的了，就不要继续比较了。
     * 3.继续重复这个动作，但是每次都会减少一个数字参与比较。
     * 4.最后所有无序的的数字都比较好了，就是有序的了。
     * @param arr
     */
    public static void bubble(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - 1 -i; j++){//不管有多少个数字已经是有序的，每次还是重复从下标是0的开始排序，直到把最大（小）的数字放在最后。 - 1是因为j和j+1比较，不减去1会越界
                if(arr[j]>arr[j+1]){                    //i 代表着有多少个数字有序了，有序的数字当然不需要比较了，要减去。
                    swap2(arr,j,j+1);
                }
            }
        }
    }

    public static void swap2(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void main(String[] args) {
        int[] arr = {4,3,1,3,4,5};
        bubble(arr);
        for(int i =0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
    }
}
