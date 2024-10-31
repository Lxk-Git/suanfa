package txxx.class18;

import java.util.HashMap;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/9 14:39
 */
public class Code01_Dijkstra2 {


    //创建一个类，是节点和他的距离
    public static class NodeRecord{
        public Node node;
        public int distance;

        public NodeRecord(Node node,int distance){
            this.node = node;
            this.distance = distance;
        }
    }
    //创建一个加强堆
    public static class NodeHeap{
        public Node[] heap;
        public HashMap<Node,Integer> indexMap;//逆序表
        public HashMap<Node,Integer> distanceMap;//点和点对应的距离。
        public int size;//用来累计index

        public NodeHeap(int size) {
            heap = new Node[size];
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        //每次加入一个节点到distanceMap中，要判断是不是已经有了还是没有，有了就是update,没有就add
        public  void addOrUpdateOrIgnore(Node node,int distance){
            if(inHeap(node)){//有就修改
                distanceMap.put(node,Math.min(distanceMap.get(node),distance));
                heapInsert(indexMap.get(node));
            }
            if(!isEntered(node)){//没有就增加
                heap[size] = node;
                indexMap.put(node,size);
                distanceMap.put(node,distance);
                size++;
                heapInsert(indexMap.get(node));
            }
        }
        //弹出的方法
        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(heap[0],distanceMap.get(heap[0]));
            swap(0,size-1);
            //这里为了表示已经弹出过，在逆序表用-1表示
            indexMap.put(heap[size-1],-1);
            //distanceMap删除掉这个节点
            distanceMap.remove(heap[size-1]);
            //heap也要删除
            heap[size -1] = null;
            heapiFy(0,--size);
            return nodeRecord;
        }

        private void heapiFy(int index, int size) {
            int leftc = 2 * index +1;
            while (leftc < size){
                int temp = leftc +1 <size && distanceMap.get(heap[leftc +1]) < distanceMap.get(heap[leftc]) ? leftc +1 : leftc;
                temp = distanceMap.get(heap[temp]) < distanceMap.get(heap[index]) ? temp :index;
                if(temp == index){
                    break;
                }
                swap(temp,index);
                index = temp;
                leftc = 2 * index +1;
            }
        }


        private void heapInsert(Integer index) {
            while (distanceMap.get(heap[index]) < distanceMap.get(heap[(index-1)/2]) ){
                swap(index,(index-1)/2);
                index = (index-1)/2;
            }
        }


        private void swap(Integer index1, Integer index2) {
            indexMap.put(heap[index1],index2);
            indexMap.put(heap[index2],index1);
            Node temp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = temp;
        }

        private boolean isEntered(Node node) {
            return distanceMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && distanceMap.get(node) != -1;
        }
        public boolean isEmpty(){
            return size== 0;
        }
    }
    public static HashMap<Node,Integer> dijkstra(Node from,int size){//传进来的参数是带size的，所以可以初始化
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(from,0);
        HashMap<Node,Integer> result = new HashMap<Node,Integer>();
        while (!nodeHeap.isEmpty()){
            NodeRecord nodeRecord = nodeHeap.pop();
            Node node = nodeRecord.node;
            int distance = nodeRecord.distance;
            for(Edge edge:node.edges){
                nodeHeap.addOrUpdateOrIgnore(edge.to,edge.weight +distance);//这里要注意加上distance，因为在addOrUpdateOrIgnore中没有加
            }
            result.put(node,distance);
        }
        return result;
    }
}
