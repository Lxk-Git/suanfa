package txxx.class17;

import java.util.*;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/5 16:31
 */
public class Code03_TopologySort {
    public static List<Node> sortedTopology(Graph graph){//传进来的参数是图，想要得到的结果是拓补排序，把所有的node放在List中
        //先创建一个hashMap，key是node节点，value是节点的入度
        HashMap<Node,Integer> inMap = new HashMap<>();
        //创建一个队列，只有入度为0的节点能加入进这个队列
        Queue<Node> queue = new LinkedList<>();
        //把图中所有的点都放入到inMap中，并且把入度为0的节点放入queue中
        for(Node node:graph.nodes.values()){
            inMap.put(node,node.in);
            if(node.in == 0){
                queue.add(node);
            }
        }
        //把想要的所有节点都放在这个result集合中
        ArrayList<Node> result = new ArrayList<>();
        while (!queue.isEmpty()){//经典对队列开始做判断
            Node cur = queue.poll();//第一次弹出的一定是入度为0的，
            result.add(cur);
            for(Node next:cur.nexts){//然后对入度为0的节点的下一些节点也开始做判断
                //inMap.get(next)是最开始存入inMap中的数据，此时为了消除掉第一步已经放入queue队列中的节点，所以要入度减去1
                inMap.put(next,inMap.get(next)-1);//这么操作就可能next的入度就变成0了
                if(inMap.get(next)==0){
                    queue.add(next);//为0就加入到队列中
                }
            }
        }
        return result;
    }

}
