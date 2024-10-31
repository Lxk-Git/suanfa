package txxx.class13;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/11 15:22
 */
public class Code06_MaxSubBSTSize {
    //找最大子树是搜索二叉树有多少个节点
    //最大搜索二叉树用递归套路需要以下属性
    //最大搜索二叉树的节点是多少，整个树的节点是多少，最大值是多少，最小值是多少

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int value) {
            val = value;
        }
    }
    public static class Info {
        public int maxBSTSubtreeSize;
        public int allSize;
        public int max;
        public int min;
        public Info(int m, int a, int ma, int mi) {
            maxBSTSubtreeSize = m;
            allSize = a;
            max = ma;
            min = mi;
        }
    }

    public static Info process(TreeNode x){
        if(x == null){
            return null;
        }
        Info leftNode = process(x.left);
        Info rightNode = process(x.right);
        int max = x.val;//全局变量
        int min = x.val;//全局变量
        int allSize = 1;//全局变量
        //以下两个if循环是统计经过x一共有多少个节点和最大值最小值是多少
        if(leftNode != null){
            max = Math.max(max,leftNode.max);
            min = Math.min(min,leftNode.min);
            allSize += leftNode.allSize;
        }
        if(rightNode != null){
            max = Math.max(max,rightNode.max);
            min = Math.min(min,rightNode.min);
            allSize += rightNode.allSize;
        }
        int p1 = -1;//不经过X节点
        if(leftNode != null){
            p1 = leftNode.maxBSTSubtreeSize;
        }
        int p2 = -1;//不经过X节点
        if(rightNode != null){
            p2 = rightNode.maxBSTSubtreeSize;
        }
        int p3 = -1;//经过X节点
        boolean leftIsBST = leftNode == null ? true : (leftNode.maxBSTSubtreeSize == leftNode.allSize);//左树是不是搜索树
        boolean rightIsBST = leftNode == null ? true : (rightNode.maxBSTSubtreeSize == rightNode.allSize);//右树是不是搜索树
        if(leftIsBST && rightIsBST){
            boolean lessMaxLeftNode = leftNode == null ? true :(leftNode.max < x.val);//左树最大值是不是小于X的值
            boolean moreMinRightNode = rightNode == null ? true : (x.val < rightNode.min);//右树最小值是不是大于X的值
            if(lessMaxLeftNode && moreMinRightNode){
                int leftNodeSize = leftNode == null ? 0 : leftNode.allSize;
                int rightNodeSize = rightNode == null ? 0 : rightNode.allSize;
                p3 = leftNodeSize + rightNodeSize + 1;
            }
        }
        return new Info(Math.max(Math.max(p1,p2),p3),allSize,max,min);
    }

    public static int maxSubBSTSize(TreeNode head){
        if(head == null){
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }


    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(8);
        head1.left = new TreeNode(6);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(7);
        head1.right = new TreeNode(10);
        head1.right.left = new TreeNode(9);
        head1.right.right = new TreeNode(12);
        maxSubBSTSize(head1);
    }
}
