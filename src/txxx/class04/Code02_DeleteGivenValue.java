package txxx.class04;

//把给定值都删除
public class Code02_DeleteGivenValue {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// head = removeValue(head, 2);
	public static Node removeValue(Node head, int num) {
		// head来到第一个不需要删的位置
		while (head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		// 1 ) head == null
		// 2 ) head != null
		Node pre = head;
		Node cur = head;
		while (cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}


	public static Node removeValue1(Node head, int num) {
		//找到第一个不需要删除的head
		while (head != null){
			if(head.value != num){
				break;
			}
			head = head.next;
		}
		//给两个指针，一开始一起，后面通过他们两个的一前一后删除掉不需要的数据
		Node pre = head;//他他后移动
		Node cur = head;//让他先移动
		while (cur != null){
			//1.如果cur的value是等于num的，那就让pre.next = cur.next,就是跳过他
			//2.如果不是，那就继续让pre和cur相等
			//3.最后是或者不是，都要让cur继续向下移动
			if(cur.value == num){
				pre.next = cur.next;
			}else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}



}
