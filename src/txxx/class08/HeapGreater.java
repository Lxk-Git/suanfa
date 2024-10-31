package txxx.class08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/20 20:59
 */
//手写加强堆的重点
    //因为系统提供的堆删除根节点的速度很快，但是不能随意查询一个数据或者删除一个数据，加强堆的作用就是这个
    //逆序表就是用来做这个的

/*
 * T一定要是非基础类型，有基础类型需求包一层
 */
public class HeapGreater<T> {
    private ArrayList<T> heap;//用集合来表示堆（这里是正序）
    //正序表是位置对应一个值，逆序表就是值对应一个位置。
    private HashMap<T, Integer> indexMap;//这个就是逆序表,key是正序表对应的值，value是位置
    private int heapSize;
    private Comparator<? super T> comp;
    public HeapGreater(Comparator<? super T> c) {//初始化
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }
    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);//对新的堆从尾到0开始排序
    }
    public T pop() {
        T ans = heap.get(0);//就是peek功能
        swap(0, heapSize - 1);//让第一个和最后一个交换
        indexMap.remove(ans);//从逆序表中删除ans
        heap.remove(--heapSize);//从正序表中删除最后一个数
        heapify(0);//对新的堆从0到尾排序
        return ans;
    }
    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);//先取正序表最后一个值
        int index = indexMap.get(obj);//拿到要删除的值的index
        indexMap.remove(obj);//逆序表删除这个值
        heap.remove(--heapSize);//正序表删除掉最后一个值
        if (obj != replace) {//当要删除的值不是正序表的最后一个值
            heap.set(index, replace);//把正序表中最后一个值放到要删除的地方，set覆盖掉了原来的值。
            indexMap.put(replace, index);//逆序表也用最后一个值，重新放入一开始放obj的地方。
            resign(replace);
        }
    }
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));//对新的堆从被删除的值到头排序
        heapify(indexMap.get(obj));//对新的堆从被删除到值到尾开始排序
    }
    // 请返回堆上的所有元素
    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }
    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {//比较器对比节点和他的头节点值的大小
            swap(index, (index - 1) / 2);//把大的排在前面
            index = (index - 1) / 2;
        }
    }
    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }
    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);//这里达到了交换效果
        heap.set(j, o1);
        indexMap.put(o2, i);//同时逆序表也要加入数据,push就算成功了。
        indexMap.put(o1, j);
    }
}
