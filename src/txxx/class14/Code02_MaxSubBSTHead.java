package txxx.class14;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/12 20:34
 */
public class Code02_MaxSubBSTHead {
    //求树的最大搜索树的头节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    //需要以下元素，最大搜索二叉树的头节点，最大搜索二叉树一共多少个节点，最大值最小值
    //三种情况：
    //情况1，最大搜索二叉树不经过x节点，并且最大搜索二叉树在左边
    //情况2，最大搜索二叉树不经过X节点，并且最大搜索二叉树在右边，这个用最大二叉树的节点对比
    //情况3，最大搜索二叉树经过X节点，那X的最大搜索二叉树的头节点，一共有左边的节点加上右边的节点加一
    public static class Info {
        public Node maxBSTHead;
        public int maxBSTSize;
        public int max;
        public int min;

        public Info(Node h, int size, int mi, int ma) {
            maxBSTHead = h;
            maxBSTSize = size;
            min = mi;
            max = ma;
        }
    }


    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftNode = process(x.left);
        Info rightNode = process(x.right);
        Node maxBSTHead = null;
        int maxBSTSize = 0;
        int max = x.value;
        int min = x.value;
        if (leftNode != null) {//如果左树不为空，且最大搜索二叉树在左边
            max = Math.max(max, leftNode.max);
            min = Math.min(min, leftNode.min);
            maxBSTSize = leftNode.maxBSTSize;
            maxBSTHead = leftNode.maxBSTHead;
        }
        if (rightNode != null) {
            max = Math.max(max, rightNode.max);
            min = Math.min(min, rightNode.min);
            if (rightNode.maxBSTSize > maxBSTSize) {//要右边的数量大于左边的时候，才能说最大搜索二叉树的头在右边
                maxBSTHead = rightNode.maxBSTHead;
                maxBSTSize = rightNode.maxBSTSize;
            }
        }
        if (leftNode == null ? true : (leftNode.max < x.value && leftNode.maxBSTHead == x) //leftNode.maxBSTHead == x就是经过x点
                && rightNode == null ? true : (rightNode.min > x.value && rightNode.maxBSTHead == x)) {
            maxBSTHead = x;
            maxBSTSize = (leftNode == null ? 0 : leftNode.maxBSTSize) + (rightNode == null ? 0 : rightNode.maxBSTSize) + 1;
        }
        return new Info(maxBSTHead, maxBSTSize, max, min);
    }




    public static Info process2(Node x){
        if(x == null){
            return null;
        }
        Node maxBSTHead = null;
        int maxBSTSize = 0;
        int max = x.value;
        int min = x.value;
        Info leftNode = process2(x.left);
        Info rightNode = process2(x.right);
        if(leftNode != null){
            max = Math.max(max,leftNode.max);
            min = Math.min(min,leftNode.min);
            maxBSTHead = leftNode.maxBSTHead;
            maxBSTSize = leftNode.maxBSTSize;
        }
        if(rightNode != null){
            max = Math.max(max,rightNode.max);
            min = Math.min(min,rightNode.min);
            if(rightNode.maxBSTSize > maxBSTSize){
                maxBSTHead = rightNode.maxBSTHead;
                maxBSTSize = rightNode.maxBSTSize;
            }
        }
        if(leftNode == null ? true : (leftNode.max < x.value && leftNode.maxBSTHead == x) &&
                rightNode == null ? true : (rightNode.min > x.value && rightNode.maxBSTHead == x) ){
            maxBSTHead = x;
            maxBSTSize = (leftNode == null ? 0 : leftNode.maxBSTSize) + (rightNode == null ? 0 : rightNode.maxBSTSize) + 1;
        }
        return new Info(maxBSTHead,maxBSTSize,max,min);
    }

    public static Node maxSubBSTHead(Node head){
        if(head == null){
            return null;
        }
        return process(head).maxBSTHead;
    }
}
