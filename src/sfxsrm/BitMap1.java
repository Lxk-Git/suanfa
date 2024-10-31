package sfxsrm;

import java.util.HashSet;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/25 10:13
 */
public class BitMap1 {
    //位图就是可以将数据存入bit中，当然也可以删除和查询
    public static class BitMap{
        public long bits[];//创建一个长整形，8个字节，64bit的数组。
        //首先传入一个数，要看他需要多少个bit位来存。
        public BitMap(int max){//带一个参数的构造方法
            bits = new long[(max+64) >> 6];//max+64是为了当传入一个0的时候也能存，右移6位就是 / 64(也就是2的6次方)
        }
        //把传入的一个数放入到位图中
        public void add(int num){
            //先看这个传进来的数字应该存在arr的哪个元素i上
            //再看这个数字应该存在哪个bit位上
            //(num >> 6) 得到这个数字应该存在arr的哪个元素上
            // (num & 63)取余相当于(num % 64)
            // 1是int型，1L是long型，这里的数组是long型，所以要用1L
            // 1L << (num & 63) 就是让1左移(num & 63)位
            // 再和bits[(num >> 6)]相 | ,最后bits[(num >> 6)]的第num & 63)个位置变成1,num就加入进去了
            int i = num & 63;
            long l = 1L << i;
            //存进来的数可不是原来的数，是位运算变化过的数。
            //刚刚第一次加的时候当然是0，然后累加，比如第一次i=2,l=4,相与之后l1就变成了[0,0,4,0....]
            //之后就是累加了，i=3,l=8,l1就变成了[0,0,12,0...]
            //总之全部都是在（num >> 6）位上做加减的。
            long l1 = bits[(num >> 6)];
            bits[(num >> 6)] |= (1L << (num & 63));
        }
        //把位图中的数据删除
        public void delete(int num){
            //先拿到那个bit位置，1变成0取反，再相 & ,就变成了0删除掉了

            int i = num & 63;
            long l = 1L << i;
            long l1 = bits[(num >> 6)]; //一直都是0.
            bits[(num >> 6)] &= ~(1L << (num & 63));
        }
        //再查询这个数字在不在 如果为0就是不存在，不为0就是存在
        public boolean contains(int num){
            return (bits[(num >> 6)] & (1L << (num & 63))) !=0;
        }

    }

    public static void main(String[] args) {
        int max = 10000;
        BitMap bitMap = new BitMap(max);
        bitMap.add(130);
        bitMap.add(131);
        bitMap.delete(130);
        boolean b = bitMap.contains(130);
        boolean b1 = bitMap.contains(131);
//        System.out.println("测试开始！");
//        int max = 10000;
//        BitMap bitMap = new BitMap(max);
//        HashSet<Integer> set = new HashSet<>();
//        int testTime = 10000000;
//        for (int i = 0; i < testTime; i++) {
//            int num = (int) (Math.random() * (max + 1));
//            double decide = Math.random();
//            if (decide < 0.333) {
//                bitMap.add(num);
//                set.add(num);
//            } else if (decide < 0.666) {
//                bitMap.delete(num);
//                set.remove(num);
//            } else {
//                if (bitMap.contains(num) != set.contains(num)) {
//                    System.out.println("Oops!");
//                    break;
//                }
//            }
//        }
//        for (int num = 0; num <= max; num++) {
//            if (bitMap.contains(num) != set.contains(num)) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("测试结束！");
    }



}
