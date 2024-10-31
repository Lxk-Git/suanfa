package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/20 22:36
 */
public class LinkTable {

    //创建一个有一个值和一个指针的节点，构建单链表
    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            value = data;
        }

/*        // 添加新的结点,this.next.add(newval)递归到this.next == null的时候加入新的节点！
        public void add(int newval) {
            Node newNode = new Node(newval);
            if(this.next == null)
                this.next = newNode;
            else
                this.next.add(newval);
        }
        // 打印链表
        public void print() {
            System.out.print(this.value);
            if(this.next != null)
            {
                System.out.print("-->");
                this.next.print();
            }
        }*/

    }
    //单链表反转
    //为了让这个Node有效，head的位置一定是这个链表开始的地方
    public static Node reverseLinkTable(Node head){//head怎么折腾都是调用了一个别的内存地址这里的head是不变的
        //给定两个空节点
        Node next = null;
        Node pre = null;
        while (head != null){//当head等于null了，就代表所有的数据都遍历完了
            next = head.next;//先将head.next指针指向的数据给到节点next，
            //将指针指向pre，第一次是null，
            // 第二次及以后就是在pre=head，head的值传递给pre,pre指针向head移动，head=next，head向next移动，head.next=pre就是将链表反转了，
            // 因为head移动到了next,head的新下一点就是pre
            //例子 第一步：null <--1   2-->3-->null
            //    第二步：null <--1<--2   3-->null
            head.next = pre;
            pre = head; // pre指针向head移动，pre变成了head了，这一步在java的jvm中通过可达性分析算法，将无用的引用废弃掉了
            head = next; //再用head替换掉一开始存的next
        }
        return pre; //必须返回她也是因为jvm可达性算法，不然就会被垃圾回收

    }

    public static Node reverseLinkTable2(Node head){
        Node next  = null;
        Node pre = null;
        while (head != null){
            next = head.next;
            head.next =pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    //创建有一个值，一个指向下一个节点的指针和一个指向上一个节点的指针，构建双链表
    public static class DoubleNode {
        public int value;
        public DoubleNode next;
        public DoubleNode last;
        public DoubleNode(int data){
            value = data;
        }
    }
    //双链表反转
    //
    public static DoubleNode reverseDoubleLinkTable(DoubleNode head){
        DoubleNode next = null;
        DoubleNode pre = null;
        while (head != null){
            next = head.next;
            head.next = pre; //链表中指向下一个的指针变成指向上一个
            head.last = next;//链表中指向上一个的指针变成指向下一个
            pre = head;
            head = next;
        }
        return pre;
    }

    //双链表的关键是前指针要指向后面，后面要指向前面
    public static DoubleNode reverseDoubleLinkTable2(DoubleNode head){
        DoubleNode next = null;
        DoubleNode pre = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));//创建一个size大小的链表
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));//创建链表的第一个数
        Node pre = head;//给到pre
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));//第二个数
            pre.next = cur;//pre的下一个数据就是cur
            pre = cur;//再把pre移动到cur
            size--;
        }
        return pre;
    }

    public static DoubleNode generateRandomDoubleList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = cur;//pre的下一个节点是cur
            cur.last = pre;//cur的上一个节点是pre
            pre = cur;//将pre移到cur的位置
            size--;
        }
        return pre;
    }

    public static void main(String[] args) {
        int len = 5;
        int value = 10;
        int testTime = 1;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            Node node1 = generateRandomLinkedList(len, value);

/*            Node node1 = new Node(1);
            node1.add(2);				//插入结点，打印
            node1.add(3);
            node1.print();*/
            System.out.println("=============");
            node1 = reverseLinkTable(node1);


            System.out.println("=============");
            DoubleNode node3 = generateRandomDoubleList(len, value);
            System.out.println(node3);
            System.out.println("=============");
            node3 = reverseDoubleLinkTable(node3);
            System.out.println(node3);

        }
        System.out.println("test finish!");
    }





}
