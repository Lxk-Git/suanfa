package txxx.class28;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/27 16:37
 */
public class Code03_IsRotation {

    //判断str1和str2是否是旋转字符串
    //把str1+str1，然后看str2是不是新的str的子字符串，kmp算法
    
    public static boolean isRotation(String str1,String str2){
        if(str1 == null || str2 == null || str1.length() != str2.length()){
            return false;
        }
        String newStr = str1+str1;
        return kmp(newStr,str2) != -1;
    }

    private static int kmp(String newStr, String str2) {
        if(newStr == null || str2==null || newStr.length()<str2.length()){
            return -1;
        }
        char[] s1 = newStr.toCharArray();
        char[] s2 = str2.toCharArray();
        int[] next = getNextArray(s2);
        int x = 0;
        int y = 0;
        while (x<s1.length && y<s2.length){
            if(s1[x] == s2[y]){
                x++;
                y++;
            }else if(next[y] ==-1){
                x++;
            }else {
                y = next[y];
            }
        }
        return y == s2.length ? x-y:-1;
    }

    private static int[] getNextArray(char[] s2) {
        if(s2.length == -1){
            return new int[]{-1};
        }
        int[] res = new int[s2.length];
        res[0] = -1;
        res[1] = 0;
        int i = 2;
        int cn = 0;
        while (i<s2.length){
            if(s2[i-1] == s2[cn]){
                res[i++] = ++cn;
            }else if(cn >0) {
                cn = res[cn];
            }else {
                res[i++] = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyun";
        System.out.println(isRotation(str1, str2));

    }
}
