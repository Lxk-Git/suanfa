package txxx.class18;


/**
 * @Author: LiuXingKun
 * @Date: 2023/2/4 14:36
 */
public class Edge {
    public int weight;//权重
    public Node from;//从哪个Node点来
    public Node to;//到哪个Node点去
    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
