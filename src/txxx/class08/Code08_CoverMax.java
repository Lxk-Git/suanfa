package txxx.class08;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/18 17:45
 */

//因为报错，移到class07
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

 /*   //1.先将线段的头节点按小到大排序
    //2.将线段的尾结点放进小根堆中
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
    }*/


    public static int coverMax3(int[][] n){//数组的第一个参数代表右多少个线段，第二个参数的第一个值是头，第二个值是尾
        //创建一个长度为n.length的Line数组
        Line[] lines = new Line[n.length];
        //将n中的每个线段的头和为放入lines中
        for(int i=0;i<n.length;i++){
            lines[i] = new Line(n[i][0], n[i][1]);
        }
        //接下来将lines按头从小到大排序好
        Arrays.sort(lines,new StartComparator());
        //创建一个小根堆
        PriorityQueue<Integer> linePriorityQueue = new PriorityQueue<>();
        int max = 0;
        //对lines中的所有线段开始操作，将他们的尾节点放入小根堆中
        for(int i = 0;i<lines.length;i++){
            //当新加入进来的线段的头节点小于小根堆的堆顶和小根堆是空的时候不用弹出，就直接把线段的尾结点放入小根堆里面。否则就弹出
            while (!linePriorityQueue.isEmpty() && linePriorityQueue.peek() <= lines[i].start){
                linePriorityQueue.poll();
            }
            linePriorityQueue.add(lines[i].end);
            max = Math.max(max,linePriorityQueue.size());//循环比较已经排序好的lines就能够得到最多的max
        }
        return max;
    }

































   /* public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int maxCover2(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        //对
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
    }*/


}