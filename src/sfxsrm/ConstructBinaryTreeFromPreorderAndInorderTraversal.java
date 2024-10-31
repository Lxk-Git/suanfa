package sfxsrm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/30 10:47
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }


    //用一个先序遍历和中序遍历还原一棵树
    //先序遍历为pre[L1.......R1],中序遍历为in[L2........R2]
    //第一步是找到头节点，这个简单，先序的L1就是头节点，
    //第二步要找到左右头节点下的左右子树的节点是哪些，这个就需要中中序遍历中找到头节点
    //然后递归左右子树就可以完成树的还原了。


    public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        //先写终止整个方法的终止条件，这也是后面递归的终止条件
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);//树的头节点
        if (L1 == R1) { //证明只有一个节点,或者已经是叶子节点，这个时候就能递归结束了！
            return head;
        }
        //现在要找L2中的头节点
        int find = L2;
        //while循环，当in[L2]和pre[L1]相等的时候，这个find就是中序遍历中头节点的索引
        while (pre[L1] != in[find]) {
            find++;
        }
        //注意这个L1点，递归之后是会加1变化的
        //那要怎么定位这个find呢，有一个办法是find-L1 = find - L2;为啥？因为两个遍历顺序，他们的左子树的个数是一样的，所以find = L1 +find -L2,
        //为啥不能是find = find -L1+L2,因为find是在L2这个索引上变化的，这样加减是求不到L1索引上正确的左子树区间的
        //右边同理！
        head.left = f(pre, L1+1, L1 +find -L2, in, L2, find-1);
        head.right = f(pre,L1 +find -L2 +1,R1,in,find +1,R2);
        return head;
    }
    //写完发现这个find每次都要去找非常麻烦，用一什么办法优化
    public static TreeNode buildTree(int[] pre,int[] in){
        if(pre ==null || in ==null ||pre.length != in.length){
            return null;
        }
        return f(pre,0,pre.length-1,in,0,in.length-1);
    }

    //优化办法就是直接将in中的值和索引放到一个map中，这样就去getKey就很快就能知道想要的find的索引了！
    public static TreeNode buildTree1(int[] pre,int[] in){
        if(pre ==null || in ==null ||pre.length != in.length){
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int  i =0;i<in.length;i++){
            map.put(in[i],i);
        }//直接将in的值和索引放在一个map中，方便查询
        return g(pre,0,pre.length-1,in,0,in.length-1,map);
    }

    public static TreeNode g(int[] pre,int L1,int R1,int[] in,int L2,int R2,Map<Integer,Integer> map){
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);//树的头节点
        if (L1 == R1) { //证明只有一个节点,或者已经是叶子节点，这个时候就能递归结束了！
            return head;
        }
        int find = map.get(pre[L1]);//直接找到这个find

        head.left = g(pre, L1+1, L1 +find -L2, in, L2, find-1,map);
        head.right = g(pre,L1 +find -L2 +1,R1,in,find +1,R2,map);
        return head;
    }
}
