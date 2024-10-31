package dcgpt.class05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: LiuXingKun
 * @Date: 2023/4/8 12:54
 */
public class Code04_DeleteMinCost {

    // 题目：
    // 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
    // 比如 s1 = "abcde"，s2 = "axbc"
    // 返回 1

    // 解法一
    // 求出str2所有的子序列，子序列就是要和不要的背包问题，然后按照长度排序，长度大的排在前面。
    // 然后考察哪个子序列字符串和s1的某个子串相等(KMP)，答案就出来了。
    // 分析：
    // 因为题目原本的样本数据中，有特别说明s2的长度很小。所以这么做也没有太大问题，也几乎不会超时。
    // 但是如果某一次考试给定的s2长度远大于s1，这么做就不合适了。
    public static int minCost1(String s1, String s2) {
        List<String> s2Subs = new ArrayList<>();
        process(s2.toCharArray(), 0, "", s2Subs);
        s2Subs.sort(new LenComp());
        for (String str : s2Subs) {
            if (s1.indexOf(str) != -1) { // indexOf底层和KMP算法代价几乎一样，也可以用KMP代替
                return s2.length() - str.length();
            }
        }
        return s2.length();
    }

    public static void process(char[] str2, int index, String path, List<String> list) {
        if (index == str2.length) {
            list.add(path);
            return;
        }
        process(str2, index + 1, path, list);
        process(str2, index + 1, path + str2[index], list);
    }


    public static class LenComp implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }

    }

    public static int minCost2(String s1, String s2) {
        List<String> list = new ArrayList<>();
        process2(s2.toCharArray(),0,"",list);
        list.sort(new LenComp2());
        for(String s:list){
            if(s.indexOf(s1) !=-1){
                return s2.length() - s.length();
            }
        }
        return s2.length();
    }

    private static void process2(char[] str, int index, String path, List<String> list) {
        if(index==str.length){
            list.add(path);
            return;
        }
        process2(str,index+1,path,list);
        process2(str,index+1,path+str[index],list);
    }

    public static class LenComp2 implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return o2.length()-o1.length();
        }
    }

}
