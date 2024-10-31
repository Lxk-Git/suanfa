package txxx.class10;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/2 20:01
 */
public class
Code10_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //这个方法就是创建一个数组，吧链表里面的数据放到数组中，这是使用容器
    //交换数组中的数字，小于的放在左边，等于的放在中间，大于的放在右边。
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        int i = 0;
        Node cur = head;
        //把链表中的数据塞入数组中
        //先得到数组的长度
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodes = new Node[i];
        cur = head;
        //在把链表中的数放入数组
        for (i = 0; i < nodes.length; i++) {
            nodes[i] = cur;
            cur = cur.next;
        }
        //对数组中的数据开始划分
        arrPartition(nodes, pivot);
        //再把划分好的数组，变成链表的形式
        for (i = 1; i < nodes.length; i++) {
            nodes[i - 1].next = nodes[i];
        }
        nodes[i - 1].next = null;
        return nodes[0];

    }

    public static void arrPartition(Node[] nodes, int pivot) {
        int small = -1;
        int big = nodes.length;
        int index = 0;
        while (index != big) {
            if (nodes[index].value < pivot) {
                swap(nodes, ++small, index++);
            } else if (nodes[index].value == pivot) {
                index++;
            } else {
                swap(nodes, --big, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    //方法2，用链表划分

    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;//小于区域的头
        Node sT = null;//小于区域的尾
        Node eH = null;//等于区域的头
        Node eT = null;//等于区域的尾
        Node mH = null;//大于区域的头
        Node mT = null;//大于区域的尾
        Node next = null;//用一个next来拿head;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    //这两句比较难理解，具体不明白就debug
                    sT.next = head;//此时sT.next和sH.next都是head,这里要用sT是因为尾部一直在变，sH头部是不变的
                    sT = head;//再把head给到sT，这样尾部才能下移。
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }

        //现在将大于等于小于区域的数据连接起来
        if (sH != null) {//小于区域不为空的情况
            sT.next = eH;
            eT = eH == null ? sT : eT;
        }
        if (eH != null) {//没有大于区域的时候就是null了
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    //思路就是弄三个区间
    //小于区间，等于区间，大于区间
    //每个区间都有两个下标，头下标和尾下标
    //注意最后的区间链接
    public static Node listPartition3(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next = null;
        while (head != null) {
            head.next = null;
            next = head.next;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        if (sH != null) {
            sT.next = eH;
            eT = eH == null ? sT : eT;
        }
        if (eH != null) {
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);

    }


    public static Node listPartition4(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next = null;
        while (head != null) {
            head.next = null;
            next = head.next;
            if (head.value < pivot) {
                if(sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            } else if(head.value == pivot){
                if(eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if(mH == null){
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        if(sH != null){
            sT.next = eH;
            eT = eT == null ? sT :eT;
        }
        if(eH != null){
            eT.next = mH;
        }
        return sH = sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 10);
        printLinkedList(head1);
    }
}
