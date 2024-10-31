package dcgpt.class05;

/**
 * @Author: LiuXingKun
 * @Date: 2023/4/7 14:53
 */
public class Code02_LeftRightSameTreeNumber {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 时间复杂度O(N * logN)
    public static int sameNumber1(Node head) {
        if (head == null) {
            return 0;
        }
        //sameNumber1(head.left)看左子树有多少个符合，sameNumber1(head.right)看右子树有多少个符合，(same(head.left, head.right) ? 1 : 0)再看自己是不是符合，符合就加一，否则就加0
        return sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.right) ? 1 : 0);
    }

    public static boolean same(Node h1, Node h2) {
        //一个是一个不是，异或的结果就是true,return的结果就是false.
        if (h1 == null ^ h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }
        // 两个都不为空
        //要两个的值相等，还要两个的左子树，右子树也相等
        return h1.value == h2.value && same(h1.left, h2.left) && same(h1.right, h2.right);
    }

    public static int sameNumber2(Node head){
        if(head == null){
            return 0;
        }
        return sameNumber2(head.left) + sameNumber2(head.right) + (same2(head.left,head.right) ? 1 : 0);
    }

    private static boolean same2(Node left, Node right) {
        if(left == null ^ right == null){
            return false;
        }
        if(left == null && right == null){
            return true;
        }
        return left.value == right.value && same2(left.left,right.left) && same2(left.right,right.right);
    }


}
