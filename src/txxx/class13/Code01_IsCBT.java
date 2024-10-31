package txxx.class13;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/10 10:54
 */
//递归套路解决
public class Code01_IsCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }
    //是不是完全二叉树
    //递归套路解决
    //传进来一个x节点
    //对于每一颗子树，是否是满二叉树、是否是完全二叉树、高度是多少

    //情况1，左子树是满二叉树，右子树是满二叉树，左右高度相等，是一个满二叉树，也就是完全二叉树
    //情况2，左子树是完全二叉树，右子树是满二叉树，左边高度等于右边高度加上1
    //情况3，左子树是满二叉树，右子树是满二叉树，左边高度等于右边高度加上1
    //情况4，左子树是满二叉树，右子树是完全二叉树，左右高度相等

    //对于每一颗子树，是否是满二叉树、是否是完全二叉树、高度是多少
    public static class Info{
        public Boolean isFull;
        public Boolean isCBT;
        public int height;

        public Info(boolean isFull,boolean isCBT,int height){
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static Info process(Node x){
        if(x == null){
            return new Info(true,true,0);
        }
        Info leftNode = process(x.left);
        Info rightNode = process(x.right);
        int height = Math.max(leftNode.height,rightNode.height) +1;
        //情况1
        boolean isFull = leftNode.isFull && rightNode.isFull && leftNode.height == rightNode.height;

        boolean isCBT = false;
        if(isFull){
            isCBT = true;
        }else{//整体不是一个满二叉树
            if(leftNode.isCBT && rightNode.isCBT){
                if(leftNode.isCBT && rightNode.isFull && leftNode.height == rightNode.height +1){//情况2
                    isCBT = true;
                }
                if(leftNode.isFull && rightNode.isFull && leftNode.height == rightNode.height +1){//情况3
                    isCBT = true;
                }
                if(leftNode.isFull && rightNode.isCBT && leftNode.height == rightNode.height){//情况4
                    isCBT = true;
                }
            }

        }
        return new Info(isFull,isCBT,height);
    }

    public static Info process1(Node x){
        if(x == null){
            new Info(true,true,0);
        }
        Info leftInfo = process1(x.left);
        Info rightInfo = process1(x.right);
        int height = Math.max(leftInfo.height,rightInfo.height) +1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if(isFull){
            isCBT = true;
        }else {
            if(leftInfo.isCBT && rightInfo.isCBT){
                if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height +1){
                    isCBT = true;
                }
                if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height +1){
                    isCBT = true;
                }
                if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
                    isCBT = true;
                }
            }
        }
        return new Info(isFull,isCBT,height);

    }

    public static Boolean isCBT(Node x){
        if(x == null){
            return true;
        }
        Info res = process(x);
        return res.isCBT;
    }
}
