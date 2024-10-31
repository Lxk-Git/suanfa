package txxx.class17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/5 17:21
 */
public class Code03_TopologicalOrderBFS {

    // 这个类是图的其他表示方法，只有值和他的邻点，这个是邻接表法。和前面自己写的图不一样
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;
        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    //参数graph就是图，传入一个图，得到他的拓补排序，ArrayList<DirectedGraphNode>就是拓补排序的结果
    public static ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph){
        //还是需要创建一个hashMap，key存graph的点，value存入度
        HashMap<DirectedGraphNode,Integer> inMap = new HashMap<>();
        for(DirectedGraphNode node:graph){
            //必须在这里加一个for循环是为了两点：
            //第一给头节点的入度变成0，其他节点变成0是没关系的，下面的代码会把别的节点的入度累加上的。
            //第二如果在下面的循环中做这个put操作会导致值被覆盖掉。
            inMap.put(node,0);
        }
        for(DirectedGraphNode cur:graph){
            for(DirectedGraphNode next:cur.neighbors){
                inMap.put(next,inMap.get(next) +1);//这一步就是在给每一个节点加上入度
            }
        }
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for(DirectedGraphNode cur:inMap.keySet()){
            if(inMap.get(cur) == 0){
                queue.add(cur);
            }
        }
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        while (!queue.isEmpty()){
            DirectedGraphNode ans = queue.poll();
            result.add(ans);
            for(DirectedGraphNode next:ans.neighbors){
                inMap.put(next,inMap.get(next) -1);
                if(inMap.get(next) == 0){
                    queue.add(next);
                }
            }
        }
        return result;
    }


}
