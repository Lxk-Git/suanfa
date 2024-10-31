package dcgpt.class03;

import java.util.Arrays;

// 给定一个数组arr，代表每个人的能力值。再给定一个非负数k。
// 如果两个人能力差值正好为k，那么可以凑在一起比赛，一局比赛只有两个人
// 返回最多可以同时有多少场比赛
public class Code04_MaxPairNumber {

    // 暴力解
    public static int maxPairNum1(int[] arr, int k) {
        if (k < 0) {
            return -1;
        }
        return process1(arr, 0, k);
    }

    public static int process1(int[] arr, int index, int k) {
        int ans = 0;
        if (index == arr.length) {
            for (int i = 1; i < arr.length; i += 2) {
                if (arr[i] - arr[i - 1] == k) {
                    ans++;
                }
            }
        } else {
            for (int r = index; r < arr.length; r++) {
                swap(arr, index, r);
                ans = Math.max(ans, process1(arr, index + 1, k));
                swap(arr, index, r);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 时间复杂度O(N*logN)
    //弄一个窗口，L和R都从0开始
    //创建一个boolean数组，是true就代表已经用过了，而且不用两个都标记，只要标记最右边的就好了，每次标记成true的时候L和R都++就不会重复了。
    public static int maxPairNum2(int[] arr, int k) {
        if (k < 0 || arr == null || arr.length < 2) {
            return 0;
        }
        Arrays.sort(arr);
        int ans = 0;
        int N = arr.length;
        int L = 0;
        int R = 0;
        boolean[] usedR = new boolean[N];
        while (L < N && R < N) {
            if (usedR[L]) {
                L++;
            } else if (L >= R) {
                R++;
            } else { // 不止一个数，而且都没用过！
                int distance = arr[R] - arr[L];
                if (distance == k) {
                    ans++;
                    usedR[R++] = true;
                    L++;
                } else if (distance < k) {
                    R++;
                } else {
                    L++;
                }
            }
        }
        return ans;
    }

    public static int maxPairNum3(int[] arr,int k){
        if(arr==null||arr.length<1||k<0){
            return 0;
        }
        Arrays.sort(arr);
        int N=arr.length;
        int ans = 0;
        int L=0;
        int R=0;
        boolean[] flag = new boolean[N];
        while (L<N && R<N){
            //如果这个点是true就是说已经参与拳击了
            if(flag[L]){
                L++;
            }else if(L>=R){//如果两个重合了，就让R右移一个
                R++;
            }else{//都不符合，那么就是可以比较了。
                int distance = arr[R]-arr[L];
                if(distance == k){
                    ans++;
                    flag[R++] = true;
                    L++;//用过了就要加一了
                }else if(distance <k){//距离小于K，那么就还不够，让R++再比较
                    R++;
                }else {//否则就是超过了，就不要比较了。
                    L++;
                }
            }
        }
        return ans;
    }





    // 为了测试
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int maxK = 5;
        int testTime = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
            int[] arr = randomArray(N, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int k = (int) (Math.random() * (maxK + 1));
            int ans1 = maxPairNum1(arr1, k);
            int ans2 = maxPairNum3(arr2, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}
