package txxx.class20;

import java.util.HashMap;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/13 16:54
 */
public class Code03_StickersToSpellWord {
    //简单办法1，就是拿第一张纸，然后递归别的所有纸，结果加上1;拿第二张纸，递归别的所有纸，结果加1....取最小值就是答案。
    //也有情况是所有纸都拿，也不能得到答案

    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process1(String[] stickers, String target) {
        //先看base case
        //当target剩下都是都0了，那就可以结束了
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            //就是拿第一张纸，然后递归别的所有纸，结果加上1;拿第二张纸，递归别的所有纸，结果加1....取最小值就是答案。
            String rest = minus(target, first);//rest为target去掉first后剩余的
            if (rest.length() != target.length()) {//证明这张纸里面有有效的字符，那就继续递归
                min = Math.min(min, process1(stickers, rest));
            }
            //否则就是min还是等于Integer.MAX_VALUE
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    //从target减去first的字符，就是rest
    private static String minus(String target, String first) {
        char[] str1 = target.toCharArray();
        char[] str2 = first.toCharArray();
        int[] count = new int[26];
        for (char cha : str1) {//用index代表字符，count[index]代表右多少个  //有点像位图
            count[cha - 'a']++;//把target中的所有元素都拿到
        }
        for (char cha : str2) {
            count[cha - 'a']--;//把first从target元素中全部去除
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {//证明在这个i位置上有值，有几个，count[i]的值说了算。
                for (int j = 0; j < count[i]; j++) {
                    stringBuffer.append((char) (i + 'a'));//(char) (i+'a')转成原字符。
                }
            }
        }
        return stringBuffer.toString();
    }


    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        // 关键优化(用词频表替代贴纸数组)
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int minStickers21(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];//做stickers词频统计的
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        int ans = process21(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    private static int process21(int[][] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        //对target里面的词做词频统计
        char[] t = target.toCharArray();
        int[] tCounts = new int[26];
        for (char cha : t) {
            tCounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] first = stickers[i];
            //这里就是剪枝
            if (first[t[0] - 'a'] > 0) {//target的第一个字符如果在stickers的某个贴纸中，就用这个做处理
                StringBuilder builder = new StringBuilder();
                //选到了第一个就要看target去掉这个之后还要什么
                for (int j = 0; j < 26; j++) {
                    if(tCounts[j] > 0){//看看target词频统计中有哪个
                        int nums = tCounts[j] - first[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    // stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
    // 每一种贴纸都有无穷张
    // 返回搞定target的最少张数
    // 最少张数
    public static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        // target做出词频统计
        // target  aabbc  2 2 1..
        //                0 1 2..
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {//尝试第一个是谁
            // 尝试第一张贴纸是谁
            int[] sticker = stickers[i];
            // 最关键的优化(重要的剪枝!这一步也是贪心!)
            if (sticker[target[0] - 'a'] > 0) {//尝试第一个是谁
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];//总的减去第一个，nums就是剩下的
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }


}
