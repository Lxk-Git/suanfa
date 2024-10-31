package txxx.class10;

import java.util.HashMap;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/2 22:16
 */
public class Code10_CopyListWithRandom {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    //第一个方法就是用容器来做

    public static Node copyRandomList1(Node head) {
        //创建一个hashmap来方copy的新节点
        //key是老节点，value是新节点
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node cur = head;
        //将新节点和老节点值一一对应
        while (cur != null) {
            hashMap.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        //将新老节点指针一一对应
        //具体做法就是新节点的next指针就是老节点cur.next对应在map中的值
        //random同理
        while (cur != null) {
            hashMap.get(cur).next = hashMap.get(cur.next);
            hashMap.get(cur).random = hashMap.get(cur.random);
            cur = cur.next;
        }
        return hashMap.get(head);

    }

    //第二个方法就不用容器了，还是使用链表
    public static Node copyRandomList2(Node head) {
        //这个方法分为三步
        //第一步用链表的方法copy next方向上的数据，结构还是一样的
        //比如 1->2->3->null变成1->1`->2->2`->3->3`->null
        //第二步，在第一步的基础上copy random指针
        //第三步，将老的和新的拆分出来
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        //第一步
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node copy = null;
        //第二步
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;//cur.random是cur的random,cur.random.next才是copy的random
            cur = next;
        }
        //第三步分离
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    public static Node copyRandomList3(Node head) {
        //第一步复制相同的值，并且指向next指针
        //第二步复制random指针
        //第三步分离
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        //第一步
        while (next != null) {
            Node copy = new Node(cur.val);
            cur.next = copy;
            cur.next.next = next;
            next = cur.next;
        }
        //第二步
        cur = head;
        Node next1 = null;
        while (next1 != null){
            Node copy = cur.next;
            copy.random = cur.random != null ? cur.random.next:null;
            next1 = copy.next;
        }
        //第三步分离
        Node res = cur.next;
        Node next3 = null;
        cur = head;
        while (cur != null){
            next3 = cur.next.next;
            Node copy = cur.next;
            cur.next = next3;
            copy.next = next3 != null ? next3.next : null;
            cur = next3;
        }
        return res;
    }
}
