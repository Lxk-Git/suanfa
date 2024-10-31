package txxx.class12;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/7 23:47
 */
public class Code05_TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                //这里的curNodeLevel加一不会影响到第42行的curNodeLevel，不会影响到46行的curNodeLevel，是因为他们都是局部加一
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            //这个地方，是当前层curLevelNodes就加一
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                //当当前层成为了上一层，也就是当前层统计结束了到了下一层了，就可以把这个max对比拿到了
                //然后curLevel++，就可以为下一层的curLevelNodes累加了
                //下一层默认是1，不用害怕最后没有的一层赋值为了1，因为我们有max对比，如果不是空，最小就是1，比1大的时候，max会把1覆盖掉。
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }
    //用一个map来存每一个值在哪一层，key是树的值，value是层。
    //每一层按顺序加入到队列，用一个队列来存每个数的值，先进先出相加出每一层有多少个值。
    //用一个参数做当前在的层级，一个参数做map中拿到的数的值对应的层级，如果两个是相等的，就nodes+1，这就相当于累加当前层级，
    //如果已经不相等了，那就说明在新的层级了，当前对应层级就就要加1。再在循环中对max和nodes对比大小，最后结束的时候，再来一次对比大小就能得到答案了
    public static int maxWidthUseMap4(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> map = new HashMap<>();
        Integer curLevel = 1;
        Integer curNodes = 0;
        queue.add(head);
        map.put(head,1);
        int max = 0;
        while (!queue.isEmpty()){
            head = queue.poll();
            Integer nodeLevel = map.get(head);
            if(head.left!=null){
                map.put(head.left,nodeLevel +1);
                queue.add(head.left);
            }
            if(head.right!=null){
                map.put(head.right,nodeLevel +1);
                queue.add(head.right);
            }
            if(curLevel == nodeLevel){
                curNodes ++;
            }else {
                max = Math.max(max,curNodes);
                curLevel++;
                curNodes = 1;
            }
        }
        max = Math.max(max,curNodes);
        return max;
    }



    //整体来说就是创建一个队列，每进去一层所有的数字，就统计这一层的宽度，然后再统计下一层
    public static int maxWidthUseMap3(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int curLevel = 1;//用这个变量来看现在在统计那一层的宽度
        int curLevelNodes = 0;//宽度是多少
        int max = 0 ;//拿到最大值
        HashMap<Node,Integer> map = new HashMap<>();
        map.put(head,1);
        while (!queue.isEmpty()){
            head = queue.poll();
            int curLevelOfNode = map.get(head);
            //从这里到下面就做到了，每次都把每一层所有的数字放入队列中。每次弹出又会把下一层的数字放入队列中，并记录了层级
            if(head.left != null){
                map.put(head.left,curLevelOfNode+1);
                queue.add(head.left);
            }
            if(head.right != null){
                map.put(head.right,curLevelOfNode+1);
                queue.add(head.right);
            }
            if(curLevelOfNode == curLevel){//每次弹完了一层才会进入else,才会curLevel++
                curLevelNodes++;
            }else {
                max = Math.max(max,curLevelNodes);
                curLevel ++;
                curLevelNodes =1;
            }
        }
        max = Math.max(max,curLevelNodes);
        return max;

    }


    //为啥一下没有看懂这个代码，还是coding不得行。
    public static int maxWidthUseMap2(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        //map的value存的值是当前在第几层
        HashMap<Node, Integer> levelMap = new HashMap<>();
        //头节点肯定是第一层，那我就直接把头节点放进Map中。
        levelMap.put(head, 1);
        //我再把head放进队列中
        queue.add(head);
        //用一个变量来存是第几层
        int curLevel = 1;
        //用一个变量来存每一层有多少节点
        int curLevelNodes = 0;
        //用max来对比取出最大的层
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curLevelNode = levelMap.get(cur);//取弹出节点是在第几层
            //层序遍历，有左入左，有右入右，统计一下在第几层，并把左右怼队列中去
            if (cur.left != null) {
                levelMap.put(cur.left, curLevelNode + 1);//这里入的节点在第几层当然要在map中存一下,这个curLevelNode是局部变化
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curLevelNode + 1);
                queue.add(cur.right);
            }
            if (curLevelNode == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    //现在这个是不带容器的办法
    //这个重点是弄一个当前层的curEnd节点，和一个下一层的nextEnd节点。
    //一个队列来进出节点，一个nodes变量来累加
    //当节点出队列的时候就nodes++,当出来的cur节点等于curEnd节点，cur就是当前层最后一个节点了，nodes就把当前层的节点累加完了。
    //当节点出队列的时候nodes++，并把当前节点的左右节点放进队列，并更新nextEnd节点
    //当当前层结束的时候，nodes累加完毕，nextEnd变成curEnd,nodes为0，开始计算下一层的nodes
    public static int maxWidthNoMap(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Node curEnd = head;
        Node nextEnd = null;
        int nodes = 0;
        int max = 0;
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.left!=null){
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if(cur.right!=null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            nodes++;
            if(cur == curEnd){
                max = Math.max(max,nodes);
                curEnd = nextEnd;
                nodes = 0;
            }
        }
        max = Math.max(max,nodes);
        return max;
    }
    public static int maxWidthNoMap3(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Node curEnd = head;
        Node nextEnd = null;
        Integer nodes = 0;
        queue.add(head);
        int max = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.left != null){
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if(cur.right != null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            nodes++;
            if(cur == curEnd){
                max = Math.max(max,nodes);
                curEnd = nextEnd;
                nodes = 0;
            }
        }
        max = Math.max(max,nodes);
        return max;
    }


    public static int maxWidthNoMap2(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curNode = head;//初始化当然还是头咯
        Node nextNode = null;
        int nodes = 0;
        int max = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.left != null){
                queue.add(cur.left);
                nextNode = cur.left;
            }
            if(cur.right != null){
                queue.add(cur.right);
                nextNode = cur.right;//最后的节点已经放在下一层的最后一个节点上了
            }
            nodes++;
            if(cur == curNode){
                max = Math.max(max,nodes);
                curNode = nextNode;//将下一层的最后一个节点赋值给curNode
                nodes = 0;
            }
        }
        max = Math.max(max,nodes);
        return max;
    }




    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        int i = maxWidthUseMap(head);
//        int i = maxWidthNoMap(head);
        System.out.println("========"+i);
    }
}
