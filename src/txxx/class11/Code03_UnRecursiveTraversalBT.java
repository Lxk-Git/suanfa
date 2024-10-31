package txxx.class11;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/4 17:40
 */
import java.util.Stack;
//上生产一般不让用递归，都要使用容器来实现非递归方法
public class Code03_UnRecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int v) {
            value = v;
        }
    }


    //头入栈，头出栈，再右左入栈，再左右出栈
    //当前节点的右孩子不为空，就把右孩子入栈，左孩子不为空，就把左孩子入栈。
    //以后不明白debug,当前我是想明白了，就是入栈出栈的知识。
    public static void pre(Node head){
        System.out.print("pre-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()){
                head = stack.pop();
                System.out.print(head.value + " ");
                if(head.right !=null){
                    stack.push(head.right);
                }
                if(head.left !=null){
                    stack.push(head.left);
                }
            }
        }
    }
    //中序遍历，每棵二叉树都可以构造成左树结构，相当于只有头和左，或者头没有左
    //做法就是头和左入栈，然后出栈就变成了左和头，再把右边变成左树结构，假如右边的左树为空，那就只有右边，就把他入栈出栈，就是左头右了
    public static void in(Node cur) {
        System.out.print("in-order: ");
        if (cur != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    System.out.print(cur.value + " ");
                    cur = cur.right;//这里是重点，cur会向右边移动，如果为空就继续回到cur = stack.pop()弹出，再到cur.right
                }
            }
        }
        System.out.println();
    }

    public static void in1(Node head){
        if(head!=null){
            Stack<Node> stack = new Stack();
            while (!stack.isEmpty() || head != null){
                if(head!= null){
                    stack.push(head);
                    head = head.left;//这样就达到了先进头再进左
                }else {
                    head = stack.pop();//没有左进了就开始弹出左再弹出头
                    System.out.println(head.value + " ");
                    head = head.right;//上面弹出之后还要右移一下，右边不为空就把他压入栈了，再弹出来。总体达到了头左进，左头出，右进右出，成为中序左头右
                }
            }
        }
    }

    //后序遍历是先序遍历用一个栈，弹出顺序为头右左，再加入一个栈，入栈第一个栈弹出的头右左，这个栈再出栈就变成了左右头
    //用两个stack实现后序，栈2的压入顺序要是头右左，最后出栈的时候才是左右头，栈1的入栈顺序是头进，头出，立刻压入栈2中，然后左右进入栈1，再右左出栈1，右左压入栈2。这样合并起来在栈2中就是头右左了
    public static void pos1(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop(); // 头 右 左
                s2.push(head);
                //我要出来是头右左，进去就是头进，头出，左右进，右左出。
                if (head.left != null) {//左先进就会后出
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            // 左 右 头
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }


    //用两个stack实现后序，栈2的压入顺序要是头右左，最后出栈的时候才是左右头，栈1的入栈顺序是头进，头出，立刻压入栈2中，然后左右进入栈1，再右左出栈1，右左压入栈2。这样合并起来在栈2中就是头右左了
    public static void pos3(Node head){
        if(head!=null){
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()){
                head = s1.pop();
                s2.push(head);
                if(head.left !=null){
                    s1.push(head.left);
                }
                if(head.right != null){
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()){
                head = s2.pop();
                System.out.println(head.value + " ");
            }
        }
        System.out.println();
    }












    //这个先不看
    public static void pos2(Node h) {
        System.out.print("pos-order: ");
        if (h != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }
}
