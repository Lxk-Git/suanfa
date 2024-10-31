package txxx.class13;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/10 17:13
 */
public class Code05_MaxDistance {

    //递归套路
    //最大距离是多少，高度是多少
    //最大距离不经过头节点，最大距离在左边或者在右边，最大距离经过头节点，最大距离是左子树的高度加右子树的高度加一
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public int maxDis;
        public int height;

        public Info(int maxDis, int height) {
            this.height = height;
            this.maxDis = maxDis;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftNode = process(x.left);
        Info rightNode = process(x.right);
        int height = Math.max(leftNode.height, rightNode.height) + 1;
        int p1 = leftNode.maxDis;//最大值在左边
        int p2 = rightNode.maxDis;//最大值在右边
        int p3 = leftNode.height + rightNode.height + 1;//最大值经过头节点
        int max = Math.max(Math.max(p1, p2), p3);
        return new Info(max, height);//这里的max就是maxDis，是这个max用来每次给maxDis传递值
    }


    public static Info process1(Node x) {
        if(x == null){
            return new Info(0,0);
        }
        Info leftNode = process1(x.left);
        Info rightNode = process1(x.right);
        int height = Math.max(leftNode.height, rightNode.height)+1;
        int p1 = leftNode.maxDis;
        int p2 = rightNode.maxDis;
        int p3 = (leftNode.height + rightNode.height) +1;
        int max = Math.max(Math.max(p1,p2),p3);
        return new Info(max,height);
    }

    public static int maxDistance(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxDis;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.left.left = new Node(3);
        node.left.right = new Node(4);
        node.left.left.left = new Node(5);
        node.left.right.right = new Node(6);
        node.right = new Node(7);
        int i = maxDistance(node);
    }
}
