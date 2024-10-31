package txxx.class09;


public class Trie {
    //前缀树用数组的实现
    //每个node节点里面就放pass和end,如果有就pass+1,不是尾部节点end就是0，是尾部节点就加1

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

    //插入
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
    //查询
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
