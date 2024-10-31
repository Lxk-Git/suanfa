package sfxsrm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/2 17:09
 */
public class PathSumII {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayList<Integer> path = new ArrayList<>();
        process(root, path, 0, sum, ans);
        return ans;
    }
    public static void process(TreeNode x, List<Integer> path, int preSum, int sum, List<List<Integer>> ans) {
        if (x.left == null && x.right == null) {//这个是叶子节点
            if (preSum + x.val == sum) {
                path.add(x.val);
                ans.add(copy(path));//要用copy的path,因为原有的path是还要递归做处理，以免变化！
                path.remove(path.size() - 1);//叶子节点递归还要返回上层，所有这里要减去最后一个位置  （清理现场的问题）
            }
            return;
        }

        //这里非叶子节点
        path.add(x.val);
        preSum += x.val;
        if (x.left != null) {
            process(x.left, path, preSum, sum, ans);
        }
        if (x.right != null) {
            process(x.right, path, preSum, sum, ans);
        }
        path.remove(path.size() - 1);//非叶子节点递归上层也要删除最后一个位置，这样才能干净的回到递归的上一层   （清理现场的问题）
    }
    public static List<Integer> copy(List<Integer> path) {
        List<Integer> ans = new ArrayList<>();
        for (Integer num : path) {
            ans.add(num);
        }
        return ans;
    }
}
