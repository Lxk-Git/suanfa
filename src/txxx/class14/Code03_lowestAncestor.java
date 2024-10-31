package txxx.class14;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/12 22:07
 */
public class Code03_lowestAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }
    //x不是最低公共节点
    //1.x在左树
    //2.x在右树
    //3.a,b不全
    //x是最低公共节点
    //1.a在左树，b在右树，相反也是一样
    //2.x就是a,b在左或者右
    //3.x就是b,a在左或者右
    public static class Info{
        public boolean findA;//a节点在不在
        public boolean findB;//b节点在不在
        private Node ans;//最低公共节点
        public Info(boolean fA, boolean fB, Node an) {
            findA = fA;
            findB = fB;
            ans = an;
        }

    }

    public static Info process(Node x,Node a, Node b){
        if(x == null){
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;//a在不在左，在不在右，在不在x
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        Node ans = null;
        if (leftInfo.ans != null) {//最小公共点在不在左树
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {//最小公共点在不在右树
            ans = rightInfo.ans;
        } else {
            if (findA && findB) {//a,b是不是都存在，都存在的时候最小公共点是不是在x点
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }

    public static Node lowestAncestor2(Node head, Node a, Node b) {
        return process(head, a, b).ans;
    }
}
