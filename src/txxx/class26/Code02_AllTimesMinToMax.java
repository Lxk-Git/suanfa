package txxx.class26;

import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/23 13:41
 */
public class Code02_AllTimesMinToMax {
    //把arr做成一个前缀和，第i到j个数的和就是j对应的前缀和减去i-1对应的前缀和
    //arr[4,3,4,3],前缀和sum[4,7,11,14]，1到2的前缀和就是11-4=7;
    //创建一个栈，让数据从小到大压入栈，如果新压入栈的数小于等于已有栈的栈顶，那就把栈顶弹出.
    // 如果栈里面没有数据了，就用sum[i-1}*arr[j]
    //如果还有数据，就用sum[i-1]-sum[stack.peek()]再乘以arr[j]
    //sum[i-1]-sum[stack.peek()]中sum[stack.peek()]就是那个比arr下标中i-1还小的数据，不能包含他
    public static int minToMax(int[] arr){
        if(arr.length == 0 || arr == null){
            return Integer.MIN_VALUE;
        }
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for(int i = 1;i<arr.length;i++){
            sum[i] = arr[i] + sum[i-1];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int i =0;i<arr.length;i++){
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                int j = stack.pop();
                max = Math.max(max,(stack.isEmpty() ? sum[i-1]:sum[i-1] - sum[stack.peek()]) * arr[j]);
            }
            stack.push(i);
        }
        //stack中还有数据
        //出现重复数据不要紧，反正是求最大的，可以出现错误的情况
        while (!stack.isEmpty()){
            int j = stack.pop();
            //最后stack中还有的数据，就是arr.length的末尾位置了
            max = Math.max(max,(stack.isEmpty() ? sum[arr.length-1]:sum[arr.length-1] - sum[stack.peek()]) * arr[j]);
        }
        return max;
    }



    public static void main(String[] args) {
        int[] arr = {4,3,4,3};
        int i = minToMax(arr);
        System.out.println(i);
    }
}
