package txxx.class14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/13 11:11
 */
public class Code05_LowestLexicography {
    //写个比较器
    //对于字符串a+b < b+a ，那么一定有a<b
    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestLexicography(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String res = null;
        Arrays.sort(strs, new MyComparator());//对strs中的字符串按照字典序排好了
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }

    //作用是提出任意一个字符串，并把接下来的数据放在数组中
    // {"abc", "cks", "bct"}
    // 0 1 2
    // removeIndexString(arr , 1) -> {"abc", "bct"}
    public static String[] removeIndexString(String[] arr, int index) {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for (int i = 0;i<N;i++){
            if(i!=index){
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }
    // strings中所有字符串全排列，返回所有可能的结果
    public static TreeSet<String> process(String[] strings){
        TreeSet<String> res = new TreeSet<>();
        if(strings == null){
            res.add("");
            return res;
        }
        for(int i = 0; i<strings.length;i++){
            String first = strings[i];
            String[] nexts = removeIndexString(strings,i);
            TreeSet<String> next = process(nexts);
            for(String cur : next ){
                res.add(first + cur);
            }
        }
        return res;
    }
    public static String lowestString1(String[] strings){
        if(strings == null || strings.length == 0){
            return "";
        }
        TreeSet<String> process = process(strings);
        return process == null ? "" : process.first();
    }
}
