package txxx.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/18 17:45
 */
public class Code08_CoverMax {
    public static class Line {
        public int start;
        public int end;
        public Line(int s, int e) {
            this.start = s;
            this.end = e;
        }

    }
    public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    //1.先将线段的头节点按小到大排序
    //2.将线段的尾结点放进小根堆中，不是一下全部放进去了，是一个一个放进去，并且小根堆的根节点下面还要和线段的头对比。
    //3.用线段的头节点和放进堆中的根节点对比，小根堆中的根节点小于线段的头，就弹出
    //4.返回在小根堆中数字最多的时候，就是重合最多的数据
    //重点：就是他们一开始就是用线段的头节点排序好的，这样才能有序的加入到小根堆中。
    public static int maxCover2(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        //对所有的线段按开头排序，这样在做重合的时候可以按顺序来，返回重合最大的就是我们需要的结果
        Arrays.sort(lines, new StartComparator());
        // 小根堆，每一条线段的结尾数值，使用默认的
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            // lines[i] -> cur 在黑盒中，把<=cur.start 东西都弹出
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max, heap.size());
        }
        return max;
    }


/*    public static int maxCover3(int[][] m){
        Line[] lines = new Line[m.length];
        for(int i = 0;i<m.length;i++){
            lines[i] = new Line(m[i][0],m[i][1]);
        }
        Arrays.sort(lines,new StartComparator());
        int max = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int i = 0;i<lines.length;i++){
            while (!heap.isEmpty() && heap.peek()<=lines[i].start){
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max,heap.size());
        }
        return max;

    }*/


}