package txxx.class31;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/4 13:41
 */
public class Code01_MorrisTraversal {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //morris遍历节约空间
    //第一大步，如果cur没有左孩子，那么cur向左边移动
    //第二大步，如果cur有左孩子，那么找到左孩子的最右节点mostRight
    //分为两种情况：情况一：如果最右节点的右指针指向null,那就让他指向cur,然后cur向左子树移动cur=cur.left,代码里面暂时跳槽循环
    //           情况二：如果最右节点的右指针指向cur,那就让他指向null,然后让cur向右子树移动cur=cur.right
    //最后第三步，如果cur为空的时候就停止。
    //morris遍历应该是 4,2，1,2,3,4,6,5，6,7
    public static void morris(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur !=null){
            if(cur.left != null){
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){//情况一
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {//情况二
                    mostRight.right =null;
                }
            }
            cur = cur.right;
        }
    }
    //先序遍历，没有左节点，第一次遇到就打印，有左节点，第一次遇到就打印
    public static void morrisIn(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur !=null){
            if(cur.left != null){
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){//第一次遇到
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {//情况二
                    mostRight.right =null;
                }
            }else {//没有左节点
                System.out.print(cur.value+" ");
            }

            cur = cur.right;
        }
        System.out.println();
    }
    //中序遍历，只能到自己一次，第一次就打印，能到自己两次，第二次才打印
    public static void morrisPre(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur !=null){
            if(cur.left != null){
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){//情况一
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {//情况二
                    mostRight.right =null;
                }
            }
            //有左孩子会在第二次遇到，没有左孩子，第一次就遇到了
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    //后序遍历：第二次遇到，逆序打印他左树的右边界，最后再逆序打印整棵树的右边界（这里用反转来打印）
    public static void morrisPos(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur !=null){
            if(cur.left != null){
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){//情况一
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {//这里就是第二次遇到
                    mostRight.right =null;
                    //逆序打印他左树的右边界
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        //这里再最后逆序原来整个树的右边界
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node head){
        //先逆序
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        //在逆序回来就是还原
        reverseEdge(tail);
    }
    //逆序
    public static Node reverseEdge(Node from){
        Node pre = null;
        Node next = null;
        while (from !=null){
            //用from.right指向next,第一次循环next=3，第二次循环，next=null
            next = from.right;
            //用pre指向from.right,第一次循环from.right=null，pre=null，第二次循环pre=2,from.right=2(第一次是3,这里就交换了)
            from.right = pre;
            //用from代替pre,第一次循环pre=2，第二次循环pre=3
            pre = from;
            //next值给到from,第一次循环from=3，此时第一次循环from还没有为空,第二次循环from=null;
            from = next;
        }
        return pre;
    }
//    搜索二叉树怎么判断
//    搜索二叉树的中序遍历，是从小到大的升序。
//    所以用morris遍历来做的话，只要在中序遍历的地方对比一下就能得到答案。
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        Integer pre = null;
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            //这里是中序遍历，如果pre代表的前一个数字大于等于后面的数字，那么就不是搜索二叉树
            if (pre != null && pre >= cur.value) {
                ans = false;
            }
            pre = cur.value;
            cur = cur.right;
        }
        return ans;
    }



    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
//        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        morrisIn(head);
        morrisPre(head);
        morrisPos(head);
//        printTree(head);

    }
}
