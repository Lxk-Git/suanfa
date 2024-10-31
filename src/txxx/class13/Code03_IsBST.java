package txxx.class13;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/10 16:31
 */
public class Code03_IsBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }
    //递归套路
    //是不是搜索二叉树，左子树的最大值是多少，右子树的最小值是多少

    //情况1，左子树不为空，左子树是搜索树，右子树不为空，右子树是搜索树,
    // 左子树不为空，左树最大值(他的左子树和右子树的最大值)小于x节点，右子树不为空，右子树最小值(他的左子树和右子树的最小值)大于X节点，


    public static class Info{
        public boolean isBST;
        public int max;
        public int min;
        public Info(boolean isB,int max,int min){
            this.isBST = isB;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(Node x){
        if (x == null){
            return null;
        }
        Info leftNode = process(x.left);
        Info rightNode = process(x.right);
        int max = x.value;
        if(leftNode != null){
            max =Math.max(max,leftNode.max);
        }
        if(rightNode != null){
            max = Math.max(max,rightNode.max);
        }
        int min = x.value;
        if(leftNode != null){
            min =Math.min(min,leftNode.min);
        }
        if(rightNode != null){
            min = Math.min(min,rightNode.min);
        }
        boolean isBST = true;//默认是
        if(leftNode != null  && !leftNode.isBST){//不是的情况都列出来
            isBST = false;
        }
        if(rightNode != null  && !rightNode.isBST){
            isBST = false;
        }
        if(leftNode != null  && leftNode.max >= x.value){
            isBST = false;
        }
        if(rightNode != null  && rightNode.min <= x.value){
            isBST = false;
        }
        return new Info(isBST,max,min);
    }

    public static boolean isBST(Node head){
        if(head == null){
            return true;
        }
        return process(head).isBST;
    }
}
