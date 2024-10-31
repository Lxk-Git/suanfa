package dcgpt.class02;

import java.util.*;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/6 16:51
 */
public class Code01_ChooseWork {
    /**
     * 1.先对整个job数组排序，一样的强度，钱多的在前面，强度不一样的，小的在前面
     * 2.创建一个有序表，有序表强度做键，钱做值。有序表里面有个方法是floorKey（返回小于或等于给定键的最大键，如果没有这样的键，则null）
     * 3.创建好的有序表，并不是把所有的工作放进去，要做个筛选，就是一样的工作强度，钱多的放进去，不一样的强度，钱是一样的，那就把强度低的放进去
     * 4.然后用有序表里面的键和打工人的能力对比，找到小于或者等于打工人能力的键，再把值取出来，这就是这个打工人能拿到最多的钱了。
     */

    public static class Job1 {
        public int hard;
        public int money;

        public Job1(int h, int m) {
            this.hard = h;
            this.money = m;
        }
    }
    //比较器
    public static class JobComparator1 implements Comparator<Job1>{
        @Override
        public int compare(Job1 o1, Job1 o2) {
            //难度不一样，难度小的放在前面，难度一样，钱多的放在前面
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
        }

    }


    public static int[] getMoneys2(Job1[] job, int[] ability) {
        //先排序
        Arrays.sort(job,new JobComparator1());
        //创建一个有序表
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        //把排序好的第一个工作的难度和金额放入treeMap中
        treeMap.put(job[0].hard,job[0].money);
        //然后for循环，把所有的工作放入有序表，但是去除一样的强度，价格低的，不一样的强度价格低的也要去除
        Job1 pre = job[0];
        //在排序好了的情况下，一样的难度，只要钱多的，这个好理解；不一样的难度，也只要钱多的，比如难度1，钱还是3元，难度3，钱3元，那前面那个就可以排除掉了
        for(int i=1;i<job.length;i++){
            //job[i].hard != pre.hard要不一样的，难度一样的，钱多的已经放在前面了，肯定进treeMap，没有必要进这个循环。
            if(pre.hard != job[i].hard && job[i].money > pre.money){
                pre = job[i];
                treeMap.put(pre.hard,pre.money);
            }
        }
        //然后就去ability取对应打工人的能力，和treeMap有序表对比，取到小于等于有序表的键的工作，就是这个打工人能胜任的工作
        int[] ans = new int[ability.length];
        for(int j=0;j<ability.length;j++){
            Integer key = treeMap.floorKey(ability[j]);
            ans[j]  = key == null ? 0 : treeMap.get(key);
        }
        return ans;
    }


}
