package txxx.class13;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/10 16:50
 */
public class Code04_IsFull {

    //是不是满二叉树，高度是多少
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }
    public static class Info{
        public boolean isFull;
        public int height;
        public Info(boolean isF,int h){
            this.height =h;
            this.isFull = isF;
        }
    }


    //左右子树都是满二叉树，高度相等，以x为头的节点就是满二叉树
    public static Info process(Node x){
        if(x == null){
            return new Info(true,0);
        }
        Info leftNode = process(x.left);
        Info rightNode = process(x.right);
        int height = Math.max(leftNode.height,rightNode.height)+1;
        boolean isFull = leftNode.isFull && rightNode.isFull && leftNode.height == rightNode.height;
        return new Info(isFull,height);
    }
    public static boolean isFull(Node head){
        if(head == null){
            return true;
        }
        return process(head).isFull;
    }

}
