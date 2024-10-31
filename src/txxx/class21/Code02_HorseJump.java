package txxx.class21;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/15 10:20
 */
public class Code02_HorseJump {

    //是一个10*9的棋盘，从0,0出发，走rest步到a,b有多少个方法
    public static int jump(int a,int b,int rest){
        return process(0,0,rest,a,b);
    }
    //情况1，rest已经是0了，刚好在a,b上，就是一种方法，否则就失败
    //然后就是从x,y出发，可以是任何点，都有8种情况，就要注意一下越界问题
    private static int process(int x, int y,int rest,int a, int b) {
        if(x<0 || x>9 || y<0||y>8){
            return 0;//越界了失败
        }
        if(rest == 0){
            return (x == a && y == b) ? 1 : 0;
        }
        int ways = process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);
        return ways;
    }

    //这个dp有点不一样，有3个可变参数 rest是层
    public static int dp(int a,int b,int k){
        int[][][] dp = new int[10][9][k+1];
        dp[a][b][0] = 1;//rest是0的时候，a,b的值为1，别的都是0
        for(int rest=1;rest<=k;rest++){//从第0层到第rest层
            for(int x = 0;x<10;x++){//x和y都在确定范围之内
                for(int y = 0;y<9;y++){
                    //注意越界问题
                    int ways = f(dp,x + 2, y + 1, rest - 1);
                    ways += f(dp,x + 1, y + 2, rest - 1);
                    ways += f(dp,x - 1, y + 2, rest - 1);
                    ways += f(dp,x - 2, y + 1, rest - 1);
                    ways += f(dp,x - 2, y - 1, rest - 1);
                    ways += f(dp,x - 1, y - 2, rest - 1);
                    ways += f(dp,x + 1, y - 2, rest - 1);
                    ways += f(dp,x + 2, y - 1, rest - 1);
                    dp[x][y][rest]= ways;
                }
            }
        }
        return dp[0][0][k];
    }
    public static int f(int[][][] dp,int x,int y,int k){
        if(x<0 || x>9 || y<0||y>8){
            return 0;
        }
        return dp[x][y][k];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jump(x, y, step));
        System.out.println(dp(x, y, step));
    }
}
