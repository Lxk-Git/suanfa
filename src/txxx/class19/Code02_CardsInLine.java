package txxx.class19;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/12 0:10
 */
public class Code02_CardsInLine {
    //一个先手一个后手，都是绝顶聪明，那就创建两个函数
    //先手是f,他只能拿左边或者是右边
    //后手是g,先手拿了L，他可以选择的就只有L+1到R，先手拿了R，他就只能选L到R-1

    public static int win1(int[] arr){
        int f1 = f1(arr,0,arr.length-1);
        int f2 = g1(arr,0,arr.length-1);
        return Math.max(f1,f2);
    }
    private static int f1(int[] arr, int l, int r) {
        //先写先手结束的条件
        if(l==r){
            return arr[l];//如果只剩下一个了，先手当然有的拿
        }
        //先手可以拿左或者右，后手就在这后面选了，先手取最大值
        int p1 = arr[l]+g1(arr,l+1,r);
        int p2 = arr[r]+g1(arr,l,r-1);
        return Math.max(p1,p2);
    }

    private static int g1(int[] arr, int l, int r) {
        //再写后手结束的条件
        if(l==r){
            return 0;//后手没得拿。
        }
        int p1 = f1(arr,l+1,r);//对方拿了l位置
        int p2 = f1(arr,l,r-1);//对方拿了R位置
        //因为这里是和先手博弈，对方是不可能给后手好的选择的，但是先手拿左还是右两种情况都考虑的情况下会有一个大的和小的，先手一定会会给后手选一个小的。
        //所以这里能给后手选的只有一个小的。
        return Math.min(p1,p2);
    }

    //现在想他们之间哪个时间出现了重复的地方
    //现在发现了有重复的地方，然后给先手设置一个dp,后手也设置一个dp，存在的时候直接拿缓存，不在的时候就存进去
    public static int win2(int[] arr){
        int n = arr.length;
        int[][] fdp = new int[n][n];
        int[][] gdp = new int[n][n];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                fdp[i][j] = -1;
                gdp[i][j] = -1;
            }
        }
        int f1 = f2(arr,0,arr.length-1,fdp,gdp);
        int f2 = g2(arr,0,arr.length-1,fdp,gdp);
        return Math.max(f1,f2);
    }

    private static int f2(int[] arr, int l, int r, int[][] fdp, int[][] gdp) {
        //先看fdp缓存中有没有,有就返回
        if(fdp[l][r] != -1){
            return fdp[l][r];
        }
        //没有就查询，并存进缓存
        int ans = 0;
        if(l == r){
            ans = arr[l];
        }else {
            int p1 = arr[l] + g2(arr,l+1,r,fdp,gdp);
            int p2 = arr[r] + g2(arr,l,r-1,fdp,gdp);
            ans = Math.max(p1,p2);
        }
        fdp[l][r] = ans;
        return ans;
    }

    private static int g2(int[] arr, int l, int r, int[][] fdp, int[][] gdp) {
        if(gdp[l][r] != -1){
            return gdp[l][r];
        }
        //没有就查询，并存进缓存
        int ans = 0;
        if(l != r){
            int p1 = f2(arr,l+1,r,fdp,gdp);
            int p2 = f2(arr,l,r-1,fdp,gdp);
            ans = Math.min(p1,p2);
        }
        gdp[l][r] = ans;
        return ans;

    }

    //fdp和gdp是相互依赖的
    public static int win3(int[] arr){
        int n = arr.length;
        int[][] fdp = new int[n][n];
        int[][] gdp = new int[n][n];
        for(int i = 0;i<n;i++){
            fdp[i][0] = arr[i];// f1的l==r得来的
        }
        //从g1的l==r也能得到所有的gdp[i][0]位置都是0所以就不必初始化了
        for(int start = 1;start<n;start++){//注意边界问题
            int l= 0;
            int r = start;
            while (r<n){
                fdp[l][r] = Math.max((arr[l] + gdp[l+1][r]),(arr[r] + gdp[l][r-1]));
                gdp[l][r] = Math.min((fdp[l+1][r]),(fdp[l][r-1]));
                l++;
                r++;
            }
        }
        return Math.max(fdp[0][n-1],gdp[0][n-1]);
    }




    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }



}
