package txxx.class12;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/5 15:36
 */
public class Code02_SerializeAndReconstructTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    //可以使用先序遍历，后序遍历和层序遍历来序列化，但是使用中序边会有歧义
    //序列化中，如果子树为空就用null来补或者用#都可以


    //先序和后序的序列化差不多
    //创建一个队列来序列化
    public static Queue<String> preSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        pres(head,ans);
        return ans;
    }
    public static void pres(Node head,Queue<String> ans){
        if(head == null){
            return;
        }
        ans.add(String.valueOf(head.value));
        pres(head.left,ans);
        pres(head.right,ans);
    }

    //反序列化
    public static Node buildByPreQueue(Queue<String> prelist) {
        if(prelist == null || prelist.size() == 0){
            return null;
        }
        return preb(prelist);
    }
    public static Node preb(Queue<String> prelist) {
        String value = prelist.poll();
        if(value == null){
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.left = preb(prelist);
        head.right = preb(prelist);
        return head;
    }

    public static Node preb1(Queue<String> preList){
        String poll = preList.poll();
        if(poll == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(poll));
        head.left = preb1(preList);
        head.right = preb1(preList);
        return head;
    }

    //后序的反序列化需要加入一个stack,因为后序是左右中进栈，所以出栈的顺序要是中，右，左才能还原这颗树，buildByPosQueue是还原了这颗树，如果还要后序遍历，参考前面的两个栈完成后序遍历
    public static Node buildByPosQueue(Queue<String> posList){
        if(posList == null || posList.size() == 0){
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!posList.isEmpty()){
            stack.push(posList.poll());
        }
        return posr(stack);
    }
    public static Node posr(Stack<String> stack) {
        String value = stack.pop();
        if(value == null){
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.right = posr(stack);
        head.left = posr(stack);
        return head;
    }


    //层序遍历的序列化
    //需要准备一个队列，用来每次控制每一层的节点进入
    public static Queue<String> levelSerial(Node head){
        Queue<String> ans = new LinkedList<>();//一个队列用来放序列化的值
        if(head == null){
            ans.add(null);
        }else{
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<Node>();//一个队列用来放二叉树
            queue.add(head);//不由分说，先把头怼进去
            while (!queue.isEmpty()){
                head = queue.poll();
                if(head.left != null){
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                }else {
                    ans.add(null);
                }
                if(head.right != null){
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                }else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }
    //层序遍历的反序列化
    public static Node levelReSerial(Queue<String> levelList){
        if(levelList == null || levelList.size() == 0){
            return null;
        }
        //用一个方法来判断是不是有值，因为在序列化的时候，把没有值的数据用null方进去了
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if(head != null){
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        return head;
    }
    public static Node generateNode(String value){
        if(value == null){
            return null;
        }
        return new Node(Integer.valueOf(value));
    }

}
