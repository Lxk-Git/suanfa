package txxx.class07;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/17 22:47
 */
public class Code07_HeapSort {
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

        //堆的排序还是可以用到这个弹出，就是将根节点的大数和最后一个节点交换，然后继续保持大根堆形式
        //这个也可以做堆的插入
        //将堆的根节点的数据弹出，但是不是真的把这个数字删除，是将根节点下标0和size(也就是最后一个数字交换)，然后size减一，就不然这个数继续参与下面的对比
        //然后再将交换了的新堆做对比，把新的最大数字放在根节点上
        //先对比左右节点的大小，再将大的和根节点大小对比，和根节点一样大就break掉，不一样就把大的放上去。
        public static void heapiFy(int[] arr,int index,int size){
            int leftC = index * 2 +1;
            while (leftC < size){
                //把大的给一个临时节点temp
                int temp = leftC + 1 < size && arr[leftC+1] >arr[leftC] ? leftC+1 : leftC;//看看是不是有右孩子，再看是谁大，谁大谁就给temp，等下temp再和新插入的头节点比较
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
        public static void heapPop2(int[] arr,int index,int size){
            int leftC = index * 2 + 1;
            while (leftC < size){
                int temp = leftC + 1 < size && arr[leftC + 1] > arr[leftC] ? leftC + 1 : leftC;
                temp = arr[temp] > arr[index] ? temp : index;
                if(temp == index){
                    break;
                }
                swap(arr,temp,index);
                index = temp;
                leftC = index * 2 + 1;
            }
        }


//        public int pop(){
//            int ans = heap[0];
//            swap(heap,0,--size);
//            heapPop(heap,0,size);
//            return ans;
//        }


        public static void heapSort(int[] arr){
            if (arr == null || arr.length < 2) {
                return;
            }
            // O(N*logN)方法一创建 //最后得到一个大根堆
//		for (int i = 0; i < arr.length; i++) { // O(N)
//			heapInsert(arr, i); // O(logN)
//		}
            // O(N) 最后还是得到一个大根堆，
            //从arr.length -1到0进行heapiFy的作用是，可以重复对数组中的数据排序，直到得到大根堆是O（N）=（n-1 + n-2 + n-3 +...+1），从0到arr.length -1就不行
            //并且没一个子树都是大根堆，且右孩子大于左孩子
            //这里很重要，整棵二叉树从上往下排序，arr从后往前，可以递归把整棵树以及每个子树都变成大根堆。
            for (int i =arr.length -1;i>=0;i--){
                heapiFy(arr,i,arr.length);
            }
            int size = arr.length;
            swap(arr,0,--size);//最后一个数和根节点交换
            //下面维持大根堆结构来排序
            while (size>0){
                //因为上面已经将所有子树包括整棵树都变成了大根堆，所有就可以从0到size进行维持大根堆
                heapiFy(arr,0,size);//边界问题看leftC + 1 < size，他是不会到最后的size的
                swap(arr,0,--size);
            }
        }


        public static void heapSort2(int[] arr){
            if(arr.length<0||arr ==null){
                return;
            }
            for(int i = arr.length-1;i>=0;i--){
                heapPop2(arr,i,arr.length);
            }
            int size = arr.length;
            swap(arr,0,--size);
            while (size>0){
                heapPop2(arr,0,size);
                swap(arr,0,--size);
            }
        }
        public static void main(String[] args) {
            int[] arr = {0, 2, 1, 3, 6, 5, 4};
            heapSort(arr);
        }
    }
}
