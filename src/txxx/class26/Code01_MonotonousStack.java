package txxx.class26;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/22 22:23
 */
public class Code01_MonotonousStack {
    //没有重复值的情况
    //返回值是一个N行2列的数组
    //N代表的是原来arr中第N个数字，2列的0代表N左边离他最近最小的点，1代表N右边离他最近最小的点
    //注意：二维数组中存的是下标不是数值
    // arr = [ 3, 1, 2, 3]
    //         0  1  2  3
    //  [
    //     0 : [-1,  1]
    //     1 : [-1, -1]
    //     2 : [ 1, -1]
    //     3 : [ 2, -1]
    //  ]
    public static int[][] getNearLessNoRepeat(int[] arr){
        int[][] ans = new int[arr.length][2];
        //创建一个栈
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<arr.length;i++){
            //正常进入弹出栈的情况
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int j = stack.pop();
                int leftMinNum = stack.isEmpty() ? -1 : stack.peek();
                ans[j][0] = leftMinNum;
                ans[j][1] = i;
            }
            stack.push(i);
        }
        //所有的数字都进入栈了，但是栈里面的数据还没有全部弹出
        while (!stack.isEmpty()){
            int j = stack.pop();
            int leftMinNum = stack.isEmpty() ? -1 : stack.peek();
            ans[j][0] = leftMinNum;
            ans[j][1] = -1;
        }
        return ans;
    }
    //有重复值的情况，把重复值加到一个list后面
    public static int[][] getNearLessNoRepeat2(int[] arr){
        int[][] ans = new int[arr.length][2];
        //创建一个栈
        Stack<List<Integer>> stack = new Stack<>();
        for(int i = 0;i<arr.length;i++){
            //正常进入弹出栈的情况
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){//重复的值都放在一个list里面，选择里面的第一个和要新进栈的数据比较
                List<Integer> pop = stack.pop();//把整个List弹出
                int leftMinNum = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);//取下面压着的list的最后一个
                for(int j :pop){//list中的数值都是相等的，所以他们的左边最接近小于他的数，和右边最接近小于他的数都是一样的。
                    ans[j][0] = leftMinNum;
                    ans[j][1] = i;
                }
            }
            List<Integer> list = new ArrayList<>();
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){//相等的情况，把i加入到上层的list中
                stack.peek().add(Integer.valueOf(i));
            }else {//为空的情况或者小于的情况，直接加进去
                list.add(i);
                stack.add(list);
            }
        }
        //所有的数字都进入栈了，但是栈里面的数据还没有全部弹出
        while (!stack.isEmpty()){
            List<Integer> pop = stack.pop();
            int leftMinNum = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
            for(int j :pop){
                ans[j][0] = leftMinNum;
                ans[j][1] = -1;
            }
        }
        return ans;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }


    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }
    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            /*if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }*/
            if (!isEqual(getNearLessNoRepeat2(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
