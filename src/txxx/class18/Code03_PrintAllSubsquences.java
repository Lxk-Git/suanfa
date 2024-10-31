package txxx.class18;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/10 10:09
 */
public class Code03_PrintAllSubsquences {
    //打印一个字符串的全部子序列
    //自己理解写的
    public static List<String> subs3(String s){
        char[] c = s.toCharArray();
        String path = null;
        List<String> ans = new ArrayList<>();
        process3(c, 0, ans, path);
        return ans;
    }
    //这个递归可以看成全部划分成到当前index，要不要index之后的字符，不要就是path(也就是自己本身)，要就是path(自己本身)+index之后的要不要的字符。
    //结束条件就是index == c.length了,ans加上走过的path，每一步都是这个条件返回
    //从头到尾理解这个递归
    //index是0，就是a,不要就是a,要的话看要几个，要一个是ab和ac,全要就是abc;index是1，就是b，不要就是b,要就是bc;index是2，就是c，不要就是c,要就是c+null还是c。index是3就结束
    //从尾到头理解这个递归
    //index是3结束；index是2，就是c,不要就是c，要是c+null还是c;index是1同理。
    private static void process3(char[] c, int i, List<String> ans, String path) {
        if(i == c.length){
            ans.add(path);
            return;
        }
        //走到index,不要index之后的字符
        //i==0,否就是a,是和否就是ab和ac,是是就是abc
        process3(c,i+1,ans,path);//否

        //走到index,要index之后的字符
        process3(c,i+1,ans,path+String.valueOf(c[i]));//是
    }


    //打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
    //同理加一个set去重就可以了
    public static List<String> subsNoRepeat1(String s){
        char[] str = s.toCharArray();
        String path = null;
        HashSet<String> set = new HashSet<>();
        List<String> ans = new ArrayList<>();
        process4(str,0,set,path);
        for(String ss:set){
            ans.add(ss);
        }
        return ans;
    }

    private static void process4(char[] str, int i, HashSet<String> set, String path) {
        if(i == str.length){
            set.add(path);
            return;
        }
        //走到index,不要index之后的字符
        //i==0,否就是a,是和否就是ab和ac,是是就是abc
        process4(str,i+1,set,path);//否
        //走到index,要index之后的字符
        process4(str,i+1,set,path+String.valueOf(str[i]));//是

    }

/*    //老师写的
    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }
    // str 固定参数
    // 来到了str[index]字符，index是位置
    // str[0..index-1]已经走过了！之前的决定，都在path上
    // 之前的决定已经不能改变了，就是path
    // str[index....]还能决定，之前已经确定，而后面还能自由选择的话，
    // 把所有生成的子序列，放入到ans里去
    public static void process1(char[] str, int index, List<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        // 要了index位置的字符
        process1(str, index + 1, ans, path + String.valueOf(str[index]));
        // 没有要index位置的字符
        process1(str, index + 1, ans, path);

    }*/

   /* //老师写的
    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }
    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, set, no);
        String yes = path + String.valueOf(str[index]);
        process2(str, index + 1, set, yes);
    }*/
    public static void main(String[] args) {
        String test = "abc";
        List<String> ans1 = subs3(test);
        List<String> ans2 = subsNoRepeat1(test);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");
    }
}
