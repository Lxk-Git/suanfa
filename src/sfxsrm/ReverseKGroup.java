package sfxsrm;

public class ReverseKGroup {

    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int data){
            this.val = data;
        }
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        // 第一组凑齐了！
        head = end;//这一句的作用体现在没有完整的k的时候就不用交换了！
        reverse(start, end);
        // 在这个地方start经过交换已经是上一组的结尾节点
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;//重新开始一组
            end = getKGroupEnd(start, k);
            if (end == null) {//如果不满足3个，直接返回上一组的尾
                return head;
            }
            //满足就再往下
            reverse(start, end);
            lastEnd.next = end;//上一组连接新的一组的没有交换前的最后一个数组
            lastEnd = start;//将新交换过的开始是头节点的位置标记为尾节点，这是为了继续循环
        }
        return head;//最后返回的是head,因为上面做的全部操作都是head的新引用做的，这个时候返回他，他还是在原处，他还是他！
    }
    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }
    public static void reverse(ListNode start, ListNode end) {
        end = end.next;//下一组的节点
        ListNode pre = null;
        ListNode cur = start;//变的是cur,start还在在原点，开始是头节点，交换完毕就变成了尾部节点
        ListNode next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;//将下一组的节点接到start下面！
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
/*        while (node !=null){
            int value = node.val;
            System.out.print(value+"-->");
            node =node.next;
        }*/
        reverseKGroup(node,3);
    }
}
