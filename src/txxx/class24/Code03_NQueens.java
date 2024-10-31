package txxx.class24;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/20 18:00
 */
public class Code03_NQueens {
    //创建一个record一维数组，index代表n*n的棋盘的行，值代表皇后放在的列
    //当index能顺利到说明是一种可行的办法
    public static int nQueens(int n){
        if(n<1){
            return 0;
        }
        int[] record = new int[n];
        return process(0,record,n);
    }

    private static int process(int i, int[] record, int n) {
        if (i==n){
            return 1;
        }
        int res =0;
        //看每一行的皇后放在哪一列上，j列上
        for(int j = 0;j<n;j++){
            if(panDuan(record,i,j)){//判断是不是符合条件，是就放进去，不是就结束
                record[i] = j;//把j列坐标放在record的i上，就知道第i行的皇后放在哪一列上。
                res += process(i+1,record,n);
            }
        }
        return res;
    }

    private static boolean panDuan(int[] record, int i, int j) {
        for(int k =0;k<i;k++){
            //j == record[k] 相等就是同一列，
            //Math.abs(record[k] -j) == Math.abs(i-k)是看是不是对角线上
            if(j == record[k] || Math.abs(record[k] -j) == Math.abs(i-k)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int n = 4;

        long start = System.currentTimeMillis();
        System.out.println(nQueens(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");


    }
}
