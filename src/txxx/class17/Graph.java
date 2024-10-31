package txxx.class17;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/4 14:42
 */
//图
public class Graph {
    public HashMap<Integer, Node> nodes;//点是（node点的值，Node点）的结构
    public HashSet<Edge> edges;//边

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
