package txxx.class13;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/10 16:00
 */
public class Code02_IsBalanced {

    //递归套路
    //每一颗子树是不是平衡二叉树，高度是多少

    //情况1，左子树是平衡二叉树，右子树是平衡二叉树，高度差不大于1

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }


    public static class Info{
        public boolean isBalanced;
        public int height;
        public Info(boolean isB,int h){
            this.height = h;
            this.isBalanced = isB;
        }
    }

    public static Info process(Node x){
        if(x == null){
            return new Info(true,0);
        }
        Info leftNode = process(x.left);
        Info rightNode = process(x.right);
        int height = Math.max(leftNode.height ,rightNode.height) +1;
        boolean isBalanced = true;
        if(!leftNode.isBalanced){//左子树不平衡
            isBalanced = false;
        }
        if(!rightNode.isBalanced){//右子树不平衡
            isBalanced = false;
        }
        if(Math.abs(leftNode.height - rightNode.height) >1){//高度差大于1
            isBalanced = false;
        }
        return new Info(isBalanced,height);
    }
}
