package txxx.class17;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/4 14:51
 */
//图的转化接口
public class GraphGenerator {

    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    //
    // [ 5 , 0 , 7]
    // [ 3 , 0,  1]
    public static Graph createGraph(int[][] matrix){
        Graph graph = new Graph();//创建一个没有值的图
        int len = matrix.length;
        for(int i =0;i<len;i++){
            //拿到每一条边
            int[] ints = matrix[i];
            int weight = ints[0];
            int from = ints[1];
            int to = ints[2];

            if(!graph.nodes.containsKey(from)){//如果没有from点就创建
                graph.nodes.put(from,new Node(from));
            }
            if(!graph.nodes.containsKey(to)){//如果没有to点就创建
                graph.nodes.put(to,new Node(to));
            }
            //如果有就拿到from节点和to节点
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            //拿到之后求他们之间的边
            Edge edge = new Edge(weight,fromNode,toNode);
            //fromNode的下一节点就是toNode
            fromNode.nexts.add(toNode);
            //from节点的出度++
            fromNode.out++;
            //to节点的入度++
            toNode.in++;
            //from节点的边,这里不是to节点的边，这点很重要
            fromNode.edges.add(edge);
            //图在上面已经加入节点了，现在就需要加边
            graph.edges.add(edge);
        }
        return graph;
    }
}
