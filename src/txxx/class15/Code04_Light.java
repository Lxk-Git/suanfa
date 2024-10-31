package txxx.class15;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/31 22:16
 */
public class Code04_Light {

    //给定字符串str，返回点亮str中所有点亮的位置至少需要几盏灯



    public static int minLight(String road){
        char[] arr = road.toCharArray();
        int i = 0;
        int light = 0;
        while (i < arr.length){
            if(arr[i] == 'X'){
                i++;
            }else {
                light ++;
                if(i+1>arr.length){
                    break;
                }else {
                    if(arr[i+1] == 'X'){
                        i = i +2;
                    }else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }
}
