package dcgpt.class02;

import java.util.HashMap;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/14 14:25
 */
public class Code03_ReceiveAndPrintOrderLine2 {
    public static class Node {
        public String info;
        public Node next;

        public Node(String str) {
            info = str;
        }
    }

    //创建两个map,一个headMap存消息的开头，一个tailMap存消息的结尾，他们两个在一起的时候就能定位每一段的开头和结尾是多少，当连续起来的时候就打印出来，当然出现1的时候就打印了。
    //但是出现23，没有1就不会打印，如果前面123打印了，又出现了56789，但是还没有4就不会打印，会一直缓存着，等出现4的时候才会打印。
    //这个headMap为了存储的数据少，在最后打印的时候，都只会存开头的数字，别的存了也要删除掉，只留下最前面的一个数字；同理tailMap也是。比如56789在缓存里面headMap值存5，tailMap只存9
    //还有一个node，用它来连接5到9，就实现了headMap只存5，tailMap只存9，代表5,6,7,8,9.
    //用一个waitPoint来确定什么时候是连续的了，初始化为1，如果进来是1，那就直接打印，如果是2,3，那就等看到1再打印，并且每次打印会waitPoint累加，打印3个就累加到了4，下次要等遇到4才打印。
    //且打印完了，headMap和tailMap里面的数据也清空完毕
    public static class MessageBox {

        public HashMap<Integer, Node> headMap;
        public HashMap<Integer, Node> tailMap;
        public int waitPoint;

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String s) {
            if (num < 1) {
                return;
            }
            Node node = new Node(s);
            //只有一个数进来，那么当然头也是他，尾也是他
            //别的时候还是两个换成都加上
            headMap.put(num,node);
            tailMap.put(num,node);
            //后面再更新
            //先更新tailMap
            if(tailMap.containsKey(num -1)){//如果有比他小的
                //小的next当然是大的，现在怼上去
                tailMap.get(num -1).next = node;
                //那么在tailMap上把小的删除掉了
                tailMap.remove(num -1);
                //同时在headMap上也要把大的删除掉
                headMap.remove(num);
            }
            //再更新headMap
            if(headMap.containsKey(num + 1)){//有比他大的
                //小的next当然还是大的，再怼上去
                node.next = headMap.get(num + 1);
                //删除掉headMap上大的
                headMap.remove(num+1);
                //删除掉tailMap上小的
                tailMap.remove(num);
            }
            //打印
            //在num和waitPoint相等的时候打印
            if(num == waitPoint){
                print();
            }
        }

        private void print() {
            //一定是从小达到打印的
            //把waitPoint对应的node提取出来
            Node node = headMap.get(waitPoint);
            //所以先把headMap中的waitPoint删除了
            headMap.remove(waitPoint);
            while (node != null){
                System.out.print(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            //再把tailMap中的waitPoint删除掉
            //为啥是waitPoint-1？因为跳出while要多加一个1，看代码，看多了就懂了
            tailMap.remove(waitPoint-1);
            System.out.println();
        }


    }


    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2,"B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1,"A"); // 1 2 -> print, trigger is 1
        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9,"I"); // 9 -> print, trigger is 9
        box.receive(10,"J"); // 10 -> print, trigger is 10
        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11




    }


}
