package dcgpt.class02;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/19 10:38
 */
public class Code02_Cola {
    /*
     * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
     * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
     * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
     * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
     * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
     * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
     * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
     * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
     * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
     */

    // 暴力尝试，为了验证正式方法而已
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }

    // 正式的方法
    // 要买的可乐数量，m
    // 100元有a张
    // 50元有b张
    // 10元有c张
    // 可乐单价x
    public static int putTimes(int m, int a, int b, int c, int x) {
        //              0    1   2
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        // 总共需要多少次投币
        int puts = 0;
        // 之前面值的钱还剩下多少总钱数
        int preQianRest = 0;
        // 之前面值的钱还剩下多少总张数
        int preQianZhang = 0;
        for (int i = 0; i < 3 && m != 0; i++) {
            // 要用之前剩下的钱、当前面值的钱，共同买第一瓶可乐
            // 之前的面值剩下多少钱，是preQianRest
            // 之前的面值剩下多少张，是preQianZhang
            // 之所以之前的面值会剩下来，一定是剩下的钱，一直攒不出一瓶可乐的单价
            // 当前的面值付出一些钱+之前剩下的钱，此时有可能凑出一瓶可乐来
            // 那么当前面值参与搞定第一瓶可乐，需要掏出多少张呢？就是curQianFirstBuyZhang
            // 向上取整的技巧：2099/700 = 2,向上取整就是(2099+（700-1）) / 700 = 3
            int curQianFirstBuyZhang = (x - preQianRest + qian[i] - 1) / qian[i];
            if (zhang[i] >= curQianFirstBuyZhang) { // 如果之前的钱和当前面值的钱，能凑出第一瓶可乐
                // 凑出来了一瓶可乐也可能存在找钱的情况，这里和投了多少次币没有关系
                giveRest(qian, zhang, i + 1, (preQianRest + qian[i] * curQianFirstBuyZhang) - x, 1);
                //需要投币多少次
                puts += curQianFirstBuyZhang + preQianZhang;
                zhang[i] -= curQianFirstBuyZhang;
                m--;
            } else { // 如果之前的钱和当前面值的钱，不能凑出第一瓶可乐
                //把当前的钱的总面额加到前面还剩下钱的总额
                preQianRest += qian[i] * zhang[i];
                //总张同理。
                preQianZhang += zhang[i];
                continue;
            }
            // 凑出第一瓶可乐之后，当前的面值有可能能继续买更多的可乐
            // 以下过程就是后续的可乐怎么用当前面值的钱来买
            // 用当前面值的钱，买一瓶可乐需要几张
            int curQianBuyOneColaZhang = (x + qian[i] - 1) / qian[i];
            // 用当前面值的钱，一共可以搞定几瓶可乐
            int curQianBuyColas = Math.min(zhang[i] / curQianBuyOneColaZhang, m);
            // 用当前面值的钱，每搞定一瓶可乐，收货机会吐出多少零钱
            int oneTimeRest = qian[i] * curQianBuyOneColaZhang - x;
            // 每次买一瓶可乐，吐出的找零总钱数是oneTimeRest
            // 一共买的可乐数是curQianBuyColas，所以把零钱去提升后面几种面值的硬币数，
            // 就是giveRest的含义
            giveRest(qian, zhang, i + 1, oneTimeRest, curQianBuyColas);
            // 当前面值去搞定可乐这件事，一共投了几次币
            puts += curQianBuyOneColaZhang * curQianBuyColas;
            // 还剩下多少瓶可乐需要去搞定，继续用后面的面值搞定去吧
            m -= curQianBuyColas;
            // 当前面值可能剩下若干张，要参与到后续买可乐的过程中去，
            // 所以要更新preQianRest和preQianZhang
            zhang[i] -= curQianBuyOneColaZhang * curQianBuyColas;
            preQianRest = qian[i] * zhang[i];
            preQianZhang = zhang[i];
        }
        return m == 0 ? puts : -1;
    }

    public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            //取整
            zhang[i] += (oneTimeRest / qian[i]) * times;
            //取余
            oneTimeRest %= qian[i];
        }
    }

    public static int putTimes1(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {a, b, c};
        //需要投币的次数
        int puts = 0;
        //之前剩下的钱的总金额
        int preQianRest = 0;
        //之前剩下的钱的张数
        int preQianZhang = 0;

        for (int i = 0; i < 3 && m != 0; i++) {
            //第一种钱用多少张可以买可乐
            int qianFirstZhang = (x - preQianRest + qian[i] - 1) / qian[i];
            //买第一瓶可乐
            if (zhang[i] > qianFirstZhang) {//当前的张数大于需要的张数目
                //满足就会产生零钱,i+1是因为零钱之后再后面产生，不会在当前的钱上产生，这里的times是1是因为只针对第一瓶可乐
                getRust(zhang, qian, i + 1, (preQianRest + qian[i] * qianFirstZhang - x), 1);
                puts += preQianZhang + qianFirstZhang;
                zhang[i] -= qianFirstZhang;
                m--;
            } else {//当前的张数不足
                //那就把这些钱全部加到剩下的钱总和里面去
                preQianRest += qian[i];
                //张也是
                preQianZhang += zhang[i];
                //暂时退出
                continue;
            }
            //第一瓶可乐解决了,已经将墙面剩下的都用掉了
            //现在解决后面的
            //当前的金额买一瓶可乐要多少张？
            int qianOutZhang = (x + qian[i] - 1) / qian[i];
            //当前金额一共能搞定多少瓶可乐
            int qianRestCols = Math.min(zhang[i] / qianOutZhang, m);
            //onTimesOut产生多少零钱,qianOutZhang是买一瓶可乐的钱
            int onTimesOut = qian[i] * qianOutZhang - x;
            //把零钱加到后面的zhang上去，这里的times变成了qianRestCols，是因为针对当前钱能买的所有可乐。
            getRust(zhang, qian, i + 1, onTimesOut, qianRestCols);
            //当前钱买可乐一共投了多少次币
            puts += qianOutZhang * qianRestCols;
            m -= qianRestCols;
            //还有的可乐就用后面的钱去买，现在剩下的钱放到pre里面去
            preQianRest = qian[i] * (zhang[i] - qianOutZhang * qianRestCols);
            preQianZhang = zhang[i] - qianOutZhang * qianRestCols;

        }
        return m == 0 ? puts : -1;
    }

    private static void getRust(int[] zhang, int[] qian, int i, int onTimesOut, int times) {
        for (; i < 3; i++) {
            zhang[i] += (onTimesOut / qian[i] * times);
            onTimesOut %= qian[i];
        }
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("如果错误会打印错误数据，否则就是正确");
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = right(m, a, b, c, x);
            int ans3 = putTimes1(m, a, b, c, x);
            if (ans1 != ans2 && ans2 != ans3) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                break;
            }
        }
        System.out.println("test end");
    }
}
