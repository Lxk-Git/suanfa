package txxx.class15;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/31 16:01
 */
public class Code03_IPO {
    //输入正整数组costs成本、正整数组profits利润、正数k能做几单生意、正数W开始有的资金、条件：累加起来的资金如果小于成本，那么这单生意就不能做。输出你最后获得的最大钱数。

    //创建两个堆，一个是按成本排序的小根堆，一个是按利润算的大根堆。
    //先将所有的生意按照成本低的顺序放入小根堆，当成本小于w原始资金或累加之后的资金的时候，再把这个生意放入按利润算的大根堆里面，再算他能获得的资金


    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public class MinCostsComp implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    public class MaxProfitsComp implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }
    public int IPO(int K, int W, int[] costs, int[] profits){
        PriorityQueue<Program> minQ = new PriorityQueue(new MinCostsComp());
        PriorityQueue<Program> maxQ = new PriorityQueue(new MaxProfitsComp());
        for(int i = 0;i<costs.length;i++){
            minQ.add(new Program(costs[i],profits[i]));
        }
        for(int i = 0; i < K;i++){
            while (!minQ.isEmpty() && minQ.peek().c <= W){
                maxQ.add(minQ.poll());//把利润最高的放在最前面，，不管是第几个for都是这个顺序
            }
            if(minQ.isEmpty()){//预防一些个成本巨高的单，这个没法做，直接返回W
                return W;
            }
            W += maxQ.poll().p;//把例如最高的取出来，这就做成一单
        }
        return W;
    }

}
