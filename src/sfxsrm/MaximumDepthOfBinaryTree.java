package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/29 17:24
 */
public class MaximumDepthOfBinaryTree {

    //判断树的深度
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int i) {
            this.val = i;
        }
    }

    public static int depthOfTree(TreeNode t){
        if(t ==null){  //递归终止条件，满足就开始归来，归来就+1
            return 0;
        }
//        int i = depthOfTree(t.left);
        //最外层左右节点谁最大，再加上1就是深度
        //向内，每次往下就加一，比如先序遍历为1，4，5，3
        //第一次递归开始是左边的4，为0+1，再往下是5，为前面得到得1+1=2，之后是递归右边是3，0+1，最后2比1大取2，加上1得3
        return Math.max(depthOfTree(t.left),depthOfTree(t.right))+1;
//        return i +1;

    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        int i = depthOfTree(head);
    }
}
