package txxx.class11;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/4 17:28
 */

//树的递归，可以画递归图，
    //1,2,4,4,4,2,5,5,5,2,1,3,6,6,6,3,7,7,7,3,1
public class Code02_RecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int v) {
            value = v;
        }
    }
    public static void f(Node head) {
        if (head == null) {
            return;
        }
        // 1这里就是先序
        f(head.left);
        // 2这里是中序
        f(head.right);
        // 3这里是后序
    }
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
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
        pos(head);
        System.out.println("========");
    }
}
