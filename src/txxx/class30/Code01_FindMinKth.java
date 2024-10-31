package txxx.class30;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/2 17:02
 */
public class Code01_FindMinKth {

    public static class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    // 利用大根堆，时间复杂度O(N*logK)
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        return maxHeap.peek();
    }


    //第一个简单的办法，用大根堆
    //先把arr中0到k-1的数字填写到大根堆中
    //如果第k大的数字刚好进去了，那么大根堆的堆顶那就是k,直接peek就是答案
    //如果第k大的数字没有进去，那就把从k到arr.length-1的数据压入堆，并且对比新进去的数字是不是小于当前堆的堆顶，如果是那就把堆里面的数字弹出，再压入新的数字，
    //不管是中间把第K大的数字压入了，还是最后吧K压入了，最后堆的堆顶都是第K大的数字
    public static class MaxHeapComparator1 implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            //比较器，正数o2就在前面，这样就是大根堆
            return o2 - o1;
        }
    }

    // 利用大根堆，时间复杂度O(N*logK)
    public static int minKth11(int[] arr, int k) {
        PriorityQueue<Integer> heapQueue = new PriorityQueue<>(new MaxHeapComparator1());
        for (int i = 0; i < k; i++) {
            heapQueue.add(arr[i]);
        }
        for (int j = k; j < arr.length; j++) {
            if (arr[j] < heapQueue.peek()) {
                heapQueue.poll();
                heapQueue.add(arr[j]);
            }
        }
        return heapQueue.peek();
    }

    // 改写快排，时间复杂度O(N)
    // k >= 1
    public static int minKth2(int[] array, int k) {
        //要复制一下干嘛呢？
        int[] arr = copyArray(array);
        return process2(array, 0, arr.length - 1, k - 1);
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i != ans.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // arr 第k小的数
    // process2(arr, 0, N-1, k-1)
    // arr[L..R]  范围上，如果排序的话(不是真的去排序)，找位于index的数
    // index [L..R]
    public static int process2(int[] arr, int L, int R, int index) {
        if (L == R) { // L = =R ==INDEX
            return arr[L];
        }
        // 不止一个数  L +  [0, R -L]
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process2(arr, L, range[0] - 1, index);
        } else {
            return process2(arr, range[1] + 1, R, index);
        }
    }

    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }
    //改写快排
    //快排的荷兰国旗问题，在数组中随机选择一个数，可以把小于他的数字放在左边，等于放在中间，大于放在右边。
    //利用这个等于区域，如果要找的第K大的数字在这个等于区域中，那么就直接返回等于区域的数字，如果k小于等于区域，那就去递归左边区域，大于就去右边区域
    //在写的过程中注意数字和下标的区别

    public static int minKth22(int[] arr, int k) {
        //第k大就是第k-1个数
        return process22(arr, 0, arr.length - 1, k - 1);
    }

    private static int process22(int[] arr, int L, int R, int index) {
        //先写base case
        if (L == R) {//只有一个
            return arr[L];
        }
        //随机选择一个point
        int point = arr[L + (int) (Math.random() * (R - L + 1))];
        //荷兰国旗，返回一个等于区域
        int[] range = partition1(arr, L, R, point);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process22(arr, L, range[0] - 1, index);
        } else {
            return process22(arr, range[1] + 1, R, index);
        }
    }
    //快排
    private static int[] partition1(int[] arr, int l, int r, int point) {
        int less = l - 1;
        //这里用R+1位置做大于位置，是因为point是从arr随机抽取的，所以arr中的所有位置都要参与比较
        int more = r + 1;
        int index = l;
        while (index < more) {
            if (arr[index] < point) {//小于区域
                swap(arr, index++, ++less);
            } else if (arr[index] > point) {//大于区域
                swap(arr, index, --more);//index不++是因为交换了之后还要去比较
            } else {//等于区域
                index++;
            }
        }

        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    // 利用bfprt算法，时间复杂度O(N)
    public static int minKth3(int[] array, int k) {
        int[] arr = copyArray(array);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    // arr[L..R]  如果排序的话，位于index位置的数，是什么，返回
    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        // L...R  每五个数一组
        // 每一个小组内部排好序
        // 小组的中位数组成新数组
        // 这个新数组的中位数返回
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    // arr[L...R]  五个数一组
    // 每个小组内部排序
    // 每个小组中位数领出来，组成marr
    // marr中的中位数，返回
    public static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {//把排序好的中位数组成新的mArr
            int teamFirst = L + team * 5;
            // L ... L + 4
            // L +5 ... L +9
            // L +10....L+14
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        // marr中，找到中位数
        // marr(0, marr.len - 1,  mArr.length / 2 )
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);//找到mArr的中位数。
    }

    public static int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        //返回排序好的中位数
        return arr[(L + R) / 2];
    }

    //插入排序
    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    //bfprt算法，重点就是怎么优越的选出point点，不是随机，而是等概率
    public static int minKth33(int[] array, int k) {
        int[] arr = copyArray(array);
        return bfprt1(arr, 0, arr.length - 1, k - 1);
    }

    // arr[L..R]  如果排序的话，位于index位置的数，是什么，返回
    public static int bfprt1(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        // L...R  每五个数一组
        // 每一个小组内部排好序
        // 小组的中位数组成新数组
        // 这个新数组的中位数返回
        int pivot = medianOfMedians1(arr, L, R);
        int[] range = partition1(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt1(arr, L, range[0] - 1, index);
        } else {
            return bfprt1(arr, range[1] + 1, R, index);
        }
    }

    // arr[L...R]  五个数一组
    // 每个小组内部排序
    // 每个小组中位数领出来，组成marr
    // marr中的中位数，返回
    private static int medianOfMedians1(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {//把排序好的中位数组成新的mArr
            //每个team的第一个数的下标
            int teamFirst = L + team * 5;
            //给每个小组做插入排序，并把他们的中位数拿出来
            mArr[team] = getMedian1(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        return bfprt1(mArr, 0, mArr.length - 1, mArr.length / 2);//找到mArr的中位数。
    }

    private static int getMedian1(int[] arr, int L, int R) {
        insertionSort2(arr, L, R);
        return arr[(R + L) / 2];
    }

    //只有最多5个数，就用插入排序
    private static void insertionSort2(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            for (int j = i - 1; j >= l ; j--) {
                if( arr[j] > arr[j + 1]){
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth11(arr, k);
            int ans2 = minKth22(arr, k);
            int ans3 = minKth33(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
