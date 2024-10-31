package sfxsrm;


import java.util.LinkedList;
import java.util.Queue;

public class QueueAndStack {
    //创建一个有一个值和一个指针的节点，构建单链表
    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V v) {
            value = v;
            next = null;
        }
    }

    //队列是先进先出
    public static class myQueue<V> {
        private Node<V> head;//头指针
        private Node<V> tail;//尾指针
        int size;

        public myQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;//判断是不是为空
        }

        public int size() {
            return size;
        }

        //入队列
        public void popQueue(V value) {
            //先创建一个节点，输入的数字是几就是几
            Node<V> node = new Node<V>(value);
            if (tail == null) { //第一个数字进来的时候，就让头尾指针都指向它
                head = node;
                tail = node;
            } else {
                tail.next = node;//第二次及之后就将新进来的node放入队列中
                tail = node;//node的值放入tail中，tail指针随着node的增加移动！
            }//到这个时候head都没有变化过，还是指着第一个进来的数字
            size++;
        }

        //出队列，因为是先进先出，那么就要对head进行操作
        private V pushQueue() {
            V value = null;
            if (head != null) {
                value = head.value;//先将数据取出来！
                head = head.next;//指针向下移动，弹出第一个进去的数字
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return value;
        }
        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }
    }

    //栈是先进后出，后进先出,用一个head就能搬到了
    public static class myStack<V> {
        private Node<V> head;
        private int size;

        public myStack() {
            head = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        //入栈
        public void popStack(V value) {
            Node<V> vNode = new Node<>(value);
            if (head == null) {
                head = vNode;
            } else {
                //第二步及以后，就将新节点node指向老的head
                vNode.next = head;
                //debug可以看见node的值赋值到了head上，这才是新加了一个节点，然后这个时候head指针指向新加入的node，这样才不会被jvm回收
                head = vNode;
            }
            size++;
        }

        //出栈，java代码中当然又是对head动手咯
        public V pushStack() {
            V value = null;
            if (head != null) {
                value = head.value;
                head = head.next;
                size--;
            }
            return value;
        }
    }


    public static void testQueue() {
        myQueue<Integer> myQueue = new myQueue<>();
        Queue<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myQueue.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myQueue.popQueue(num);
                test.offer(num);
            } else if (decide < 0.66) {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.pushQueue();
                    int num2 = test.poll();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myQueue.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.pushQueue();
            int num2 = test.poll();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }
//    public static void testStack() {
//        MyStack<Integer> myStack = new MyStack<>();
//        Stack<Integer> test = new Stack<>();
//        int testTime = 5000000;
//        int maxValue = 200000000;
//        System.out.println("测试开始！");
//        for (int i = 0; i < testTime; i++) {
//            if (myStack.isEmpty() != test.isEmpty()) {
//                System.out.println("Oops!");
//            }
//            if (myStack.size() != test.size()) {
//                System.out.println("Oops!");
//            }
//            double decide = Math.random();
//            if (decide < 0.33) {
//                int num = (int) (Math.random() * maxValue);
//                myStack.push(num);
//                test.push(num);
//            } else if (decide < 0.66) {
//                if (!myStack.isEmpty()) {
//                    int num1 = myStack.pop();
//                    int num2 = test.pop();
//                    if (num1 != num2) {
//                        System.out.println("Oops!");
//                    }
//                }
//            } else {
//                if (!myStack.isEmpty()) {
//                    int num1 = myStack.peek();
//                    int num2 = test.peek();
//                    if (num1 != num2) {
//                        System.out.println("Oops!");
//                    }
//                }
//            }
//        }
//        if (myStack.size() != test.size()) {
//            System.out.println("Oops!");
//        }
//        while (!myStack.isEmpty()) {
//            int num1 = myStack.pop();
//            int num2 = test.pop();
//            if (num1 != num2) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("测试结束！");
//    }
    public static void main(String[] args) {
        testQueue();
//        testStack();
    }



}
