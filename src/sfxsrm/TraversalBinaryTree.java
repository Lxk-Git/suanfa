package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/29 15:51
 */
public class TraversalBinaryTree {
    //遍历分为先序遍历：头左右，中序：左头右，后序：左右头
    //树的遍历就是递归遍历，就是3次，第一次经过的就是先序，第二次的就是中序，三次就是后序
    //例如一颗树先序遍历为：1,2,4,5,3,6,7.中序为：4,2,5,1,6,3,7.后序为：4,5,2,6,7,3,1
    //他的递归遍历其实是1,2,4,4,4,2,5,5,5,2,1,3,6,6,6,3,7,7,7,3,1
    //创建一个链表
    public static class ListNode{
        public int value;
        public ListNode left;
        public ListNode right;

        public ListNode(int data){
            this.value = data;
        }
    }

    //树的遍历 ，就是递归遍历，然后选择打印的地方就可以把第i次出现的数据打印出来
    public static void f(ListNode head){
        if(head == null){
            return;
        }
        //递归左子树
        System.out.println(head.value+"/前序");//第一次出现的
        f(head.left);
        System.out.println(head.value+"·中序");//第二次出现的
        //递归右子树
        f(head.right);
        System.out.println(head.value+"、后序");//第三次出现的
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.left = new ListNode(2);
        head.right = new ListNode(3);
        head.left.left = new ListNode(4);
        head.left.right = new ListNode(5);
        head.right.left = new ListNode(6);
        head.right.right = new ListNode(7);
        f(head);
    }






}
