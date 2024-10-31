package txxx.class20;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/12 21:50
 */
public class Code02_ConvertToLetterString {
    //递归
    //先考虑base case,index==str.length的时候说明得到了一个可行的方案，str[i] == '0'说明不可以转
    //然后不等于0，自己一个单转
    //不等于0，但是后面是0,就要考虑当前这个数和后面的0结合是不是大于26，是就不可以转；且前面都不是0，然后看他们相加是不是大于26
    //str[i]-'0'这个是ascii码相减，（str[i]-'0'）*10 +（str[i+1]-'0'）<27
    public static int number(String s){
        return process(s.toCharArray(),0);
    }

    private static int process(char[] str, int index) {
        if(index == str.length){
            return 1;
        }
        if (str[index] == '0') {
            return 0;
        }
        //不等于0的情况
        int ways = process(str,index+1);//一个数字转
        if(index+1<str.length &&(str[index]-'0')*10 +(str[index+1]-'0')<27){
            ways +=process(str,index+2);//这是两个数字一起转
        }
        return ways;
    }

    //从右往左动态规划
    //看朱函数index依赖于index+1，所以毫无疑问是从右往左依赖的
    public static int dp1(String s){
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n+1];
        dp[n] =1;
        for(int index = n-1;index>=0;index--){//这里怎么依赖都看主函数的可变参数怎么变
            if(str[index] != '0'){
                int ways = dp[index+1];//一个数字转
                if(index+1<str.length &&(str[index]-'0')*10 +(str[index+1]-'0')<27){
                    ways +=dp[index+2];//这是两个数字一起转
                }
                dp[index]=ways;
            }
        }
        return dp[0];
    }


   /* // 从左往右的动态规划
    // dp[i]表示：str[0...i]有多少种转化方式
    public static int dp2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        if (str[0] == '0') {
            return 0;
        }
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            if (str[i] == '0') {
                // 如果此时str[i]=='0'，那么他是一定要拉前一个字符(i-1的字符)一起拼的，
                // 那么就要求前一个字符，不能也是‘0’，否则拼不了。
                // 前一个字符不是‘0’就够了嘛？不够，还得要求拼完了要么是10，要么是20，如果更大的话，拼不了。
                // 这就够了嘛？还不够，你们拼完了，还得要求str[0...i-2]真的可以被分解！
                // 如果str[0...i-2]都不存在分解方案，那i和i-1拼成了也不行，因为之前的搞定不了。
                if (str[i - 1] == '0' || str[i - 1] > '2' || (i - 2 >= 0 && dp[i - 2] == 0)) {
                    return 0;
                } else {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
                }
            } else {
                dp[i] = dp[i - 1];
                if (str[i - 1] != '0' && (str[i - 1] - '0') * 10 + str[i] - '0' <= 26) {
                    dp[i] += i - 2 >= 0 ? dp[i - 2] : 1;
                }
            }
        }
        return dp[N - 1];
    }


    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }
    // 为了测试
    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp1(s);
            int ans2 = dp2(s);
            if (ans0 != ans1 && ans0!=ans2) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
*/
}
