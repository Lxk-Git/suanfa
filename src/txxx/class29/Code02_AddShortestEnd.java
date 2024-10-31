package txxx.class29;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/27 13:19
 */
//求"abcd123321"这样的字符串包含整体的回文序列，还缺少的那一部分
public class Code02_AddShortestEnd {
    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i != str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            //当边界R扩到最右边没法扩的时候，end的值就是pArr[i]
            //比如这个例子，pArr[14]=7,end = 7
            if (R == str.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        //用原来的s长度减去end+1就是后面缺少的长度
        char[] res = new char[s.length() - maxContainsEnd + 1];
        //逆序，res从后往前面填写，str从前往后赋值
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = str[i * 2 + 1];
        }
        return String.valueOf(res);
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }
}
