package txxx.class17;

import org.omg.PortableServer.POA;

import java.util.*;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/5 22:38
 */
public class Code04_Kruskal {
    public static class UnionFind{
        public HashMap<Node,Node> fatherMap;//就是点的父节点是谁存在这个map
        public HashMap<Node,Integer> sizeMap;//每个节点对应有多少个点
        public UnionFind(){
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }
        public void makeSets(Collection<Node> nodes){//其实是在这里做初始化
            fatherMap.clear();
            sizeMap.clear();
            for(Node cur:nodes){
                fatherMap.put(cur,cur);
                sizeMap.put(cur,1);
            }
        }
        //查(用栈是因为他是先进后出，路径压缩需要把每个路过的点的头节点都设置成一样的cur节点)
        //栈可以实现，那么队列也能实现，他们的优缺点是什么。现在我明白了是用栈实现就像用数组一样实现好实现，用队列要注意边界问题不好实现
        public Node findParents(Node cur){
            Stack<Node> sta = new Stack<>();
            while (cur != fatherMap.get(cur)){
                sta.add(cur);
                cur = fatherMap.get(cur);
            }
            while (!sta.isEmpty()){
                fatherMap.put(sta.pop(),cur);
            }
            return cur;
        }
        //是不是相等
        public boolean isSameNode(Node a,Node b){
            return findParents(a) == findParents(b);
        }
        //并
        public void union(Node a,Node b){
            if(a == null || b == null){
               return;
            }
            Node f1 = findParents(a);
            Node f2 = findParents(b);
            if(f1 != f2){
                if(sizeMap.get(f1) >=sizeMap.get(f2)){
                    fatherMap.put(f2,f1);
                    sizeMap.put(f1,sizeMap.get(f1)+sizeMap.get(f2));
                    sizeMap.remove(f2);
                }else {
                    fatherMap.put(f1,f2);
                    sizeMap.put(f2,sizeMap.get(f1)+sizeMap.get(f2));
                    sizeMap.remove(f1);
                }
            }
        }
        public static class MyComp implements Comparator<Edge>{
            @Override
            public int compare(Edge o1, Edge o2) {
                return o2.weight - o1.weight;
            }
        }
        //k算法就是将所有的边的权重放入一个队列中，然后选择最小权重的边,依次弹出，将他的出点和入点并到一个集合里面，如果并过了就不用并了，然后继续
        public static Set<Edge> kruskal(Graph graph){
            UnionFind unionFind = new UnionFind();
            unionFind.makeSets(graph.nodes.values());//初始化了
            //小根堆，要点的权重从小到大排序的
            PriorityQueue<Edge> edges = new PriorityQueue<>(new MyComp());
            for(Edge ed:graph.edges){
                edges.add(ed);
            }
            Set<Edge> result = new HashSet<>();
            while (!edges.isEmpty()){
                Edge poll = edges.poll();//从小到大弹出
                if(!unionFind.isSameNode(poll.from,poll.to)){//判断这条边的出节点和入节点是不是已经在一个集合中了，不在才能去合并
                    result.add(poll);
                    unionFind.union(poll.from,poll.to);
                }
            }
            return result;
        }
    }
}
