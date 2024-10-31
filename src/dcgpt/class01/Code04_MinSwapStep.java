package dcgpt.class01;

import java.util.ArrayList;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/28 16:43
 */

public class Code04_MinSwapStep {
    //
    public static int swapNum(String s){
        if(s==null || s.equals("")){
            return 0;
        }
        char[] str = s.toCharArray();
        int swapG = 0;
        int swapB = 0;
        int gi = 0;//G的下标
        int bi = 0;//B的下标
        for(int i = 0;i<str.length;i++){
            if(str[i] == 'G'){//如果i下标对应的是G，那么就swapG增加
                swapG += i - (gi++);
            }else {
                swapB += i - (bi++);
            }
        }
        return Math.min(swapG,swapB);
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("sss");
        list.add("ddd");
        if(list.contains("sss")){
            System.out.println("111");
        }
        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        System.out.println(list);
        list.remove("sss");
        System.out.println(list);
        if(list == null){
            System.out.println("1");
        }
    }
}
