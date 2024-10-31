package dcgpt.class01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/28 14:13
 */
public class Code01_CordCoverMaxPoint {
    //第一种方法
    //把arr[i]中的每个点都看成是绳子的末尾点，看这个点往左能盖住多少个点，要查询能盖住多少个点可以用二分查询法
    // 比如arr[2,3,4,8,14,15,16,17,18]，L=4,当i=0，arr[i]=2，需要二分查询的范围就是arr[i]-L=-2。二分查询一下R是0，在2的时候可以有效位置的下标是哪个（可以包含本身）。

    public static int maxPoint1(int[] arr, int L) {
        if (arr == null || arr.length < 0 || L < 0) {
            return 0;
        }
        //不为空至少有一个
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest  = nearestIndex(arr, i, arr[i] - L);
            //查询到了有效范围，再用i-arr中第nearest个有效范围 +1就能得到有多少个点符合。
            //比如上面这个例子，14的下标是4，通过二分查询方法，得到下标4的右边有效位置是在下标4的位置，那就用4-4+1得到只有一个数字符合需求。
            res = Math.max(res, i - nearest  + 1);//加一是因为右边有效位置包含本身了，所以相减之后要加一
        }
        return res;
    }

    //在arr中查询以value值为区间，以arr[i]为末尾点，他的右边有效位置是哪个下标（可以包含本身）。
    //这里的L和R都是arr的下标，看他们的中点，arr[mid]的值是不是大于value。
    //有效范围设置成index,一开始有效的范围默认是R，以R向左推，不能小于0.
    //用二分，查询arr[mid]中点的值是不是大于arr[i]-L的value，是就说明这个R范围有点大了，应该往左缩小，index有效范围就变成了mid,但是最多到0.
    //如果arr[mid]中点的值小于arr[i]-L的value，说明L范围在左边有点大了，应该往右边缩小，但是L不能大于R，最多等于R。
    private static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }
    public static int maxPoint2(int[] arr,int L){
        if (arr == null || arr.length < 0 || L < 0) {
            return 0;
        }
        int res = 1;
        for(int i = 0;i<arr.length;i++){
            int next = nearestIndex2(arr,i,arr[i]-L);
            res = Math.max(res,i-next+1);
        }
        return res;
    }

    private static int nearestIndex2(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L<=R){
            int mid = L + ((R-L) >> 1 );
            if(arr[mid] > value){
                index = mid;
                R = mid -1;
            }else {
                L =mid +1;
            }
        }
        return index;
    }


    //第二种方法
    //用窗口，绳子的头从L位置出发，看他从左到右能包含的点数有多少个。
    //就是一个L一个R，用arr[R]-arr[L]<=L就是符合的情况
    public static int maxPoint3(int[] arr,int L){
        int left=0;
        int right=0;
        int max = 0;
        int N=arr.length;
        while (left<N){
            while (right<N && arr[right] - arr[left] <=L){
                right ++;
            }
            max = Math.max(max,right-(left++));
        }
        return max;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int[] arr = {2,3,4,8,14,15,16,17,18};
        int i = maxPoint2(arr,4);
        int h = maxPoint3(arr,4);
        System.out.println(i);
    }
}
