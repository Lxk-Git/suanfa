package txxx.class27;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/24 10:23
 */
public class Code02_FibonacciProblem {
    //递归实现菲波那切数列 O（n）
    public static int f1(int n){
        if(n<0){
            return 0;
        }
        if(n ==1){
            return 1;
        }
        if(n==2){
            return 1;
        }
        return f1(n-1) + f1(n-2);
    }
    //非递归实现O(n)
    public static int f2(int n){
        if(n<0){
            return 0;
        }
        if(n ==1){
            return 1;
        }
        if(n==2){
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for(int i=3;i<=n;i++){
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    //O(lngN)
    public static int f3(int n){
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {
                { 1, 1 },
                { 1, 0 }
        };
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }
    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        // res = 矩阵中的1
        int[][] t = m;// 矩阵1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = product(res, t);
            }
            t = product(t, t);
        }
        return res;
    }

    // 两个矩阵乘完之后的结果返回
    public static int[][] product(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length; // a的列数同时也是b的行数
        int[][] ans = new int[n][m];
        for(int i = 0 ; i < n; i++) {
            for(int j = 0 ; j < m;j++) {
                for(int c = 0; c < k; c++) {
                    ans[i][j] += a[i][c] * b[c][j];
                }
            }
        }
        return ans;
    }

    //题目⑤字符串问题
    public static int s1 (int n){
        if(n<0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        return s1(n-1)+s1(n-2);
    }

    public static int s2 (int n){
        if(n<0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        int res = 2;
        int pre = 1;
        int tmp =0;
        for(int i =3;i<=n;i++){
            tmp =res;
            res = res+pre;
            pre = tmp;
        }
        return res;
    }
    public static int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = { { 1, 1 }, { 1, 0 } };
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }
    //小母牛问题
    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    public static int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre1 = 2;
        int pre2 = 1;
        int tmp =0 ;
        for(int i=4;i<=n;i++){
            tmp = res;
            res = res +pre2;
            pre2 = pre1;
            pre1 = tmp;
        }
        return res;

    }
    public static int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {
                { 1, 1, 0 },
                { 0, 0, 1 },
                { 1, 0, 0 } };
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }
    public static void main(String[] args) {
        int n = 19;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
        System.out.println("===");

        System.out.println(s1(n));
        System.out.println(s2(n));
        System.out.println(s3(n));
        System.out.println("===");

        System.out.println(c1(n));
        System.out.println(c2(n));
        System.out.println(c3(n));
        System.out.println("===");

    }


}
