package txxx.class04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/11 18:09
 */
public class Code04_TwoQueueImplementStack {


    //用两个队列来实现栈
    //用一个队列queue来存数据，先进先出，用一个help队列来接收，然后queue保留最后一个数字
    //再将队列queue中存的那个队列的数字弹出，这样就实现了第一个弹出的数字是最后进入queue队列的数字
    //再将queue和help队列交换，queue还是继续弹出到保留最后一个数字



    public static class TwoQueueStack<T>{
        public Queue<T> queue;
        public Queue<T> help;


        public TwoQueueStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(T value){
            queue.offer(value);
        }


        public T poll(){
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            T ans = queue.poll();//实现弹出一个数字了
            Queue<T> emp = queue;
            queue = help;
            help = emp;//实现交换
            return ans;
        }
        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            help.offer(ans);
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }
        System.out.println("test finish!");
    }
}
