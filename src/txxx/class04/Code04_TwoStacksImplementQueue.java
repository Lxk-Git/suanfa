package txxx.class04;

import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/9 16:28
 */
public class Code04_TwoStacksImplementQueue {


    //两个栈创建一个队列
    //两个重点,这样才能保住新加入的数据不会影响到stackpop栈弹出的时候顺序乱掉

    //一：就是在pop栈没有全部弹出的时候，不能够往pop栈加入新的数据
    //二：push栈没有全部弹出给到pop的时候，不能让push加入的新数据进入pop栈


    //创建两个栈，一个压入栈stackPush，一个弹出栈stackPop
    //加入第一个数据进入压入栈stackPush，并把他马上送入stackPop。
    //压入弹出栈要有以上两个条件，pop栈是空的时候就把push栈的全部数据压过来，pop栈不是空的时候，push栈的数据就让他放在push栈中
    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;
        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }
        // push栈向pop栈倒入数据
        private void pushToPop() {
            if (stackPop.empty()) {//为空的时候才把push栈的数据拿过来，第一次就拿一个，后面把这第一个弹出了之后，push栈有的都给压入到pop栈中。
                while (!stackPush.empty()) {//这一句就是当push中的数据不为空的时候，就把所有的数据都压入到pop栈中。
                    stackPop.push(stackPush.pop());
                }
            }
        }
        public void add(int pushInt) {
            stackPush.push(pushInt);//第一次加入到push栈,
            pushToPop();//并从push栈弹出到pop栈
        }
        public int poll() {//第一次弹出的时候就是弹出了第一次压入到pop的数据
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();//第一次调用这个是不会跑的，第二次才会调用这个
            return stackPop.pop();//直接弹出第一次压入到pop栈的数据
        }
        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }
    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
//        System.out.println(test.peek());
        System.out.println(test.poll());
//        System.out.println(test.peek());
        System.out.println(test.poll());
//        System.out.println(test.peek());
        System.out.println(test.poll());
    }



}
