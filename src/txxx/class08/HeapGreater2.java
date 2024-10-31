package txxx.class08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/8 10:39
 */
public class HeapGreater2<T> {
    //创建一个正序表,也就是堆
    public ArrayList<T> heap;
    //创建一个逆序表hashMap,key是正序表的值，value是索引
    public HashMap<T, Integer> indexMap;
    //创建一个size记录堆里面有多少个值
    public int heapSize;
    //创建一个比较器，用来比较大小
    public Comparator<? super T> comp;

    //初始化
    public HeapGreater2(Comparator<? super T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    //先写push方法
    public void push(T obj) {
        heap.set(heapSize, obj);
        indexMap.put(obj, heapSize);
        //从尾到头排序堆，维持一个小根堆
        heapInsert(heapSize++);
    }

    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {//如果孩子节点小于父节点，将孩子节点和父节点交换，最后得到一个小根堆。
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }

    //在写pop方法
    public T pop() {
        T ans = heap.get(0);
        //将第一个和最后一个值交换
        swap(0, heapSize - 1);
        //indexMap删除掉ans
        indexMap.remove(ans);
        //heap删除掉ans
        heap.remove(--heapSize);
        //对heap和indexMap中的所有数据从0到尾开始排序。维持一个小根堆
        //因为一开始就是一个小根堆的结构，所以现在从0到尾进行heapiFy就能重新得到一个小根堆。
        heapFy(0);
        return ans;
    }

    private void heapFy(int i) {
        int leftC = 2 * i + 1;
        while (leftC < heapSize) {
            int temp = leftC + 1 < heapSize && comp.compare(heap.get(leftC + 1), heap.get(leftC)) < 0 ? leftC + 1 : leftC;//选左右孩子里面小的
            temp = comp.compare(heap.get(temp), heap.get(i)) < 0 ? temp : i;
            if(temp == i){
                return;
            }
            swap(temp,i);
            i = temp;
            leftC = 2 * i +1;
        }
    }

    //删除方法
    public void remove(T obj){
        //先查询最后一个值是多少
        T end = heap.get(heapSize-1);
        //拿到这个值的坐标
        int index = indexMap.get(obj);
        //heap删除最后一个值
        heap.remove(heapSize-1);
        //indexMap删除这个obj
        indexMap.remove(obj);
        //如果这个要删除的值不是最后一个值
        if(obj != end){
            //将开始放obj的位置放end
            heap.set(index,end);
            indexMap.put(end,index);
            //对新的堆进行排序，得到小根堆
            resign(index);
        }

    }

    private void resign(int index) {
        heapInsert(index);
        heapFy(index);
    }


}
