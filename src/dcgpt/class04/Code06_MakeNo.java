package dcgpt.class04;

/**
 * @Author: LiuXingKun
 * @Date: 2023/4/6 14:56
 */
public class Code06_MakeNo {

    // 生成长度为size的达标数组
    // 达标：对于任意的 i<k<j，满足 [i] + [j] != [k] * 2
    //分治思想，把左边弄好，右边弄好，再看整体
    //这个题目就是比如7个数字，向上取整就是4,4个时候搞定2个就好了，2个的时候搞定1个，
    // 比如划分到最后arr只有一个数字为1，那么要到达到数组中任意左加右不等于2倍的中，
    // 那么生产2个数的arr,就是[1*2,1*2+1],长度为2的生成4的就是[2*2,3*2,2*2+1,3*2+1]，4的生成8的[8,12,10,14,9,13,11,15]，但是只要长度为7，只要去掉一个15就好了
    public static int[] makeNo(int size) {
        if (size == 1) {
            //第一个数可以是1，也可以是别的
            return new int[] { 1 };
        }
        // size
        // 一半长达标来
        // 7 : 4
        // 8 : 4
        // [4个奇数] [3个偶]
        int halfSize = (size + 1) / 2;
        //递归弄到最小的时候
        int[] base = makeNo(halfSize);
        // base -> 等长奇数达标来
        // base -> 等长偶数达标来
        int[] ans = new int[size];
        int index = 0;
        //分治，先弄一边
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 ;
        }
        //再弄另外一边。i就是左边的，index是接着上面的一起的
        for (int i = 0; index < size; index++, i++) {
            ans[index] = base[i] * 2 +1;
        }
        return ans;
    }

    public static int[] makeNo2(int size){
        if(size == 1){
            return new int[] {1};
        }
        int halfSize = (size +1)/2;//向上取整
        int[] base = makeNo2(halfSize);
        int[] ans = new int[size];
        int index = 0;
        for(;index<halfSize;index++){
            ans[index] = base[index] *2;
        }
        for(int i = 0;index<size;index++,i++){
            ans[index] = base[i] *2 +1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = makeNo2(7);
        for(int i:ints){
            System.out.println(i);
        }
    }
}
