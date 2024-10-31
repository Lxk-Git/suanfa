package txxx.class03;

/**
 * @Author: LiuXingKun
 * @Date: 2022/12/6 21:37
 */
public class Code02_EvenTimesOddTimes {

    //获取一个出现奇数次的数，别的都是偶数次，怎么把这个出现奇数次的数找出来，
    //找出一个出现奇数次的数。
    public static void printOddNumber1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            //相同的异或得到0
            eor ^= arr[i];//0和所有数异或都是得到本身。
        }
        System.out.println(eor);
    }

    //获取两个奇数
    public static void printOddNumber2(int[] arr) {
        int eor = 0;
        //这一步就得到了两个奇数相与之后得到的数据。
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        //因为a和b是做异或运算，所以他们在二进制最右边一定有一位上，a有1，b没有1。求这个数转化成二进制最右边的1
        int rightOne = eor & (~eor + 1);//一个数与自己的相反数(~eor + 1)相与，就能得到二进制最右边的1；
        //拿到这个1后，整个数组就会分成在这个最右边上有1的数和在这个最右边上没有1的数。
        int a = 0;
        for (int j = 0; j < arr.length; j++) {
            if ((arr[j] & rightOne) != 0){//当这个数和最右边相与不是0就证明他是a或者b,这个时候把所有符合条件的数据全部异或出来就是想要的数据了。
                a ^= arr[j];
            }
        }

        int b = a ^ eor;

        System.out.println(a+","+b);






    }
    /**
     * 2024.1.30
     * 1.先所有的数据想异或，这就会得到两个奇数的异或和
     * 2.将异或和取反加一再和异或和想与，得到最右边为1的数据，
     * 3，如果数组中的数据和最右边的数据相与一定一个为0，一个不为0，先拿到那个不为0的那个a
     * 4.怎么拿，因为b那个数已经在数组中被剔除了，那么把所有数据再异或一下就能得到a
     * 5.最后a和b相互异或的数据已经得到了是c，a也拿到了，要想得到b,就再异或一下a,就得到了b,因为a^b=c,c^a=a^b^a=b(ps:自己异或自己可不就是0)
     */
    public static void printOddNumber3(int[] arr) {
        int c= 0;
        for (int i = 0; i < arr.length; i++) {
            c ^=arr[i];
        }


        int rightOne1 = c & (~c +1);
        int a = 0;
        for (int i = 0; i < arr.length; i++) {
            if((arr[i] & rightOne1) != 0){
                a ^= arr[i];
            }
        }
        int b1 = c ^ a;
        System.out.printf(a+"+"+ b1);
    }




    public static void main(String[] args) {
        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddNumber1(arr1);
        int[] arr2 = { 8,24,56,56 };
        printOddNumber3(arr2);
    }

}
