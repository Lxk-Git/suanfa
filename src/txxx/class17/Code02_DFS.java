package txxx.class17;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/4 15:34
 */
public class Code02_DFS {
//图的深度优先遍历
//重点就是从一个点开始遍历，并且把节点压入到栈里面，和放入set中，遍历到不能再往下。
//然后开始弹出压入栈里面的节点，再看他的下一个节点在不在set中，在就不管。
//不在就继续把刚刚弹出的节点压入栈里面去，并把不在set中的节点压入set和压入栈，继续重复刚刚压入栈的动作
    public static void dfs(Node start){
        if(start == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        //入栈的时候打印
        System.out.println(start.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for(Node next:cur.nexts){
                if(!set.contains(next)){//基本知识点：栈是先入后出
                    stack.add(cur);//把自己再加入进去，最后在stack里面能得到所有已经打印过的数据，最后一步一步弹出，才能遍历到所有的点
                    stack.add(next);//再把next节点压入栈
                    set.add(next);
                    System.out.println(next.value);
                    //为了防止cur.nexts有很多，然后一直遍历，所以需要break掉，这样就先选择了cur.nexts中的一个先继续遍历下去
                    break;
                }
            }
        }
    }
}
