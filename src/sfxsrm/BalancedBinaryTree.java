package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/1 18:11
 */
public class BalancedBinaryTree {


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public boolean isBalanced;
        public int height;
        public Info(boolean i, int h) {
            isBalanced = i;
            height = h;
        }
    }

    public static boolean isBalanced(TreeNode root){
        return process(root).isBalanced;
    }


    //先用逻辑分析画出递归图，想明白递归的终止条件，并想明白返回的结果是什么
    //再用debug看系统栈（frames）是怎么一步一步执行的，这个也可以用递归图来体现，这样就能比较清晰的理解递归算法了
    public static Info process(TreeNode root){
        if (root == null){ //先把递归终止条件写出来
            return new Info(true,0);//也可能是最后的结果
        }
        Info leftInfo = process(root.left);//分成小递归
        Info rightInfo = process(root.right);
        int height = Math.max(leftInfo.height,rightInfo.height)+1;//递归过程
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) <2;//递归过程
        return new Info(isBalanced,height);//小递归结束向上返回的结果，大递归最后的结果
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(7);
        boolean b = isBalanced(treeNode);
    }





}
