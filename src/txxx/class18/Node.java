package txxx.class18;

import java.util.ArrayList;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/4 14:35
 */


//点的结构
public class Node {

    public int value;//点的值
    public int in;//入度
    public int out;//出度
    public ArrayList<Node> nexts;//邻居
    public ArrayList<Edge> edges;//从他出发有哪些直接的边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}

