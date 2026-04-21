package txxx.ms;

/**
 * Definition for a binary tree node.
 */

//https://blog.csdn.net/T_Y_F_/article/details/144093243

public class Solution {

    /**
     * Finds the lowest common ancestor of two nodes in a binary tree.
     *
     * @param root the root of the binary tree
     * @param p the first node
     * @param q the second node
     * @return the lowest common ancestor of nodes p and q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果 root 为空，或者 root 等于 p 或 q，直接返回 root
        if (root == null || root == p || root == q) {
            return root;
        }

        // 在左子树递归查找
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // 在右子树递归查找
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果左右子树都找到，说明当前 root 是最近公共祖先
        if (left != null && right != null) {
            return root;
        }

        // 否则，返回非空的那个子树
        return left != null ? left : right;
    }


    public TreeNode lowest(TreeNode root,TreeNode p,TreeNode q){
        //如果root是null,root是p或者q直接返回，这也是一个递归条件
        if(root == null || root == p || root == q){
            return root;
        }
        //左右递归
        TreeNode left = lowest(root.left,p,q);
        TreeNode right = lowest(root.right,q,p);
        if(left != null && right != null){
            return root;
        }
        return left != null ? left:right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        Solution solution = new Solution();
        TreeNode lca = solution.lowestCommonAncestor(root, root.left, root.left.right.right);
        System.out.println("LCA: " + lca.val); // 输出 5

    }
}

