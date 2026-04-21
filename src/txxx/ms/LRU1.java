package txxx.ms;

import java.util.HashMap;
import java.util.Map;

public class LRU1 {
    class Node{
        private Integer key;
        private Integer value;
        private Node next;
        private Node pre;
        public Node(){


        }
        public Node(Integer key,Integer value){
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }
    }
    class LinkedNodeList{
        private Node vmHead;
        private Node vmTail;
        private Integer length;

        public LinkedNodeList(){
            this.vmHead = new Node();
            this.vmTail = new Node();
            this.vmHead.setNext(this.vmTail);
            this.vmTail.setPre(this.vmHead);
            length = 0;
        }
        public void add(Node node){
            node.setNext(this.vmTail);
            node.setPre(this.vmTail.getPre());
            this.vmTail.getPre().setNext(node);
            this.vmTail.setPre(node);
            length++;
        }
        public void delete(Node node){
            node.getPre().setNext(node.getNext());
            node.getNext().setPre(node.getPre());
            node.setPre(null);
            node.setNext(null);
            length--;
        }
        public void deleteHead(){
            Node head = this.vmHead.getNext();
            if(head == null){
                return;
            }
            this.delete(head);
        }
    }
    class LUR2{
        private Map<Integer,Node> map;
        private LinkedNodeList linkedNodeList;
        private Integer size;

        public LUR2(Integer size){
            this.size = size;
            map = new HashMap<>();
            linkedNodeList = new LinkedNodeList();
        }

        public Integer get(Integer key){
            if(this.size == 0){
                return -1;
            }
            if(!map.containsKey(key)){
                return -1;
            }
            Node node = map.get(key);
            this.linkedNodeList.delete(node);
            this.linkedNodeList.add(node);
            return node.getValue();
        }

        public void put(Integer key,Integer value){
            if(this.size==0){
                return ;
            }
            if(map.containsKey(key)){
                Node node  = map.get(key);
                this.linkedNodeList.delete(node);
            }else if(this.size == this.linkedNodeList.length){
                Node head = this.linkedNodeList.vmHead.getNext();
                map.remove(head.getKey());
                this.linkedNodeList.deleteHead();
            }
            Node newNode = new Node(key,value);
            map.put(key,newNode);
            this.linkedNodeList.add(newNode);
        }

    }


}
