package txxx.class12;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/8 19:46
 */
public class Code06_SuccessorNode {
    //中序遍历查询任意一个节点的后序节点
    //用一个O(K)时间复杂度的代码k是代表步长
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int data) {
            this.value = data;
        }
    }

    //两个要点，
    //1.如果当前节点有右节点，那么它的右节点的最后一个左孩子节点就是当前节点的后继节点
    //2.如果当前节点没有右节点，如果当前节点的父节点不为空，而且当前节点是父节点的右节点，那么后继节点往上继续循环，直到父节点为空或父节点的右节点不是当前节点。
    public static Node getSuccessorNode(Node node){
        if(node == null){
            return null;
        }
        if(node.right != null){//有右节点
            return getLeftMost(node.right);
        }else{//没有右节点，只有左节点
            Node parent = node.parent;
            while (parent != null && parent.right == node){//parent.right == node这一句很重要，判断出当前节点是父节点的左节点就能跳出循环了
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getSuccessorNode22(Node node){
        if(node == null){
            return null;
        }
        if(node.right != null){
            return getLeftMost(node.right);
        }else {
            Node parent = node.parent;
            while (parent != null && parent.right == node){
                parent = parent.parent;
            }
            return parent;
        }

    }

    public static Node getLeftMost(Node rNode){
        while (rNode.left != null){
            rNode = rNode.left;
        }
        return rNode;
    }

    public static void f(Node head){
        if(head == null){
            return;
        }
        f(head.left);
        System.out.printf(","+head.value);
        f(head.right);
    }



    public static Node getSuccessorNode1(Node head){
        if(head == null){
            return null;
        }
        if(head.right != null){
            return getLeftMost2(head.right);
        }else {
            Node parent = head.parent;
            while (parent != null || parent.right == head){
                head = parent;
                parent = head.parent;
            }
            return parent;
        }

    }

    public static Node getLeftMost2(Node node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.left.parent = new Node(1);
        head.right = new Node(3);
        head.right.parent = new Node(1);
        head.left.left = new Node(4);
        head.left.left.parent = new Node(2);
        head.left.right = new Node(5);
        head.left.right.parent = new Node(2);
        head.right.left = new Node(6);
        head.right.left.parent = new Node(3);
        head.right.right = new Node(7);
        head.right.right.parent = new Node(3);
        head.left.left.left = new Node(8);
        head.left.left.left.parent = new Node(4);
        head.left.left.right = new Node(9);
        head.left.left.right.parent = new Node(4);
        head.left.right.left = new Node(10);
        head.left.right.left.parent = new Node(5);
        head.left.right.right = new Node(11);
        head.left.right.right.parent = new Node(5);
        head.right.left.left = new Node(12);
        head.right.left.left.parent = new Node(6);
        head.right.left.right = new Node(13);
        head.right.left.right.parent = new Node(6);
        Node successorNode = getSuccessorNode(head.right.right);
        System.out.println("========"+successorNode.value);

        f(head);
    }
}
