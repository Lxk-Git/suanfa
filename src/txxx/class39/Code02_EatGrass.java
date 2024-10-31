package txxx.class39;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/28 14:51
 */
public class Code02_EatGrass {
    //整个过程中有一个先手和一个后手
    //但是在递归子过程中，f(n)先手，在子过程f(n-1)中f(n)的后手变成了先手，先手就变成了后手

    public static String whoWin(int n) {
        if (n < 5) {
            return n == 0 || n == 2 ? "后手" : "先手";
        }

        int want = 1;
        while (want <= n) {
            //这里就是，如果是后手胜出，那么在大过程中就是先手赢了。
            if (whoWin(n - want).equals("后手")) {
                return "先手";
            }
            if (want <= n / 4) {
                want *= 4;
            } else {
                break;
            }
        }
        return "后手";

    }

    public static String whoWin2(int n) {
        if (n % 5 == 0 ||n % 5 == 2){
            return "后手";
        }else {
            return "先手";
        }
    }
    //后先后先先
    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + ":" + whoWin(i));
        }
    }
}
