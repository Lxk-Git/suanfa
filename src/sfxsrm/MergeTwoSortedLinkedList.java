package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/23 16:55
 */
public class MergeTwoSortedLinkedList {
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    //首先这是两个有序的链表
    //如果是从小到大的话，小的肯定是放在前面的
    public static ListNode mergeTwoLink(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        /*
         * 这一大截是可以优化的
        //选出两个链表谁的开头小
        ListNode S = head1.val <= head2.val ? head1 : head2;
        ListNode B = S == head1 ? head2 : head1;
        ListNode head = S;//最后连起来的时候，要返回一个有效的head
        ListNode pre = head;//还要一个可以一直移动的pre指针
        ListNode cur1 = S.next;//用一个新的链表存储开头小的链表中除去开头的部分。
        ListNode cur2 = B;
         */
        ListNode head = head1.val <= head2.val ? head1 : head2;
        ListNode cur1 = head1.next;
        ListNode cur2 = head == head1 ? head2 : head1;
        ListNode pre = head;
        while (cur1 != null && cur2 != null) { //当他们两个都不为空的时候进行比较
            if (cur1.val <= cur2.val) {//cur1.val小的时候
                pre.next = cur1;//pre.next指向cur1
                cur1 = cur1.next;//cur1的指针向cur1.next移动
            } else {//cur2.val小的时候
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;//pre指针向下移动
        }
        //当有一条链表比较长,pre.next就指向那条长的
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }

}
