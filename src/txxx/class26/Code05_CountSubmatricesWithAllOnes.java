package txxx.class26;


/**
 * @Author: LiuXingKun
 * @Date: 2023/2/23 16:31
 */
public class Code05_CountSubmatricesWithAllOnes {
    //压缩数组加上最大的x减去左边y和右边z中大的再乘上n(n+1)/2就是能有的子矩阵数量
    //每压缩一次就算一次，再累加就是答案。
    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int nums = 0;
        int[] height = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            nums += process(height);
        }
        return nums;
    }

    private static int process(int[] height) {
        if (height == null || height.length == 0) {
            return Integer.MIN_VALUE;
        }
        int N = height.length;
        int[] stack = new int[N];
        int si = -1;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            //此时是一定有右边的，因为都是新加！
            while (si != -1 && height[stack[si]] >= height[i]) {
                int j = stack[si--];
                //先看有没有左边
                int left = si == -1 ? -1 : stack[si];
                int n = i - left - 1;//代表符合条件的有多少列
                //注意判断，当没有左边的时候,那就减去右边的，有左边有右边就选最大的减去
                int l = left == -1 ? height[j]-height[i] : height[j] - Math.max(height[i], height[left]);
                sum += l * num(n);
            }
            stack[++si] = i;
        }
        //此时没有右边了！
        while (si != -1){
            int j = stack[si--];
            //先看有没有左边
            int left = si == -1 ? -1 : stack[si];
            int n = N - left - 1;//代表符合条件的有多少列
            //没有左边没有右边就啥都不用减去，这个时候已经只剩下左边没有右边了，那就减去左边
            int l = left == -1 ? height[j] : height[j] - height[left];
            sum += l * num(n);
        }
        return sum;
    }

    public static int num(int n) {
        return (n * (n + 1) / 2);
    }
}
