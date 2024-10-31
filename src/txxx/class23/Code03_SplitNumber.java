package txxx.class23;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/19 10:39
 */
//就一个拆分条件，后面的不能小于前面的，所以第一个一定是从1开始的
    //比如6，可以拆分为0,6;1,5；2,4；3,3；后面的就不行了（4,2.。。。）,且其中1,5种的5又可以分为1,4；2,3；这里的4可以分为1,3；2,2以此类推
    //这样就很明显是递归行为
public class Code03_SplitNumber {
    public static int splitNumber(int num){
        if(num<0){
            return 0;
        }
        if(num==1){
            return 1;
        }
        return process(1,num);//从1开始而不是从0开始是因为下面的first==rest时候rest-first=0就是一种办法
    }

    private static int process(int pre, int rest) {

        if(rest == 0){//rest没了就是一种方法
            return 1;
        }

        if(pre > rest){
            return 0;
        }
        int ways = 0;
        for(int first = pre;first<=rest;first++){
            ways+=process(first,rest-first);
        }
        return ways;

    }

    public static int dp(int num){
        if(num<0){
            return 0;
        }
        if(num==1){
            return 1;
        }
        int[][] dp=new int[num+1][num+1];
        for(int pre=1;pre<=num;pre++ ){
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for(int pre = num-1;pre>=1;pre--){
            for(int rest = pre+1;rest<=num;rest++){
                int ways = 0;
                for(int first = pre;first<=rest;first++){
                    ways+=dp[first][rest-first];
                }
                dp[pre][rest]= ways;
            }
        }

        return dp[1][num];
    }

    public static void main(String[] args) {
        int test = 4;
        System.out.println(splitNumber(test));
        System.out.println(dp(test));
    }
}
