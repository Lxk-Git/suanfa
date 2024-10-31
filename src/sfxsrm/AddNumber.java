package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/23 15:59
 */
public class AddNumber {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //计算一下链表长度
    public static int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }

    //3种情况
    //第一种，两个一样长，两两相加，大于等于0进1
    //第二种，有一个长的，那就自己加上前面的进位
    //第三种，那个最长的加上进位之后还是产生了进位1
    public static ListNode addToNumber(ListNode head1, ListNode head2) {
        int len1 = listLength(head1);
        int len2 = listLength(head2);
        //比较两个的长短
        ListNode l = len1 >= len2 ? head1 : head2;//这个长
        ListNode s = l == head1 ? head2 : head1;//这个短
        ListNode curL = l;
        ListNode curS = s;
        ListNode lastL = curL;//用一个lastL来应对第三种情况
        int carry = 0;//用他来记录进位数
        int sumNum = 0;//用一个
        while(curS != null){
            sumNum = curL.val + curS.val + carry;
            curL.val = sumNum % 10 ;//取余然后将这个数字放到长的链表上去
            carry = sumNum / 10 ; //取整数得到进位，为下次加减做准备
            lastL = curL ; //lastL指针放在长的链表上，并随着curL指针的移动而移动移动
            curL = curL.next;
            curS = curS.next; //移动到下一个数据
        }
        while (curL != null){
            sumNum = curL.val +carry;
            curL.val = sumNum % 10 ;
            carry = sumNum / 10 ;
            lastL = curL ;
            curL = curL.next;
        }
        if(carry !=0 ){//最后还是不等于0代表着他还有进位
            lastL.next = new ListNode(1);
        }
        return l;
    }
}
