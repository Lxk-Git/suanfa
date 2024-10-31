package dcgpt.class04;

/**
 * @Author: LiuXingKun
 * @Date: 2023/4/5 20:42
 */
public class Code05_CandyProblem {

    //技巧就是从左往右做一个和原来等长的数组，可以把他们全部初始化值为1，也可以在最后直接加上原始数组的N，然后从左往右对比，大的加一，小就不变
    //再做一个从右往左的，大的加一，小的不变
    //两个数组对比，大的就是需要的
    public static int candy1(int[] arr){
        if(arr==null || arr.length<0){
            return 0;
        }
        int N = arr.length;
        int[] left = new int[N];
        for(int i=1;i<arr.length;i++){
            if(arr[i-1]<arr[i]){
                left[i] = left[i-1]+1;
            }
        }
        int[] right = new int[N];
        for(int i=N-2;i>=0;i--){
            if(arr[i+1] <arr[i]){
                right[i] = right[i+1]+1;
            }
        }
        int ans = 0;
        for(int i =0;i<N;i++){
            ans += Math.max(left[i],right[i]);
        }
        //这里是因为left和right初始化都是0，所以结果需要加上N;
        return ans +N;
    }

    //加强版
    //在原问题的基础上，增加一个原则：
    //相邻的孩子间如果分数一样，分的糖果数必须一样
    //返回至少需要分多少糖

    //那就变成，
    // ①右边大，那么就加一
    // ②一样大，就不变
    // ③小于就是1
    public static int candy2(int[] arr){
        if(arr==null || arr.length<0){
            return 0;
        }
        int N = arr.length;
        int[] left = new int[N];
        for(int i=1;i<arr.length;i++){
            if(arr[i-1]<arr[i]){
                left[i] = left[i-1]+1;
            }else if(arr[i-1] == arr[i]) {
                left[i] = left[i-1];
            }
        }
        int[] right = new int[N];
        for(int i=N-2;i>=0;i--){
            if(arr[i+1] <arr[i]){
                right[i] = right[i+1]+1;
            }else if(arr[i+1] ==arr[i]){
                right[i] = right[i+1];
            }
        }
        int ans = 0;
        for(int i =0;i<N;i++){
            ans += Math.max(left[i],right[i]);
        }
        //这里是因为left和right初始化都是0，所以结果需要加上N;
        return ans +N;
    }

    public static void main(String[] args) {
        int[] arr= {1,2,2};
        int i = candy2(arr);
        System.out.println(i);
        int i1 = candy1(arr);
        System.out.println(i1);
    }
}
