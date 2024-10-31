package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/29 16:39
 */
public class SameTree {

    //判断两颗树是不是结构一样
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int data){
            this.val=data;
        }
    }
    //头和头比，左边和左边比，右边和右边比    还是递归遍历，递归遍历就是会本身遍历一次，调了n次递归就遍历n次,一共是n+1
    public static boolean isSameTree(TreeNode t1,TreeNode t2){
        if(t1 == null ^ t2 == null){ //异或 11是0,00是0就是false，10和01是1就是true,返回就是false.
            return false;
        }
        if(t1 == null && t2 == null){
            return true;
        }
        return t1.val == t2.val && isSameTree(t1.left,t2.left) && isSameTree(t1.right,t2.right);//&&就是全部为True的时候才返回True
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);

        TreeNode head2 = new TreeNode(1);
        head2.left = new TreeNode(2);
        head2.right = new TreeNode(4);

        boolean sameTree = isSameTree(head, head2);

    }

}
