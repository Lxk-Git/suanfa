package txxx.class17;

import java.util.*;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/5 23:40
 */
public class Code05_Prim {
    //就是选择一个节点做头节点，然后解锁他的所有边，边上是有权重的，选择一条最小权重的边，然后和他连接的点也解锁，这个点的所有的边同样解锁，
    // 现在解锁的边和第一个节点的解锁的边全部都在一起，选择一个这里面最小的边，解锁和他连接的点，往下同理。
    //但是这里面为了防止加入重复的点，需要把符合要求的点放入set中。
    //返回的结果是set里面放的边
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph){
        //小根堆
        PriorityQueue<Edge> edgesQueue = new PriorityQueue<>(new EdgeComparator());
        //用来存点是不是已经走过的set
        HashSet<Node> hashSet = new HashSet<>();
        //最后需要的结果，走过的边
        Set<Edge> result = new HashSet<>();
        for(Node node:graph.nodes.values()){//随便选择一个点进入
            //这是第一个点
            if(!hashSet.contains(node)){
                hashSet.add(node);//解锁的node
                for(Edge edge:node.edges){//把和node相关的边全部加入到堆里面去
                    edgesQueue.add(edge);
                }
                while (!edgesQueue.isEmpty()){
                    Edge edge = edgesQueue.poll();//弹出最小的边
                    if(!hashSet.contains(edge.to)){//最小边的出点不在hashSet中
                        hashSet.add(edge.to);//解锁这个点
                        result.add(edge);//下一节点不在hashSet中的边才能加入结果中，将这条边加入到结果中
                        for(Edge nextEd:edge.to.edges){//解锁了新的节点，再把和节点相关的边也全部解锁
                            edgesQueue.add(nextEd);//从小到大加入到小根堆中去。
                        }
                    }
                }
            }
            break;//最好还是break掉，这里只要for一次就够了
        }
        return result;
    }

}
