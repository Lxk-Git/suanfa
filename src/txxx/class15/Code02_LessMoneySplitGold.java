package txxx.class15;

import java.util.PriorityQueue;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/31 15:22
 */
public class Code02_LessMoneySplitGold {

    //用贪心算法解决输入一个数组返回金条分割的最小代价
    //用小根堆反向求解就能得到最小代价
    //先将所有的数据插入小根堆，系统自带的小根堆是排序好的。然后将小根堆上面的两两弹出，再相交，在插入小根堆，当堆中只有一个数据的时候停止，所有参与过相加的数字就是最小的代价
    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0;i<arr.length;i++){
            priorityQueue.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (priorityQueue.size() >1){
            cur = priorityQueue.poll() + priorityQueue.poll();
            sum +=cur;
            priorityQueue.add(sum);
        }
        return sum;

    }
}
