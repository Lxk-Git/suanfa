package sfxsrm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/29 13:47
 */
public class MergeKSortedLists {
    //合并多个有序链表
    //第一步是创建一个小根堆，就是创建一个按照最小数值排列的优先队列
    //在这个优先队列中，传入多条有序链表,他们会自动按照头节点从小到大排列好，然后取出链表头最小的链表，是整个取出
    //这条链表头节点向下移动，怎么移动：就是将除了表头的部分又重新加入到队列中
    //当队列为空的时候就结束了。
    public static class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int data) {
            this.value = data;
        }
    }

    //按照链表中的数值从小达到返回
    public static class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            //小得负就是小根堆，如果变一下，写成if else自己定义，小得正就是大根堆了。
            return o1.value - o2.value;//只会返回正负，o1小得到结果就是负数，o1大就是正数，相等为0.
        }
    }

    //合并链表，最后要返回一个有效的链表头head
    public static ListNode mergeNSortLists(ListNode[] listNodes) {//listNodes数组里面存入所有的有序链表
        if (listNodes == null) {
            return null;
        }
        PriorityQueue<ListNode> queues = new PriorityQueue<>(new ListNodeComparator());//创建一个以队列的值从小到大排列的优先队列。
        //先将所有的有序链表整个链表放入优先队列中。
        for (int i = 0; i < listNodes.length; i++) {
            if (listNodes[i] != null) {//当是空的有序链表就没必要加入了
                queues.add(listNodes[i]);
            }
        }
        if (queues.isEmpty()) {
            return null;
        }
        ListNode head = queues.poll();//小根堆有序链表第一个弹出来的值就是最小的，用它来做我们最后需要的链表的头
        ListNode pre = head;//重新弄一个pre去下面操作
        if (pre.next != null) {//这条链表头节点向下移动，怎么移动：就是将除了表头的部分pre.next又重新加入到队列中，这样就完成了新加入进去
            queues.add(pre.next);
        }
        //当队列为空的时候就结束了。
        while (!queues.isEmpty()) {
            ListNode cur = queues.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {//同理，就是将新出来的链表除了表头全部加进优先队列中
                queues.add(cur.next);
            }
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(7);
        ListNode listNode2 = new ListNode(2);
        listNode2.next = new ListNode(5);
        listNode2.next.next = new ListNode(8);
        ListNode listNode3 = new ListNode(3);
        listNode3.next = new ListNode(6);
        listNode3.next.next = new ListNode(9);

        ListNode[] listNodes = {listNode,listNode2,listNode3};
        ListNode node = mergeNSortLists(listNodes);
    }

}
