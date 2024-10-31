package txxx.class10;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/30 16:26
 */
public class Code01_LinkedListMid {
    public static class Node {
        private Node next;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    //奇数长度返回中点，偶数长度返回上中点。
    public static Node method1(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //奇数长度返回中点，偶数长度返回下中点。
    public static Node method2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next !=null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    //奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node method3(Node head){
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = head.next;
            fast = head.next.next;
        }
        return slow;
    }
    //奇数长度返回中点前一个，偶数长度返回下中点前一个（就是上中点）
    public static Node method4(Node head) {
        if (head == null || head.next == null ) {
            return null;
        }
        if( head.next.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    //自己的改进版本
    public static Node method44(Node head) {
        if(head == null || head.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
