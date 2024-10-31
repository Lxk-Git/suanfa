package txxx.class17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/5 21:46
 */
public class Code03_TopologicalOrderDFS2 {

    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
    //重点：如果x的点次比y的大，一定有x的拓补序比y小
    public static class Record {//点次类
        public DirectedGraphNode node;
        public int nodes;//点次

        public Record(DirectedGraphNode n, int ns) {
            node = n;
            nodes = ns;
        }
    }
    //对数器
    public static class MyComp implements Comparator<Record>{

        @Override
        public int compare(Record o1, Record o2) {
            return o2.nodes-o1.nodes;//o2大于o1，那么o1就是在前面
        }
    }

    //这个算法重点是弄缓存，这个缓存用一个hashMap来存，key是DirectedGraphNode，value是Record点次
    public static Record f(DirectedGraphNode cur,HashMap<DirectedGraphNode,Record> order){
        //如果cur一开始就在这个缓存里面，直接拿就好了
        if(order.containsKey(cur)){
            return order.get(cur);
        }
        //否则就是不在的情况
        int nodes = 0;//点次
        //算cur点下面的每个点的点次
        for(DirectedGraphNode next:cur.neighbors){
            nodes += f(next,order).nodes;
        }
        //cur自己的点次
        Record ans = new Record(cur,nodes+1);
        //把每一步的点次都挂到order缓存上去
        order.put(cur,ans);
        return ans;
    }
    //主方法是要输入的图，然后的是ArrayList<DirectedGraphNode>的序
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph){
        //创建一个order
        HashMap<DirectedGraphNode,Record> order = new HashMap<>();
        for(DirectedGraphNode cur:graph){//这一步把所有的点和点次都存进去了
            f(cur,order);
        }
        //将所有的点和他的点次信息放入ArrayList中
        ArrayList<Record> recordArr = new ArrayList<>();
        for(Record cur:order.values()){
            recordArr.add(cur);
        }
        //把点的点次排个序，把拓补序在前面的先排列出来
        recordArr.sort(new MyComp());
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        for(Record re:recordArr){
            result.add(re.node);
        }
        return result;
    }
}
