package txxx.class03;

import java.util.Arrays;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/6 17:01
 */
public class Code02_Swap {

    //注意，要是i和j是相等的，也就是指向一个位置，那就坏了，就会变成0，因为一样的数^就是0
    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];//这一步带进去就是把arr[i]变成了 arr[i] ^ arr[j] ，然后arr[j] = arr[i] ^ arr[j]^ arr[j],得arr[j] = arr[i] 位置交换
        arr[i] = arr[i] ^ arr[j];//注意第二部arr[j]已经等于arr[i]了，同理arr[i] =arr[i] ^ arr[j]^ arr[i]，得arr[i] =arr[j]，位置交换
    }

    public static void swap2(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {3,1,100};
        for(int i =0;i<3;i++){
            for(int j =i+1;j<3;j++){
                swap(arr,i,j);
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
