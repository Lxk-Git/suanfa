package dcgpt.class03;

import java.util.Arrays;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/21 17:06
 */

// 给定一个正数数组arr，代表若干人的体重
// 再给定一个正数limit，表示所有船共同拥有的载重量
// 每艘船最多坐两人，且不能超过载重
// 想让所有的人同时过河，并且用最好的分配方法让船尽量少
// 返回最少的船数
// 测试链接 : https://leetcode.com/problems/boats-to-save-people/
public class Code05_BoatsToSavePeople {
    //收尾指针方法
    public static int numRescueBoats(int[] arr,int limit){
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length-1;
        int ans  =0;
        while (left <= right){
            if(arr[left] + arr[right] >limit){
                right--;
                //这里要ans++是考虑又边有大于limit的情况
                ans++;
            }else {
                //如果左边和右边相加是大于limit的，right要减一，然后在现在的情况下left用了之后，right用了之后要加一，不然会有重复
                left++;
                right--;
                ans++;
            }
        }
        return ans;
    }

    public static int numRescueBoats1(int[] arr,int limit){
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length-1;
        int ans  =0;
        while (left <= right){
            if(arr[left] + arr[right] <=limit){
                ++left;
            }
            //这里不符合的情况，一定要让右边先减去，不然就会出现一直不符合的情况。
            --right;
            ++ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr= {3,2,2,1};
        int i = numRescueBoats1(arr, 3);
    }
}
