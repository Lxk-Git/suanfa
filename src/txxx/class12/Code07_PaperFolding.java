package txxx.class12;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/8 20:19
 */
public class Code07_PaperFolding {
    public static void printAllFolds(int N) {
        process(1, N, true);
        System.out.println();
    }
    //i代表在第几层,N代表你想要多少层
    //当是凹的时候down为T
    //当是凸的时候down为F
    public static void process(int i ,int N,boolean down){
        if(i > N){//拿这个控制结束的退出递归的条件
            return;
        }
        process(i+1,N,true);//左凹
        System.out.println(down ? "凹":"凸");//中序遍历
        process(i+1,N,false);//右凸
    }
    public static void process2(int i ,int N,boolean down){
        if(i>N){
            return;
        }
        process2(i+1,N,true);//左凹
        System.out.println(down ? "凹":"凸");//中序遍历
        process2(i+1,N,false);//右凸
    }

    public static void main(String[] args) {
        printAllFolds(3);
    }




}
