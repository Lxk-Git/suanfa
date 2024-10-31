package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/29 17:13
 */
public class SymmetricTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int data){
            this.val=data;
        }
    }
    //根节点用一个就可以了，相当于两个一样的树在对比
    public static boolean isSysM(TreeNode t){
        return isSymmetricTree(t,t);
    }

    //是否是镜面就是要左子树和右子树是一样的，是一棵树
    public static boolean isSymmetricTree(TreeNode t1,TreeNode t2){
        if(t1 == null ^ t2 == null){ //异或 11是0,00是0就是false，10和01是1就是true,返回就是false.
            return false;
        }
        if(t1 == null && t2 == null){//叶子节点下面都是null
            return true;
        }
        return t1.val == t2.val && isSymmetricTree(t1.left,t2.right) && isSymmetricTree(t1.right,t2.left);
    }
}
