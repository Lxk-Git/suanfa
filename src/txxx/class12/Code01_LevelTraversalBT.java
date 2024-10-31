package txxx.class12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/5 14:53
 */
public class Code01_LevelTraversalBT {
    //树的层序遍历
    //用队列，先进先出，左树不为空加入左树，右树不为空加入右树
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value = data;
        }
    }

    public static void level(Node head){
        if(head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            System.out.print(head.value+", ");
            if(head.left != null){
                queue.add(head.left);
            }
            if(head.right != null){
                queue.add(head.right);
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        level(head);
        System.out.println("========");
    }
}
