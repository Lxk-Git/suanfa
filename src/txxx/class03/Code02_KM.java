package txxx.class03;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/6 22:54
 */
public class Code02_KM {
//    public static int test(int[] arr, int k, int m) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int num : arr) {
//            if (map.containsKey(num)) {
//                map.put(num, map.get(num) + 1);
//            } else {
//                map.put(num, 1);
//            }
//        }
//        int ans = 0;
//        for (int num : map.keySet()) {
//            if (map.get(num) == k) {
//                ans = num;
//                break;
//            }
//        }
//        return ans;
//    }
//
//    public static HashMap<Integer, Integer> map = new HashMap<>();
//
//    // 请保证arr中，只有一种数出现了K次，其他数都出现了M次
//    public static int onlyKTimes(int[] arr, int k, int m) {
//        if (map.size() == 0) {
//            mapCreater(map);
//        }
//        int[] t = new int[32];
//        // t[0] 0位置的1出现了几个
//        // t[i] i位置的1出现了几个
//        for (int num : arr) {
//            while (num != 0) {
//                int rightOne = num & (-num);
//                t[map.get(rightOne)]++;
//                num ^= rightOne;
//            }
//        }
//        int ans = 0;
//        // 如果这个出现了K次的数，就是0
//        // 那么下面代码中的 : ans |= (1 << i);
//        // 就不会发生
//        // 那么ans就会一直维持0，最后返回0，也是对的！
//        for (int i = 0; i < 32; i++) {
//            if (t[i] % m != 0) {
//                ans |= (1 << i);
//            }
//        }
//        return ans;
//    }
//
//    public static void mapCreater(HashMap<Integer, Integer> map) {
//        int value = 1;
//        for (int i = 0; i < 32; i++) {
//            map.put(value, i);
//            value <<= 1;
//        }
//    }
//
//    // 更简洁的写法
//    public static int km(int[] arr, int k, int m) {
//        int[] help = new int[32];
//        for (int num : arr) {
//            for (int i = 0; i < 32; i++) {
//                help[i] += (num >> i) & 1;
//            }
//        }
//        int ans = 0;
//        for (int i = 0; i < 32; i++) {
//            help[i] %= m;
//            if (help[i] != 0) {
//                ans |= 1 << i;
//            }
//        }
//        return ans;
//    }







    //只能说只搞明白这一个，对数器的都不懂
    public static int km2(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) != 0) { //向右位移i位，再和1相与就能看出这个i位置是不是1
                    t[i]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((t[i] % m) != 0) {
                ans |= 1 << i ;//左移并且或上，就能恢复原来的数据
            }
        }
        return ans;
    }

    /**
     *
     * @param arr
     * @param k
     * @param m
     * @return
     * 2024.1.30
     * 1.用整数数组来存二进制数据，比如8，在arr[32]的第28个下标就加1，在来一个8就变成了2，在来一个就是3,进来一个24就变成,28的位置再加一，27的位置也加一，28的位置的数变成4,27的位置的数变成1.
     * 2.存好之后，就用下标对应的数处于M，如果不等于0，说明这个数在这里出现过，并且余数就是k,那么就把数组中，所有除以M取余的数反推二进制，拼接起来就是我们需要的出现了k次的数据
     */
    public static int km3(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int num : arr) {
            for (int i1 = 0; i1 < t.length; i1++) {
                if(((num >> i1) & 1) != 0){//记不住就自己去测试！真的是烦死！就是要想知道在那个地方加1，就需要看数据从左往右推，推了几次和1相与不是0了，说明就遇到了1
                    t[t.length-i1] ++;//t.length-i1是方便立即的写法，也可以写不一样的
                }
            }
        }
        //最外面的for循环弄完之后，就得了所有数据在t数组中的位置
        //这个时候要看t中的数据除以m是不是有余数，有就是我们需要的那个出现了K次的数据
        int ans = 0;
        for (int i : t) {
            if((i % m) != 0){
               ans |= 1; //不需要左推
            }
        }
        return ans;

    }




    // 为了测试
//    public static int[] randomArray(int maxKinds, int range, int k, int m) {
//        int ktimeNum = randomNumber(range);
//        // 真命天子出现的次数
//        int times = k;
//        // 2
//        int numKinds = (int) (Math.random() * maxKinds) + 2;
//        // k * 1 + (numKinds - 1) * m
//        int[] arr = new int[times + (numKinds - 1) * m];
//        int index = 0;
//        for (; index < times; index++) {
//            arr[index] = ktimeNum;
//        }
//        numKinds--;
//        HashSet<Integer> set = new HashSet<>();
//        set.add(ktimeNum);
//        while (numKinds != 0) {
//            int curNum = 0;
//            do {
//                curNum = randomNumber(range);
//            } while (set.contains(curNum));
//            set.add(curNum);
//            numKinds--;
//            for (int i = 0; i < m; i++) {
//                arr[index++] = curNum;
//            }
//        }
//        // arr 填好了  打乱这个arr
//        for (int i = 0; i < arr.length; i++) {
//            // i 位置的数，我想随机和j位置的数做交换
//            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
//            int tmp = arr[i];
//            arr[i] = arr[j];
//            arr[j] = tmp;
//        }
//        return arr;
//    }
//
//    // 为了测试
//    // [-range, +range]
//    public static int randomNumber(int range) {
//        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
//    }

    // 为了测试
//    public static void main(String[] args) {
//        int kinds = 5;
//        int range = 30;
//        int testTime = 100000;
//        int max = 9;
//        System.out.println("测试开始");
//        for (int i = 0; i < testTime; i++) {
//            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
//            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
//            int k = Math.min(a, b);
//            int m = Math.max(a, b);
//            // k < m
//            if (k == m) {
//                m++;
//            }
//            int[] arr = randomArray(kinds, range, k, m);
//            int ans1 = test(arr, k, m);
//            int ans2 = onlyKTimes(arr, k, m);
//            int ans3 = km2(arr, k, m);
//            if (ans1 != ans2 || ans1 != ans3) {
//                System.out.println(ans1);
//                System.out.println(ans3);
//                System.out.println("出错了！");
//            }
//        }
//        System.out.println("测试结束");
//    }
}
