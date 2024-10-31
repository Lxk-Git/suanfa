package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/2 16:43
 */
public class PathSum {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }




    public static boolean isBst = false ;//先假定一个变量，就是没有合适的路径组成sum
    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root ==null){
            return false;
        }
        isBst = false;
        process(root,0,sum);
        return isBst;
    }

    public static void process(TreeNode root,int nodeSum,int sum){ //nodeSum是除了叶节点累加的，sum是要求节点累加之后得到的数字
        //还是先写递归结束的条件
        if(root.left == null && root.right == null){//是叶节点了
            if(root.val + nodeSum == sum){//如果叶节点加上前面所有的累加和等于sum那就证明这棵树满足累加和
                isBst = true;
            }
            return;//否则就不符合，直接return
        }

        nodeSum += root.val;
        //左右递归！
        if(root.left !=null){
            process(root.left,nodeSum,sum);
        }
        if(root.right !=null){
            process(root.right,nodeSum,sum);
        }
    }
}
