package dcgpt.class03;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/21 13:38
 */
// 本题测试链接 : https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class Code01_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        //str[i]是ascii码，i是下标,比如aaacaba,中i=0,str[0] = 97,a的ascii码就是97.str[0]就是代表a,str[1]=97,还是代表a
        char[] str = s.toCharArray();
        //map[i] = v i这个ascii码的字符，上次出现的位置。
        int[] map = new int[256];
        //初始化的时候，全部都放成-1,

        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        //str[0]这个ascii码的字符上次出现的位置是0.
        //map[str[0]]=0就是map[97]=0,map[str[1]]=0,还是map[97]=0
        map[str[0]] = 0;
        int N = str.length;
        int ans = 1;
        //前面的字符能有多少长度。
        int pre = 1;
        //第0个字符的长度ans=1和前面的长度pre=1都是1,所以从第1个字符开始for循环
        for (int i = 1; i < N; i++) {
            /*pre = Math.min(i - map[str[i]], pre + 1);
            ans = Math.max(ans, pre);*/
            //str[i]是当前字符，map[str[i]]是上次出现的位置，i减去上次出现的位置就是第一种情况，（当前字符上次的位置）
            int p1 = i-map[str[i]];
            //pre是前面i-1位置的字符，它到上一次出现它的距离；用这个pre加上1就得到情况2.（i-1位置上的字符到i-1位置上的字符上次出现的距离，再加上1就是当前字符符合的范围。）
            int p2 = pre +1;
            //两种情况，为了没有重复，取最小的
            int cur = Math.min(p1,p2);
            //答案是从每种情况中选最大的。
            ans = Math.max(cur,ans);
            //再把当前的cur变成下一个字符的上一个结果pre，这里就是动态规划，把前面的答案都放在pre中
            pre = cur;
            //再将str[i]这个ascii码的字符出现的位置放入map中,
            map[str[i]] = i;
        }
        return ans;
    }

    public static int lengthOfLongestSubstring2(String s){
        if(s==null || s.equals(" ")){
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for(int i=0;i<256;i++){
            map[i] = -1;
        }
        map[str[0]] = 0;
        int N = str.length;
        int ans = 1;
        int pre = 1;
        for(int i = 1;i<N;i++){
            pre = Math.min(i-map[str[i]],pre+1);
            ans = Math.max(ans,pre);
            map[str[i]] =i;
        }
        return ans;
    }



    public static void main(String[] args) {
        String s = "aaacba";
        int i = lengthOfLongestSubstring2(s);
        System.out.println(i);
    }
}
