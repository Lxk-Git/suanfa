package txxx.class12;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/6 14:54
 */
public class Code03_EncodeNaryTreeToBinaryTree {
    public static class Node {
        public int val;
        public List<Node> children;
        public Node() {
        }
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public static TreeNode enCode(Node root){
        if(root == null){
            return null;
        }
        TreeNode head = new TreeNode(root.val);//用root多叉树的头的值做TreeNode二叉树的头节点
        //让所有子节点都做左子树的右孩子（包含左子树本身）
        head.left = en1(root.children);
        return head;
    }
    //将多叉树变成了二叉树
    public static TreeNode en(List<Node> children){
        TreeNode head = null;
        TreeNode cur = null;
        if(children!=null){
            for(Node child:children){
                TreeNode tNode = new TreeNode(child.val);
                if(head == null){//相当于把所有子节点都弄成一个只有右节点的数
                    head = tNode;
                }else {
                    // 将新进来的节点加到这个新树的右节点上,为啥这里不用head,老问题了，
                    // 请看55行，tNode每次都会赋值到cur中的，所以用head会一直只有两个节点，新进来的节点会被覆盖掉
                    cur.right = tNode;
                }
                cur = tNode;//每个新进来的点有它的children，所以应该重复en方法
                cur.left = en(child.children);
            }
        }
        return head;
    }

    public static TreeNode en1(List<Node> children){
        if(children == null){//本地测试需要加这个跳出递归的条件
            return null;
        }
        TreeNode head = null;
        TreeNode cur = null;//主要是用来传递变化的值
        if(children != null){
            for (Node child:children){
                TreeNode tNode = new TreeNode(child.val);
                if(head == null){
                    head = tNode;
                }else {
                    //这里就是让所有子节点全部变为左树的右节点
                    cur.right = tNode;//这里用cur的好处就是cur的值可以变化，head是最后返回的不能变化
                }
                cur = tNode;//这里就是用来传递变化的值的
                cur.left = en1(child.children);
            }
        }
        return head;
    }
    //把所有的子节点都换成左孩子的右节点
    public static TreeNode en2(List<Node> children){
        if(children == null){
            return null;
        }
        TreeNode head = null;
        TreeNode cur = null;
        if(children!= null){
            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val);
                if(head == null){
                    head = cur;
                }else {
                    cur.right = tNode;
                }
                cur = tNode;
                cur.left = en2(child.children);
            }
        }
        return head;
    }



    //不是很熟悉，还要再想想（第二次已经想明白）
    //将二叉树变多叉树
    //重点就是一个节点和一个List的孩子集合
    //然后代码编写上不是从头到尾的每个节点每个节点还原，而是从最后一层的所有节点还原到最上面一层
    public static Node deCode(TreeNode root){
        if(root == null){
            return null;
        }
        return new Node(root.val,de(root.left));//de(root.left)就是children
    }

    public static List<Node> de(TreeNode node){
        if(node == null){
            return null;
        }
        List<Node> children = new LinkedList<>();
        while (node != null){
            Node node1 = new Node(node.val,de(node.left));//node节点下面可能还有children，所以要递归
            children.add(node1);//第一次到这里的时候，是拿到最下面的那一层的
            node = node.right;//因为到最下面的左边时候，只有右边，所以把右边给到node
        }
        return children;
    }
    //从多叉树生成二叉树是把多叉变成左子树的右孩子，多叉是变成了右孩子节点的，所以二叉树还原也是一样的
    public static List<Node> de2(TreeNode node){
        if(node == null){
            return null;
        }
        List<Node> children = new ArrayList<>();
        while (node!= null){
            Node node1 = new Node(node.val, de2(node.left));
            children.add(node1);
            node = node.right;
        }
        return children;
    }


    public static List<Node> de1(TreeNode node){
        if(node == null){ //本地测试需要加这个跳出递归的条件
            return null;
        }
        List<Node> children = new LinkedList<>();
        while (node!= null){
            Node node1 = new Node(node.val,de(node.left));
            children.add(node1);
            node = node.right;
        }
        return children;
    }



    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        List<Node> children = new ArrayList<>();
        children.add(node);
        children.add(node2);
        children.add(node3);
        children.add(node4);
        Node root = new Node(0,children);
        TreeNode treeNode = enCode(root);
    }


}
