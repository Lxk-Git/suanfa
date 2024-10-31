package txxx.class11;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/4 14:57
 */
public class Code10_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    //第一步，判断一个单链表是不是有环，有就返回入环节点，没有就返回null
    //第二步，如果都是无环节点，相交了就返回第一个相交的节点，没有相交就返回null
    //第三步，如果都是有环节点，相交有三种情况，
    //情况一两个有环单链表不相交，返回null，
    //情况二两个链表在环外相交,返回相交节点，
    //情况三两个链表在环内相交，两个入环节点都行，选一个。


    //第一步，判断单链表是不是有环，
    // 使用快慢指针，快指针一次走两步，慢指针一次走一步，
    // 如果快指针走到了null,就代表没有环,也可以用慢指针走到null来判断，但是fast快，就用他比较好，
    // 如果快指针没有走到null,那么快慢指针一定会在环内相遇（这个就是结论，不去证明），并且此时让快指针从head头节点出发，按慢指针一步一步走，慢指针从环内出发一步一步走，再次相遇就是入环节点
    // 这个方法很有意义，可以判断一个链表是不是有环，还能找到入环节点。
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //如果没有走到null,那他们就一定相遇了，那么现在把fast放到head上
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        //跳出这个循环的时候就是他们在入环节点相遇的时候。
        return slow;
    }

    public static Node getLoopNode2(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    //第二步,相交返回第一个相交节点，不相交返回null
    //如果两个链表，从头遍历到尾部，head1的尾部end1,head2的尾部end2，end1==end2就代表相交，但是这是相交的最后一个节点，不相等就不相交。
    //下面是找第一个节点。
    //两个相交的链表，谁长谁就先走长的那部分，然后再同时一步一步的走，出现第一个相等的节点，就是相交节点

    public static Node noLoopNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        //用一个变量就可以算两个链表的长度了，还能指导谁长，长多少，秒啊
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1 != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            n--;
            cur2 = cur2.next;
        }
        //如果end1不等于end2就代表不相交
        if (cur1 != cur2) {
            return null;
        }
        //相交就找到谁长谁短
        cur1 = n > 0 ? head1 : head2;//长的给cur1
        cur2 = cur1 == head1 ? head2 : head1;//短的给cur2
        Math.abs(n);
        //让长的先走n步
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        //让两个相等的时候，就是相交节点
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static Node noLoopNode2(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1 != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1.value != cur2.value) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1.value != cur2.value) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur2;
    }

    //第三步，如果都是有环节点，相交有三种情况，
    // 情况一两个有环单链表不相交，返回null，
    // 情况二两个链表在环外相交,返回相交节点，
    // 情况三两个链表在环内相交，两个入环节点都行，选一个。
    //如果两个链表的入环节点loop1和loop2相等的，那就是情况二，否则就是情况一和情况三
    //如果loop1继续next能走到loop2就是情况三，返回loop1或者loop2;否则就是情况一,返回null
    //情况二的时候和无环相交很像，可以将end点换成loop1，然后长的先走n步，再一起走，相遇的地方就是入环节点。
    public static Node haveLoopNode(Node head1, Node head2, Node loop1, Node loop2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {//不需要管这个n是不是包含入环的节点，在下面的121行代码中都会走到相交的地方的
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop1) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;

            Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {//当还没有走一圈
                if (cur1 == loop2) {
                    return loop2;
                }
                cur1 = cur1.next;
            }
            //当走完一圈，loop1不能走到loop2就是情况一，无相交
            return null;
        }
    }

    public static Node haveLoopNode2(Node head1, Node head2, Node loop1, Node loop2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1.value != cur2.value) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1){
                if(cur1 == loop2){
                    return loop2;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoopNode(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return haveLoopNode(head1, head2, loop1, loop2);
        }
        return null;
    }

    public static Node getIntersectNode2(Node head1, Node head2) {
        if(head1 == null || head2 ==null){
            return null;
        }
        Node loop1 = getLoopNode2(head1);
        Node loop2 = getLoopNode2(head2);
        //如果是无环
        if(loop1 == null && loop2 == null){
            return noLoopNode2(head1,head2);
        }
        //如果有环
        if(loop1 != null && loop2 != null){
            return haveLoopNode2(head1, head2, loop1, loop2);
        }
        return null;

    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
/*        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);*/
        // 0->9->8->6->7->null
        Node head2 = new Node(0);
/*        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next.next; // 8->7
        System.out.println(getIntersectNode(head1, head2).value);*/
        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4
        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next; // 8->4
        System.out.println(getIntersectNode(head1, head2).value);
        // 0->9->8->6->4->5->6..
/*        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);*/
    }
}
