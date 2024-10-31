package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/2 15:07
 */
public class IsBinarySearchTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean is, int ma, int mi) {
            isBST = is;
            max = ma;
            min = mi;
        }
    }

    public static Boolean isBinarySearch(TreeNode root) {
        return process(root).isBST;
    }

    public static Info process(TreeNode root) {
        if (root == null) {//递归终止条件
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int max = root.val;
        int min = root.val;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);//将某子树的根节点和左节点对比，选出最大最小值，如果是搜索树，左边大的节点是根节点，小的是子节点。
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);//右边同理
            min = Math.min(rightInfo.min, min);
        }
        boolean isBST = false;//先设置成不是搜索树
        boolean leftIsBST = leftInfo == null ? true : leftInfo.isBST;
        boolean rightIsBST = rightInfo == null ? true : rightInfo.isBST;
        boolean leftIsMin = leftInfo == null ? true : (leftInfo.max < root.val);//这一步参见上一句注释，还是不懂就画图
        boolean rightIsMax = rightInfo == null ? true : (rightInfo.min > root.val);
        if (leftIsBST && rightIsBST && leftIsMin && rightIsMax) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }
}
