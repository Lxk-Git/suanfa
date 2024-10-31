package dcgpt.class03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code08_DistanceKNodes {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }



    //解题思路：
    //二叉树，要求目标节点，到距离为K的所有节点的集合
    //这个就需要用宽度优先遍历，使用队列，每一层所有的节点遍历完毕了，才会去遍历下一层的节点。
    //还要创建一个hashMap用来存节点的parents，所以这个hashMap要从头节点开始递归存
    //在后面找符合提交的节点的时候，不能让结果出现重复的答案，还要创建一个hashSet，用它判断这个节点是不是走过了，走过了就不要加进去了。
    //重新理一下：
    //一个二叉树，一个目标节点，找到他的左子树，右子树，父节点，然后左子树，右子树，父节点又分别找到自己对应的左子树，右子树，父节点。这是宽度优先遍历，把他们加入到队列和set中。
    //用curLevel记录层级，curLevel==k的节点全部返回
    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        HashMap<Node, Node> parents = new HashMap<>();
        parents.put(root, null);
        createParentMap(root, parents);
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int curLevel = 0;
        List<Node> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                if (curLevel == K) {
                    ans.add(cur);
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    visited.add(cur.left);
                    queue.offer(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    visited.add(cur.right);
                    queue.offer(cur.right);
                }
                if (parents.get(cur) != null && !visited.contains(parents.get(cur))) {
                    visited.add(parents.get(cur));
                    queue.offer(parents.get(cur));
                }
            }
            curLevel++;
            if (curLevel > K) {
                break;
            }
        }
        return ans;
    }

    public static void createParentMap(Node cur, HashMap<Node, Node> parents) {
        if (cur == null) {
            return;
        }
        if (cur.left != null) {
            parents.put(cur.left, cur);
            createParentMap(cur.left, parents);
        }
        if (cur.right != null) {
            parents.put(cur.right, cur);
            createParentMap(cur.right, parents);
        }
    }


    public static List<Node> distanceKNodes1(Node root,Node target,int k){
        HashMap<Node, Node> parents = new HashMap<>();
        parents.put(root,null);
        createParentMap1(root,parents);
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> hashSet = new HashSet<>();
        queue.add(target);
        hashSet.add(target);
        List<Node> ans = new ArrayList<>();
        int level = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- >0){
                Node cur = queue.poll();
                if(level == k){
                    ans.add(cur);
                }else{
                    if(cur.left != null && !hashSet.contains(cur.left)){
                        queue.add(cur.left);
                        hashSet.add(cur.left);
                    }
                    if(cur.right != null && !hashSet.contains(cur.right)){
                        queue.add(cur.right);
                        hashSet.add(cur.right);
                    }
                    if(parents.get(cur) != null && !hashSet.contains(parents.get(cur))){
                        queue.add(parents.get(cur));
                        hashSet.add(parents.get(cur));
                    }
                }
            }
            //注意这个地方，要 while (size-- >0)走完了，就是宽度走完了，才能level++
            level++;
            if(level >k){//这个非常需要注意，只要把和target相差K距离的所有节点找到了就直接break掉，不然会循环完所有的节点。
                break;
            }
        }
        return ans;
    }

    private static void createParentMap1(Node node, HashMap<Node, Node> parents) {
        if(node == null){
            return;
        }
        if(node.left !=null){
            parents.put(node.left,node);
            createParentMap1(node.left,parents);
        }
        if(node.right !=null){
            parents.put(node.right,node);
            createParentMap1(node.right,parents);
        }
    }

    public static void main(String[] args) {
/*        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }*/


        String s = "#1";
        String s1 = "1#null";
        String[] split = s1.split("#");
        int length = split.length;
        System.out.println(length);
        for(String s2:split){
            System.out.println(s2);
        }


    }

}