package dcgpt.class02;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/17 16:28
 */
// 本题测试链接 : https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
    //默认整个是从小到大排序好大部分的，
    //因为只要一段数字排序，所以就看从左到右，最后前面的数字大于后面的数字，后面的数字也大于前面找到的那个数字，记录下来，这就是最右边符合的数字
    //同理从右到左，左边出现的最后的那个数字，大于从右到左找到的最小的数字，就是我们期望的位置
    //例子：0,1,2,15,16,14,20,21,22
    //最右边符合的位置是下标5的14
    //最左边符合的位置是下标3的15
public class Code06_MinLengthForSort {
    public static int findUnsortedSubarray2(int[] nums){
        if(nums == null || nums.length<1){
            return 0;
        }
        int N=nums.length;
        int max = Integer.MIN_VALUE;
        int right = -1;
        for(int i = 0;i<N;i++){
            if(max > nums[i]){
                right = i;
            }
            max = Math.max(max,nums[i]);
        }
        int left = N;
        int min = Integer.MAX_VALUE;
        for(int i = N-1;i>=0;i--){
            if(min < nums[i]){
                left = i;
            }
            min = Math.min(min,nums[i]);
        }
        return right - left + 1;
    }
}
