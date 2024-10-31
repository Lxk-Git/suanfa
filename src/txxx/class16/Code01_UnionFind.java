package txxx.class16;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/1 9:52
 */
public class Code01_UnionFind {
    //并查集是一种树型的数据结构，用来解决一些不相交集合的合并和查询问题。
    //好处是使用他解决一些N个元素的集合应用问题时候，可以做到较低的时间复杂度和空间复杂度。
    //并查集的重点：(1)将不相交的集合合并，合并要点是小的集合去合并大的集合
    //(2)去查合集中最高的节点，如果多个节点的最高节点都是同一个节点，那么就说明他们属于一个集合。
    //注意点：需要把每个独立的元素做成一个只调用自己的集合，这样当这个点是最上面的点的时候，他的父节点就是他自己。
    //例如a,b,c就是{子节点，最高父节点}：{a,a},{b,b},{c,c};a->b->c就是{子节点，最高父节点}：{a,c},{b,c},{c,c}
   //合并的时候不一定是链结构，看代表节点下面有几个节点，如果只有一个就是一个去连代表节点，两个就那两个的代表节点去连另外的代表节点，做好得到的是一个多叉树


    public static class Node<V>{
        public V value;
        public Node(V v){
            this.value = v;
        }
    }


    public static class UnionFid<V>{
        public HashMap<V,Node<V>> nodes;//先自己对自己
        public HashMap<Node<V>,Node<V>> parents;//每个点的父节点是哪个
        public HashMap<Node<V>,Integer> sizeMap;//每个节点，包含自己一共有多少个节点。

        public UnionFid(List<V> values){//初始化传入的是V的集合
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for(V val:values){
                Node<V> node = new Node<>(val);
                //现在都是初始化的时候，
                nodes.put(val,node);//自己对自己
                parents.put(node,node);//自己的最高父就是自己
                sizeMap.put(node,1);//自己一个节点，总共一个节点
            }
        }

        //先看查最高节点的问题
        //要向上找，不能再往上，把这个点返回


        public Node<V> findParent(Node<V> cur){
            //需要一个容器，栈stack,让数据从map中拿出来，又能放回map中
            Stack<Node<V>> stack = new Stack<>();
            while (cur != parents.get(cur)){
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()){//这就是优化的地方
                parents.put(stack.pop(),cur);//这一步就可以实现a->b->c本来的{a,b},{b,c},{c,c}变成{a,c},{b,c},{c,c}这样的扁平效果
            }
            return cur;
        }

        //判断两个节点的最高节点是不是一个
        public Boolean isSomeNode(V a,V b){
            return findParent(nodes.get(a)) == findParent(nodes.get(b));
        }

        //合并
        //只有当两个的最高父节点不是一个节点的时候，才能合并,合并之后size要变化

        public void union(V a,V b){
            Node<V> node1 = findParent(nodes.get(a));
            Node<V> node2 = findParent(nodes.get(b));

            if(node1!=node2){
                int sizeNode1 = sizeMap.get(node1);
                int sizeNode2 = sizeMap.get(node2);
                Node<V> big = sizeNode1 > sizeNode2 ? node1 : node2;
                Node<V> small = big == node1 ? node2 : node1;
                parents.put(small,big);
                sizeMap.put(big,sizeNode1+sizeNode2);
                sizeMap.remove(small);//还要记得把小的那个删除掉
            }
        }

    }

}
