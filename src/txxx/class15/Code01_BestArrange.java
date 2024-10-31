package txxx.class15;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/31 14:38
 */
public class Code01_BestArrange {
    //用贪心算法返回最多会议室宣讲场次。
    //一个会议有开始时间和结束时间，如果一个会议开始时间是1点到3点，那么2点到3点的会议就不能开，3点到4点的就能看，求最多可以开多少次会议。


    public static class Program {
        public int start;
        public int end;

        public Program(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    //贪心算法这么解
    //让会议的结束时间从小到大排序，然后用一个timeLine变量来接受开始时间，只要会议的开始时间大于这个timeLine就可以开
    public static class MyCompare implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs) {
        Arrays.sort(programs, new MyCompare());
        int res = 0;
        int timeLine = 0;
        for (int i = 0; i < programs.length; i++) {
            while (programs[i].start >= timeLine) {
                res++;
                timeLine = programs[i].end;
            }
        }
        return res;
    }


}
