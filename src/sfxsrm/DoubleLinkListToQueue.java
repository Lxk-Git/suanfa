package sfxsrm;

//双端队列要使用双链表来做
//那当然是头部插入，尾部弹出，尾部插入，头部弹出！
public class DoubleLinkListToQueue {
    //先创建一个双链表
    public static class Node<V> {
        public V value;
        public Node<V> next;//向下的指针
        public Node<V> last;//向上的指针

        public Node(V data) {
            value = data;
            next = null;
            last = null;
        }

    }

    //创建一个队列
    public static class MyQueue<V> {
        private Node<V> head;//头节点
        private Node<V> tail;//尾节点
        private int size;

        public MyQueue() {
            this.size = 0;
            this.head = null;
            this.tail = null;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }


        //从头部插入的方法
        public void pushHead(V value) {
            Node<V> cur = new Node<>(value);
            if (head == null) {//第一次创建的时候head是空的
                head = cur;
                tail = cur; //头尾节点都是这个cur
            } else { //这就是进入的第二个及以后的数字了
                cur.next = head;//将head节点放入到cur的下一个节点
                head.last = cur;//将cur节点放入到head的上一个节点，这样就形成双链表了
                head = cur;//再将cur的值换掉head的值，也就是head向cur移动！
            }
            size++;
        }

        //尾部插入的方法
        public void pushTail(V value) {
            Node<V> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;//将cur的值给到tail,tail指针向cur移动
            }
            size++;
        }

        //头部插入的弹出办法
        public V pollHead() {
            V value = null;
            if (head == null) {//说明没有数据了
                return value;
            }
            size--;
            value = tail.value;
            if(tail == head){
                tail = null;
                head = null;
            }else {
                tail = tail.last;//tail.last的值给到tail,tail指针移动到他的上一个节点
                tail.next = null;//把下一个节点去除！
            }
            return value;
        }

        //尾部插入的弹出办法
        public V pollTail(){
            V value = null;
            if(head ==null){
                return value;
            }
            size--;
            value=head.value;
            if(head == tail){
                head=null;
                tail=null;
            }else {
                head = head.next;
                head.last=null;
            }
            return value;
        }
    }
}
