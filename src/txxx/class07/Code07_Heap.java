package txxx.class07;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/17 22:47
 */
public class Code07_Heap {
    public static class CrateHeap{
        private static int size;
        private static int limit;
        private static int[] heap;

        public CrateHeap(int limit){
            this.heap = new int[limit];
            this.limit = limit;
            this.size = 0;
        }

        private static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        //堆是一颗完全二叉树，不会有右节点没有左节点
        //堆分为大根堆和小根堆，节点i(index的缩写)的左孩子节点2i+1,右孩子节点为2i+2，父节点为（i-1）/2.
        //以创建大根堆为例子：用arr[index]和他的父节点arr[（index-1）/2]对比，谁大谁就交换上去，不大就不管,然后把原来的父节点继续循环
        public static void heapInsert(int[] arr,int index){
            while (arr[index] >arr[(index-1)/2]){
                swap(arr,index,(index-1)/2);
                index = (index-1)/2;
            }
        }
        public static void push(int value){
            if(size ==limit){
                throw new RuntimeException("heap is full");
            }
            heap[size] = value;//先把数字入堆
            heapInsert(heap,size++);//再对堆进行排序，选出最大的数字做根节点
        }


        //堆的弹出
        //将堆的根节点的数据弹出，但是不是真的把这个数字删除，是将根节点下标0和size(也就是最后一个数字交换)，然后size减一，就不然这个数继续参与下面的对比
        //然后再将交换了的新堆做对比，把新的最大数字放在根节点上
        //先对比左右节点的大小，再将大的和根节点大小对比，和根节点一样大就break掉，不一样就把大的放上去。
        public static void heapPop(int[] arr,int index,int size){
            int leftC = index * 2 +1;
            while (leftC < size){
                //把大的给一个临时节点temp
                int temp = leftC + 1 < size && arr[leftC+1] > arr[leftC] ? leftC+1 : leftC;//看看是不是有右孩子，再看是谁大，谁大谁就给temp，等下temp再和新插入的头节点比较
                //再用它和父节点index对比
                temp = arr[temp] > arr[index] ? temp : index;
                if (temp ==index){//当新插入的头节点数据是大于左右孩子节点的时候，就可以结束了。
                    break;
                }
                swap(arr,temp,index);
                index = temp;//向下循环
                leftC = index *2 +1;//向下循环
            }
        }
        public int pop(){
            int ans = heap[0];
            swap(heap,0,--size);
            heapPop(heap,0,size);
            return ans;
        }

        public static void heapPop1(int[] arr,int index,int size){
            int leftC = index * 2+1;
            while (leftC < size){
                int temp = leftC + 1 < size && arr[leftC +1 ]>arr[leftC] ? leftC +1 : leftC;
                temp = arr[index] > arr[temp] ? index : temp;
                if(temp == index){
                    break;
                }
                swap(arr,index,temp);
                index = temp;
                leftC = index *2 +1;
            }
        }
        public int pop1(){
            int ans = heap[0];
            swap(heap,0,--size);
            heapPop1(heap,0,size);
            return ans;
        }
    }
}
