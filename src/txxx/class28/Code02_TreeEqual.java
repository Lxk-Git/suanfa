package txxx.class28;

import java.util.ArrayList;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/27 17:24
 */
public class Code02_TreeEqual {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    //先把两棵树先序遍历，然后就是kmp算法了
    public static boolean treeEqual(TreeNode big, TreeNode small) {
        if (big == null || small == null) {
            return false;
        }
        ArrayList<String> b = preSerial(big);
        ArrayList<String> s = preSerial(small);
        String[] str = new String[b.size()];
        //将List集合转换成String数组
        for (int i = 0; i < str.length; i++) {
            str[i] = b.get(i);
        }
        String[] str2 = new String[s.size()];
        //将List集合转换成String数组
        for (int i = 0; i < str2.length; i++) {
            str2[i] = s.get(i);
        }
        return kmp(str, str2) != -1;

    }

    private static int kmp(String[] str, String[] str2) {
        if (str == null || str2 == null) {
            return -1;
        }
        int x = 0;
        int y = 0;
        int[] next = getNext(str2);
        while (x < str.length && y < str2.length) {
            if (str[x] == str[y]) {
                x++;
                y++;
            }else if(next[y] == -1){
                x++;
            }else {
                y = next[y];
            }
        }
        return y == str2.length ? x-y:-1;
    }

    private static int[] getNext(String[] str2) {
        if(str2.length == 1){
            return new int[]{-1};
        }
        int[] res = new int[str2.length];
        res[0] = -1;
        res[1] = 0;
        int i = 2;
        int cn = 0;
        while (i<res.length){
            if(str2[i-1] == str2[cn]){
                res[i++] = ++cn;
            }else if(cn >0){
                cn = res[cn];
            }else {
                res[i++] = 0;
            }
        }
        return res;
    }

    private static ArrayList<String> preSerial(TreeNode head) {
        ArrayList<String> res = new ArrayList<>();
        pre(head, res);
        return res;
    }

    private static void pre(TreeNode head, ArrayList<String> res) {
        if (head == null) {
            return;
        } else {
            res.add(String.valueOf(head.val));
            pre(head.left, res);
            pre(head.right, res);
        }
    }
}
