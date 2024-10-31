package txxx.class29;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/27 10:11
 */
public class Code01_Manacher {
    //先将原始数组加上#处理一下
    public static int manacher(String s) {
        if (s == null) {
            return 0;
        }
        char[] str = manacherString(s);
        //设置好回文半径数组
        int[] pArr = new int[str.length];
        //设置中心位置，一开始为-1
        int C = -1;
        //设置最右边位置R，一开始也为-1，在代码中R是满足回文区域的下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            //如果回文范围R<=i的时候，那么至少也是有一个的，比如#1#2#3#2#1#,比如第2个#，R是等于2，i=2这个时候这个这个#在回文半径数组中pArr[2] = 1;
            //R>i时候，就会出现那三个小情况，那么就选最小的。pArr[2*C-i]就是i'的位置。
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            //i + pArr[i]就是i点为中心，往右推动pArr[i]个位置，当没有越界的时候
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                //当以i为中心，加减pArr[i]个位置都是相等的时候，pArr[i]就是回文长度加+1
                if(str[i+pArr[i]] == str[i-pArr[i]]){
                    pArr[i]++;
                }else {//否则不合适就直接结束
                    break;
                }
            }
            //当越界情况退出之后，要把R的位置向右扩，C也要变化
            if(i+pArr[i] >R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max,pArr[i]);
        }
        return max -1;


    }

    private static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        char[] res = new char[str.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : str[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "12321";
        int manacher = manacher(s);
        System.out.println(manacher);
    }
}
