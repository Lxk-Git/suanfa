package txxx.class17;

import java.util.*;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/6 15:33
 */
public class Code06_Dijkstra {
    //所有的点都放在一个map中，key是点，value是最小距离，如果能直接到就用直接距离，不能就是正无穷
    //选择一个点作为进入的点，他的直接距离是0。
    //用一个set存没有经过的点，经过的时候如果这个点没有存在set中，并且这个点的距离小于正无穷或者前面已经放进去的距离，对比一下，放最小的进去
    public static HashMap<Node,Integer> dijkstra2(Node from){
        //distanceMap里面的数据会更新
        HashMap<Node,Integer> distanceMap = new HashMap<>();
        distanceMap.put(from,0);
        //nodeHashSet存在这里面的点，相当于打钩了的点
        HashSet<Node> nodeHashSet = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode2(distanceMap,nodeHashSet);
        while (minNode != null){
            int distance = distanceMap.get(minNode);
            for(Edge edge:minNode.edges){//这一步会把所有的点加入distanceMap，并且经过下面的循环，得到到每个点的最小边。
                Node tNode = edge.to;
                if(!nodeHashSet.contains(tNode)){
                    distanceMap.put(tNode,distance+edge.weight);//不在就新加
                }else {
                    distanceMap.put(tNode,Math.min(distanceMap.get(tNode),distance+edge.weight));//在就刷新
                }
            }
            nodeHashSet.add(minNode);
            minNode = getMinDistanceAndUnselectedNode2(distanceMap, nodeHashSet);//求当前点下面的所有边中最小边对应的点
        }
        return distanceMap;

    }

    private static Node getMinDistanceAndUnselectedNode2(HashMap<Node, Integer> distanceMap, Set<Node> nodeHashSet) {
        Node minNode = null;//全局变量
        int minDistance = Integer.MAX_VALUE;//全局变量
        for(Map.Entry<Node,Integer> entry:distanceMap.entrySet()){
            Node node = entry.getKey();
            int distance = entry.getValue();
            //!nodeHashSet.contains(node)筛选node点是不是在nodeHashSet中，是就结束
            //distance<minDistance 和minDistance = distance;minNode = node;取到最小的边，最小边对应的点就是我们需要的结果
            if(!nodeHashSet.contains(node) && distance<minDistance){
                minDistance = distance;
                minNode = node;
            }
        }
        return minNode;
    }
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Edge edge1 = new Edge(1, node1, node2);
        Edge edge2 = new Edge(4, node1, node3);
        Edge edge3 = new Edge(5, node1, node4);
        Edge edge4 = new Edge(1, node2, node3);
        Edge edge5 = new Edge(2, node3, node4);
        Edge edge6 = new Edge(4, node2, node5);
        Edge edge7 = new Edge(2, node3, node5);
        Edge edge8 = new Edge(10, node4, node5);
        node1.edges.add(edge1);
        node1.edges.add(edge2);
        node1.edges.add(edge3);
        node1.nexts.add(node2);
        node1.nexts.add(node3);
        node1.nexts.add(node4);
        node1.out = 3;
        node2.edges.add(edge4);
        node2.edges.add(edge6);
        node2.nexts.add(node3);
        node2.nexts.add(node5);
        node2.out = 2;
        node2.in = 1;
        node3.edges.add(edge5);
        node3.edges.add(edge7);
        node3.nexts.add(node4);
        node3.nexts.add(node5);
        node3.out = 2;
        node3.in = 2;
        node4.edges.add(edge8);
        node4.nexts.add(node5);
        node4.out = 1;
        node4.in = 2;
        node5.in = 3;
        dijkstra2(node1);

    }



    /*public static class NodeRecord {
        public Node node;
        public int distance;
        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
    public static class NodeHeap {
        private Node[] nodes; // 实际的堆结构
        // key 某一个node， value 上面堆中的位置
        private HashMap<Node, Integer> heapIndexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
        private HashMap<Node, Integer> distanceMap;
        private int size; // 堆上有多少个点
        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }
        public boolean isEmpty() {
            return size == 0;
        }
        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(size++);
            }
        }
        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            // free C++同学还要把原本堆顶节点析构，对java同学不必
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }
        private void insertHeapify(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }
        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }
        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }
        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }
    // 改进后的dijkstra算法
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }*/
}
