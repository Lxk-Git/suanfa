package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/13 20:32
 */
public class Code01 {
    public static void main(String[] args) {
        int a=123213;
        int b = 23232;
//        print(a);
//        print(b);
//        System.out.println("与");
//        print(a & b);
//        System.out.println("或");
//        print(a | b);
//        System.out.println("+++++++++");
//        print(a);
//        print(b);
//        System.out.println("异或");
//        print(a ^ b);

//        int aa = 1024;
//        print(aa);
//        print(aa << 1);

        int aaa = 8;
        int ddd=~aaa;
        int bbb = (~aaa +1);//位运算，取反加一
        System.out.println(aaa);
        System.out.println(ddd);

        print(ddd);
        print(9);



    }

    public static void print(int num){
        for(int i=31;i>=0;i--){
            System.out.print((num & (1 << i)) ==0 ? "0":"1");
        }
        System.out.println();
    }

}
