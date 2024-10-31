package txxx.class39;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/28 10:58
 */
public class Code01_AppleMinBags {
    //大的优先，那就是先看8的能不能满足，不能满足就减去一个八，剩下的加上一个八，如果最后还是不能装满，就是-1
    public static int minD(int apple){
        if(apple == 0){
            return -1;
        }
        int app8 = apple / 8;//向下取整，向上取整是 (apple + (8-1))/8
        int rest = apple - (app8 * 8);

        while (app8 >=0){//这里要可以等于0，因为等于6的时候，可以用6的袋子装
            if(rest % 6 == 0){
                return app8 + (rest / 6);
            }else {
                app8 --;
                rest +=8;
            }
        }
        return -1;
    }

    public static int youHuaMinD(int apple){
        if((apple & 1 )  != 0){ //是奇数就是-1
            return -1;
        }
        if(apple < 18){
            if(apple ==0 ){
                return 0;
            }else if(apple==6 || apple ==8){
                return 1;
            }else if(apple==12||apple==14||apple==16){
                return 2;
            }else {
                return -1;
            }
        }
        return (apple - 18)/8 +3;
    }




    //再看看有啥规律，再优化一波
    //18之后奇数的是1，大于18的，没加八个都是加一个袋子。(apple - 18)/8 +3
    public static void main(String[] args) {
        for(int i =1;i<=200;i++){
            System.out.println(i +":"+minD(i));
        }
    }

}
