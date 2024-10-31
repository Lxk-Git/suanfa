package sfxsrm;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/1 10:11
 */
public class BinaryTreeLevelOrderTraversalII {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static List<List<Integer>> treePoll(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();//创建一个队列，用来存树
        queue.add(root);//将头节点存进去
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> nodes = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                nodes.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right !=null){
                    queue.add(node.right);
                }
            }
            ans.add(0,nodes);
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(7);
        List<List<Integer>> lists = treePoll(treeNode);
        System.out.println(lists.toString());


    }





}
