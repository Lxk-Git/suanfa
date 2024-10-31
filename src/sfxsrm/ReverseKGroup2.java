package sfxsrm;


public class ReverseKGroup2 {

    public static class ListNode{
        public int value;
        public ListNode next;
        public ListNode(int data){
            this.value = data;
        }
    }

    //第一步，先将整个队列按照k个值划分，返回他的末尾数字
    public static ListNode queueGroup(ListNode start,int k){
        while (--k !=0 && start !=null){ //start !=null是针对划分不满K的时候就直接跳出循环了！
            start = start.next;
        }
        return start;
    }
    //第二步，将拿到的一组队列进行反转
    public static void reverseQueue(ListNode start,ListNode end){
        end = end.next;//先用这个end存下一组的开头，因为满足k的一组反转之后还要连接下一组的开头！
        ListNode next = null; //单链表的反转创建的两个空的节点
        ListNode pre = null;
        ListNode cur = start; //创建一个cur来代替start,下面的操作都是用到cur,start还是在原地
        while (cur != end){ //两个重合了就代表交换完了
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;//最后再用start.next指向下一组的开头！
    }

    //准备好了两个重要的方法，开始对链表操作
    //为了最后交换完毕的链表有效，所以肯定是要返回一个head的
    //注意，满足一直有k个数据的时候才交换，不满足直接就不管！
    public static ListNode reverseKGroup(ListNode head, int k){
        ListNode start = head;//jvm知识，又创建一个新的内存地址来存head,下面操作的都是start,head的内存地址是不变的！
        ListNode end = queueGroup(start, k);//返回的是新分好的end尾部节点
        if(end == null){//如果返回的尾部节点是null,代表没有数据，或者没有满足一组
            return head;
        }
        //针对满足了一组，后面的不再满足一组的时候
        //这里先用head吧end存下来,因为后面做了交换之后start要变成尾，end变成了头，现在先将head指针放在这里，没有满足k个直接返回。
        head = end;
        //开始交换满足的一组
        reverseQueue(start,end);
        //先用一个新的内存地址存交换后的start节点
        ListNode lastEnd = start;
        //交换完毕之后，已经将新交换好的连接下一组的开头了，那么下一组还有没有数据？
        while (lastEnd.next != null){//不为空就代表还有数据，可以继续分
            //把下一组满足k的提取出来
            //start节点变成了lastEnd.next了，但是lastEnd位置还没有改变的
            start = lastEnd.next;
            end = queueGroup(start, k);
            //新的一组是不是满足k呢
            if(end == null){
                //如果不满足，就直接返回head了
                return head;
            }
            //满足了就继续对新的一组进行转换
            reverseQueue(start,end);
            //lastEnd.next指向存新数组的头节点
            lastEnd.next = end;
            //lastEnd 再向新一组一直没用变的start节点移动！好进入下一次循环
            lastEnd = start;
        }

/*        ListNode lastEnd = start;
        while (lastEnd.next != null){
            ListNode newStart = lastEnd.next; //错误原因是要一直使用一个node,这里是新的node了，所以错了！
            ListNode newEnd = queueGroup(newStart, k);
            if(newEnd ==null){
                return head;
            }
            reverseQueue(newStart,newEnd);
            lastEnd.next = newEnd;
            lastEnd = newStart;
        }*/

        return head;


    }
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(6);
        node.next.next.next.next.next.next = new ListNode(7);
        node.next.next.next.next.next.next.next = new ListNode(8);

/*        while (node !=null){
            int value = node.val;
            System.out.print(value+"-->");
            node =node.next;
        }*/
        reverseKGroup(node,3);
    }




























}
