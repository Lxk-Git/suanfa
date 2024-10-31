package dcgpt.class03;

import java.util.HashSet;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/22 10:24
 */
public class Code02_HowManyTypes {
    /*
     * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，
     * 如果其中某两个字符串，所含有的字符种类完全一样，就将两个字符串算作一类 比如：baacba和bac就算作一类
     * 虽然长度不一样，但是所含字符的种类完全一样（a、b、c） 返回arr中有多少类？
     *
     */
    //创建一个hashSet，里面存的是Integer类型的数字
    //把数组中的所有字符都用位运算计算一下，再加入到hashSet中，就可以把重复的去除了。
    public static int types1(String[] arr) {
        HashSet<Integer> types = new HashSet<>();
        for (String str : arr) {
            //每个字符都换成char数组
            char[] chs = str.toCharArray();
            int key = 0;
            for(int i = 0 ; i < chs.length;i++) {
                //数组中的每个字母都去做位运算
                //这里有个技巧，就是让1左移n位，再与key相或
                //比如str是“abc”,chs=['97','98','99']
                //(chs[i] - 'a')就是0
                //1 << (chs[i] - 'a')就是1左移0位
                //key |= (1 << (chs[i] - 'a'))就是0或1，那么key还是01
                //for循环b和c得到key等于011,0111，最后结束key=7.
                //这里就算是baacba，他们做完这个运算，最后还是得到7。这个运算只会管到char是谁，不会关心有多少个char。
                key |= (1 << (chs[i] - 'a'));
            }
            types.add(key);
        }
        return types.size();
    }

    public static int types2(String[] str){
        HashSet<Integer> hashSet = new HashSet<>();
        for(String s:str){
            char[] cha = s.toCharArray();
            int key = 0;
            for(int i = 0;i<cha.length;i++){
                key |= (1<<(cha[i] - 'a'));
            }
            hashSet.add(key);
        }
        return hashSet.size();
    }



}
