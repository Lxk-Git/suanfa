package txxx.class39;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/28 15:28
 */
public class Code03_MSumToN {
    //就是从1开始测试，从1加到某个数字，如果大于num就是false，等于就是true
    //比如1+2+3+4+5+6+7...测试，如果加到某个数字是小于num,再加一个数字就大于num了这就失败了，否则就是成功了。
    //失败了再从2开始测试，2+3+4+5+6+7+8.....
    public static boolean isMSum1(int num){
        for(int i = 1;i<=num;i++){
            int sum =i;
            for(int j = i+1;j<=num;j++){
                if(sum + j > num){
                    break;
                }else if(sum + j == num){
                    return true;
                }else {
                    sum+=j;
                }
            }
        }
        return false;
    }

    //优化
    public static boolean isMSum2(int num){
        //怎么得到一个num最右边的1：
        //num & (~num + 1)
        return (num & (num -1) )!= 0 ; //比如8是1000,8-1是0111，相与就是0,0！=0是false
    }
    //得到2的次方是false
    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i + ":" + isMSum1(i));
        }
    }
}
