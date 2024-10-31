package txxx.class26;

import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/23 14:46
 */
public class Code03_LargestRectangleInHistogram {
    //例如[4,2,5,3]答案就是8.
    //代码就莫名其妙，记住一个地方
    //使用的是单调栈，当要新压入栈的数据小于栈顶元素，那就弹出
    public static int largestRectangleArea1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();//这里就死背！
                int curArea = (i - k - 1) * height[j];//这里就死背！
                maxArea = Math.max(maxArea, curArea);
                System.out.println("i:"+i+"+height[j]:"+height[j]+"+curArea:"+curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
            System.out.println("j:"+j+"+height[j]:"+height[j]+"+curArea:"+curArea);
        }
        return maxArea;
    }

    //用数组代替栈的写法
    public static int largestRectangleArea2(int[] height){
        if (height == null || height.length == 0) {
            return 0;
        }
        int N = height.length;
        int[] stack = new int[N];
        int maxArea = 0;
        int si = -1;//这个代表栈为空
        for(int i = 0;i<N;i++){
            while (si !=-1 && height[stack[si]] >= height[i]){
                int j = stack[si--];
                int k = si == -1 ? -1 : stack[si];
                int curArea = (i-k-1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack[++si] = i;//把i位置压入stack数组,++si就是运行前先加
        }
        while (si!=-1){
            int j = stack[si--];
            int k = si == -1 ? -1 : stack[si];
            int curArea = (N-k-1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[] arr = {4,2,5,3};
        int i = largestRectangleArea2(arr);
        System.out.println(i);
    }
}
