package txxx.class17;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/4 15:26
 */
//图的宽度优先遍历
//重点是要创建一个set,节点入队列的时候先检查set中有没有，没有就加入set，并且加入队列，然后弹出。
// 加入这个set就是为了防止图成环了之后会一直遍历下去导致死循环。
public class Code01_BFS {

    public static void bfs(Node start){
        if(start == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for(Node next:cur.nexts){//遍历每一个下一节点
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
