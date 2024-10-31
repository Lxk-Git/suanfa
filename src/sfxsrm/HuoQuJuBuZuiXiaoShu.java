package sfxsrm;

/**
 * @Author: LiuXingKun
 * @Date: 2022/11/20 12:49
 */
public class HuoQuJuBuZuiXiaoShu {
    //第一步，先弄一个随机数组，相邻不相等
    public static int[] randomAArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);//设置一个0位置的数字，是为了下面的do while循环不越界。
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    //第二步,开始怎么查询到一个局部最小的值，要他返回出来！ 错的！
    public static int oneMinValue(int[] arr) {
        //1.如果没有数据返回-1；
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        //2.如果只有一个就返回那个数据
        if (N == 1) {
            return 0;
        }
        //3.先看左右两边的左边的情况
        if (arr[0] < arr[1]) {
            return 0;
        }
        //4.同理再看右边
        if (arr[N - 2] > arr[N - 1]) {
            return N - 1;
        }

        //5.上面的情况都不是，那就按照二分法来查询最小的值
        //先找一个重点mid
        int L = 0;
        int R = N - 1;
        int mid = (L + R) / 2;//这里可能会出现问题，以后应该会换成位运算的，这样才不会出现越界
        //6.如果mid上的值小于左边，也小于右边，此时他就是局部最小的值
        if (arr[mid - 1] > arr[mid] && arr[mid + 1] > mid) {
            return mid;
        } else { //如果不是上面的情况，就去左边找,左边还是用二分法的查询，右边舍弃掉，因为我们只要一个局部最小的值！
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else {
                L = mid + 1;
            } //这个if是单独针对左边下面的循环的！这里要注意！
        }
        return arr[L] > arr[R] ? L : R;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 用于测试
    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

/*    public static void main(String[] args) {
        int maxLen = 5;
        int maxValue = 500;
        int testCount = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testCount; i++) {
            int[] arr = randomAArray(maxLen, maxValue);
            int ans = oneMinValue(arr);
            if (!check(arr, ans)) {
                printArray(arr);
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束");
    }*/

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public static int getLessIndex2(int[] arr) {
        if (arr == null || arr.length < 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return L;
    }

    public static int getLessIndex3(int[] arr) {
        if (arr.length == 0 || arr.length < 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 2] > arr[arr.length - 1]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while (L <=R){
            mid = L + ((R-L) >> 1);
            if(arr[mid] > arr[mid -1]){
                R = mid -1;
            }else if(arr[mid] > arr[mid+1]){
                L = mid + 1;
            }else {
                return mid;
            }
        }
        return L;
    }


    public static void main(String[] args) {
        int arr[] = {88, 23, 18, 19, 23, 43, 44};
//        int i = oneMinValue(arr);
        int lessIndex = getLessIndex(arr);
        System.out.print(lessIndex);
    }


}
