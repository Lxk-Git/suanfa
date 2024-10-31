package txxx.class18;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/10 9:17
 */
public class Code02_Hanoi2 {
    //汉诺塔问题：从左移动到右边，而且必须大的环在下面，小的环在上面，不论怎么交换都不能破坏这个规则。
    //用我自己的话说，三个柱子，最左边的所有环看成两个部分，一个部分是第n个，一个部分是1到n-1个
    //从左到右分为三步
    //第一大步，将1到n-1从左移动到中
    //第二小步，将第n个从左移动到右
    //第三大步，将1到n-1从中移动到右
    //再对上面的两大步开始划分，也就是递归！每次都是分为两部分，这就很符合递归的将大问题划分为规模小的子问题，这里就是递去，然后遇到第n个直接移动的就是归来的条件。
    public static void hanoi1(int n){
        leftToRight(n);
    }
    //下面的两个大步，都要为小步服务，就是我想做的是所有的数从左边到右边，第一步n-1个数是从左边到中，第二步是第n个数（1个数）从左到有，最后一步是中间的n-1个数从中到右。整体就是从左到右的过程。
    private static void leftToRight(int n) {
        if(n == 1){
            System.out.println("将第n个数(1个数)从左移动到右");
            return;
        }
        leftToMid(n -1);
        System.out.println("将1到n-1的数(n-1个数)从左移动到右边"+":个数:"+n);
        midToRight(n-1);

    }
    private static void leftToMid(int n) {
        if(n==1){
            System.out.println("将1个数从左到中");
            return;
        }
        leftToRight(n-1);
        System.out.println("将n-1个数从左到中"+":个数:"+n);
        rightToMid(n-1);
    }

    private static void rightToMid(int n) {
        if(n==1){
            System.out.println("将1个数从右到中");
            return;
        }
        rightToLeft(n-1);
        System.out.println("将n-1个数从右到中"+":个数:"+n);
        leftToMid(n-1);
    }

    private static void midToRight(int n) {
        if(n ==1){
            System.out.println("将1个数从中到右");
            return;
        }
        midToLeft(n-1);
        System.out.println("将n-1个数从中到右"+":个数:"+n);
        leftToRight(n-1);

    }
    private static void midToLeft(int n) {
        if(n==1){
            System.out.println("将1个数从中到左");
            return;
        }
        midToRight(n-1);
        System.out.println("将n-1个数从中到左"+":个数:"+n);
        rightToLeft(n-1);
    }
    private static void rightToLeft(int n) {
        if(n==1){
            System.out.println("将1个数从右到左");
            return;
        }
        rightToMid(n-1);
        System.out.println("将n-1个数从右到左"+":个数:"+n);
        midToLeft(n-1);
    }



    //用多带几个参数的方法写递归左是from,右是to,中是other
    //还是那大体的三步
    public static void hanoi2(int n){
        func(n,"left","right","mid");
    }

    private static void func(int n, String from, String to, String other) {//左到右,最后一个参数貌似在这一步中没用，其实是按照表面的想法没用而已，参见上面的6个过程，最后一个参数总有用得上的时候
        if(n==1){
            System.out.println("1个数从左到右");
            return;
        }
        func(n-1,from,other,to);//左到中
        System.out.println(n+"个数从左到右");
        func(n-1,other,to,from);//中到右
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi1(n);
        System.out.println("============");
        hanoi2(n);
    }

}
