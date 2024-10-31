package txxx.class26;

import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/23 15:13
 */
public class Code04_MaximalRectangle {
    //数组压缩技巧，把这个二维数组压缩成一个一维的
    //看有道云笔记
    //时间复杂度O（n的平方）
    public static int maximal(int[][] arr){
        if(arr==null || arr.length ==0){
            return -1;
        }
        int max = Integer.MIN_VALUE;
        //压缩数组
        int[] height = new int[arr[0].length];
        for(int i = 0;i<arr.length;i++){//行
            for(int j = 0;j<height.length;j++){//列
                height[j] = arr[i][j] == 0 ? 0 : height[j] +1;//height是列
            }
            max = Math.max(max,process(height));//对每一行进行对比
        }
        return max;
    }
    //代码就硬背好吧
    private static int process(int[] height) {
        if(height == null || height.length == 0){
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ;i<height.length;i++){
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]){
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int maxV = (i-k-1) * height[j];
                max = Math.max(max,maxV);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int maxV = (height.length-k-1) * height[j];
            max = Math.max(max,maxV);
        }
        return max;

    }


    public static void main(String[] args) {
        int[][] arr = {{1,1,1,1},{1,1,1,1},{1,0,1,1},{1,1,0,1}};
        int maximal = maximal(arr);
        System.out.println(maximal);
    }
}
