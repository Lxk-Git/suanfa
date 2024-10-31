package dcgpt.class05;

/**
 * @Author: LiuXingKun
 * @Date: 2023/4/7 9:55
 */
// 本题测试链接 : https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
public class Code01_ConstructBinarySearchTreeFromPreorderTraversal {
    // 不用提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 提交下面的方法
    public static TreeNode bstFromPreorder1(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        return process1(pre, 0, pre.length - 1);
    }


    public static TreeNode process1(int[] pre, int L, int R) {
        //如果越界，那就返回null
        if (L > R) {
            return null;
        }
        int firstBig = L + 1;
        //把head头节点下面属于左子树的划分出来
        //因为是搜素二叉树，所以有左子数上的所有节点都不会大于头节点，右子树上的所有节点都不会小于头节点
        //当pre[firstBig] > pre[L]的时候就是左子树的边界了
        for (; firstBig <= R; firstBig++) {
            if (pre[firstBig] > pre[L]) {
                break;
            }
        }
        TreeNode head = new TreeNode(pre[L]);
        //那么递归左右子树就能得到答案咯
        head.left = process1(pre, L + 1, firstBig - 1);
        head.right = process1(pre, firstBig, R);
        return head;
    }


    public static TreeNode bstFromPreorder2(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        return process2(pre, 0, pre.length - 1);
    }

    private static TreeNode process2(int[] pre, int L, int R) {
        if (L > R) {
            return null;
        }
        int F = L + 1;
        for (; F <= R; F++) {
            if (pre[F] > pre[L]) {
                break;
            }
        }
        TreeNode head = new TreeNode(L);
        head.left = process2(pre,L+1,F-1);
        head.right = process2(pre,F,R);
        return head;
    }


}
