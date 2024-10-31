package txxx.class04;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/8 16:25
 */
public class Code04_RingArray {
    //用数组创建一个队列
    //两个重点
    //第一，用size来看这个数组有没有被填满
    //第二，用limit控制，指针是不是已经从0到i把数组填满了，然后0的位置被弹出了，再压入数字，指针又重新回到0。
    //具体操作
    //第一，要创建一个end指针，用来压入队列，一个begin指针，用来弹出队列
    //第二，需要创建一个size来看数组有没有填满 size==limit就是满了，或者是不是已经弹空了size==0就是弹空了
    //第三，需要创建一个limit，用它来与end和begin对比，比如都叫他们i,i<limit -1就是还没有压满或者是没有弹出完毕，就指针向下移动i+1,不满足就代表压满了或者弹出完毕了，就应该回到让指针回到0,从头再压入弹出。


    public class myQueue {
        private int[] arr;
        private int end;
        private int begin;
        private int size;
        private int limit;

        public myQueue(int limit) {
            arr = new int[limit];
            end = 0;
            begin = 0;
            size = 0;
            this.limit = limit;
        }

        public void posh(int value) {
            if (size == limit) {
                System.out.println("数组已经满了！");
            }
            size++;
            arr[end] = value;
            end = addIndex(end);
//            if (end < limit - 1) {
//                end++;
//            } else {
//                end = 0;
//            }
        }

        public int pop() {
            if (size == 0) {
                System.out.println("被掏空了！");
            }
            size--;
            int ans = arr[begin];
            begin = addIndex(begin);
//            if (begin < limit - 1) {
//                begin++;
//            } else {
//                begin = 0;
//            }
            return ans;
        }
        public int addIndex(int i){
            return i <limit -1 ? i+1:0;
        }



    }
}
