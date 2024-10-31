package txxx.class09;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/28 22:20
 */
public class Code09_Trie01 {
    //前缀树用数组的实现
    //每个node节点里面就放pass和end,如果有就pass+1,不是尾部节点end就是0，是尾部节点就加1

    /**
     * 前缀树是一种用于快速检索的多叉树结构，利用字符串的公共前缀来降低查询时间，核心思想是空间换时间，经常被搜索引擎用于文本词频统计。
     * 优点：最大限度地减少无谓的字符串比较，查询效率高；
     * 缺点：内存消耗较大；
     *
     * 从根节点构建树，每个节点定义两个int变量，pass和end。
     * pass：通过该节点的次数
     * end：以该节点做结尾的次数。
     */
    class Node {
        public int pass;
        public int end;
        public Node[] nexts;
        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];
        }
    }

    private Node root;
    public void Trie() {
        root = new Node();
    }

    //创建前置树
    public void insertChar(String word){
        if(word == null){
            return;
        }
        char[] chars = word.toCharArray();
        Node node = root;
        node.pass++;
        int path = 0;
        for(int i = 0;i<chars.length;i++){//从左往右
            path = chars[i] - 'a';//用ascii码的十进制值来确定路线
            if(node.nexts[path] == null){
                node.nexts[path] = new Node();
            }
            node = node.nexts[path];//必须要下移一个，才能继续累加
            node.pass++;
        }
        node.end++;
    }
    //查询又多少个数字在前缀树中
    public int countWord(String word){
        if(word == null){
            return 0;
        }
        char[] chars = word.toCharArray();
        Node node = root;
        int path = 0;
        for(int i = 0;i<chars.length;i++){
            path = chars[i] - 'a';
            if (node.nexts[path] == null){
                return 0;
            }
            node = node.nexts[path];
        }
        return node.end;
    }
    //返回前缀有多少个
    public int countPre(String word){
        if(word == null){
            return 0;
        }
        char[] chars = word.toCharArray();
        Node node = root;
        int path = 0;
        for(int i = 0;i<chars.length;i++){
            path = chars[i] - 'a';
            if (node.nexts[path] == null){
                return 0;
            }
            node = node.nexts[path];
        }
        return node.pass;
    }
    //删除前缀树中的元素,注意内存溢出的问题
    public void deleteChar(String word){
        if(countWord(word) != 0){
            char[] chars = word.toCharArray();
            Node node = root;
            node.pass--;
            int path = 0;
            for(int i = 0;i<chars.length;i++){//从左往右
                path = chars[i] - 'a';//用ascii码的十进制值来确定路线
                if(--node.nexts[path].pass == 0){//证明是没有别的分支了，要设置成null,每次进来都先减一
                    node.nexts[path] = null;
                    return;
                }
                node = node.nexts[path];//必须要下移一个，才能继续
            }
            node.end--;
        }

    }


}
