package txxx.ms;


import sun.dc.pr.PRError;

import java.util.HashMap;
import java.util.Map;

public class LRU {

    class Node {
        private int key;
        private int value;

        private Node pre;
        private Node next;

        public Node(){

        }
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    //双链表
    class linkedNodeList{
        //虚拟的头
        private Node vmHead;
        //虚拟的尾
        private Node vmTail;
        //链表长度
        private Integer length;

        public linkedNodeList(){
            //虚拟的头和尾部相连
            this.vmHead = new Node();
            this.vmTail = new Node();
            this.vmHead.setNext(this.vmTail);
            this.vmTail.setPre(this.vmHead);
            length = 0;
        }

        public Node getVmHead() {
            return vmHead;
        }

        public void setVmHead(Node vmHead) {
            this.vmHead = vmHead;
        }

        public Node getVmTail() {
            return vmTail;
        }

        public void setVmTail(Node vmTail) {
            this.vmTail = vmTail;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }
        //新增node，是新增在末尾
        public void add(Node node){
            node.setNext(this.vmTail);//node先连上虚拟末尾
            node.setPre(this.vmTail.getPre());//node先和原来的末尾相连
            this.vmTail.getPre().setNext(node);//原来的末尾再连上新的node
            this.vmTail.setPre(node);//末尾最后要连上新的node
            length++;
        }
        //删除node,可以在任何地方删除
        public void delete(Node node){
            node.getPre().setNext(node.getNext());
            node.getNext().setPre(node.getPre());
            node.setPre(null);
            node.setNext(null);
            length--;
        }
        //删除头
        public void deleteHead(){
            Node head = this.vmHead.getNext();
            if(head == null){
                return;
            }
            delete(head);
        }
    }


    class LRU1{
        //创建一个map存node，并且设置一个值作为范围。
        private Map<Integer,Node> map;
        private linkedNodeList linkedNodeList;
        private Integer size;

        public LRU1(Integer size){
            this.size = size;
            map = new HashMap<>();
            linkedNodeList = new linkedNodeList();
        }

        //查询
        public Integer get(Integer key){
            if(size == 0){
                return -1;
            }
            if(!map.containsKey(key)){
                return -1;
            }
            //存在就更新
            //更新就是从删除再新增
            Node node = map.get(key);
            linkedNodeList.delete(node);
            linkedNodeList.add(node);
            return node.getValue();
        }
        //插入
        public void put(Integer key,Integer value){
            if(size == 0){
                return;
            }
            if(map.containsKey(key)){
                //存在就应该删除掉，然后更新
                Node node = map.get(key);
                linkedNodeList.delete(node);
            }else if(size != 0 && size == linkedNodeList.length){
                //满了，要把头给剔除掉
                Node head = linkedNodeList.getVmHead().getNext();
                map.remove(head.getKey());
                linkedNodeList.deleteHead();
            }
            //把新的装进去
            Node node = new Node(key,value);
            map.put(key,node);
            linkedNodeList.add(node);

        }
    }

    class LRU2{
        private Map<Integer,Node> map;
        private linkedNodeList linkedNodeList;
        private Integer size;


        public LRU2(Integer size){
            this.size = size;
            map = new HashMap<>();
            linkedNodeList = new linkedNodeList();
        }

        public Integer get(Integer key){
            if(size == 0){
                return -1;
            }
            if(!map.containsKey(key)){
                return -1;
            }
            Node node = map.get(key);
            linkedNodeList.delete(node);
            linkedNodeList.add(node);
            return node.value;
        }
        public void put(Integer key,Integer value){
            if(size == 0){
                return;
            }
            if(map.containsKey(key)){
                Node node = map.get(key);
                linkedNodeList.delete(node);
            }else if(size==linkedNodeList.length){
                linkedNodeList.deleteHead();
                Node head = linkedNodeList.getVmHead().next;
                map.remove(head.getKey());
            }
            Node node = new Node(key,value);
            map.put(key,node);
            linkedNodeList.add(node);

        }
    }

    public static void main(String[] args) {
        LRU lru = new LRU();
        LRU.LRU1 cache = lru.new LRU1(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);

        System.out.println(cache.get(1)); // 访问 key 1
        cache.put(4, 4); // 此时 key 2 被淘汰

        System.out.println(cache.get(2)); // 返回 -1，因为 key 2 已被淘汰
        System.out.println(cache.get(3)); // 返回 3
        System.out.println(cache.get(4)); // 返回 4
    }

}
