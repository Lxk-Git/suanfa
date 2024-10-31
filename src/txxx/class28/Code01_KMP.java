package txxx.class28;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/26 16:03
 */
public class Code01_KMP {
    //kmp算法
    //一个字符串s1，一个字符串s2，s2不能大于s1,把所有的s2字符串都设置成一个next数组（重点是理解next数组）
    //在next数组中，有个概念，就是如果i位置前面0到i-1位置上，从头部到尾部，和尾部到头部的字符串是一样的,（但是不是全体，如果是全体就没有意义了），
    //那么next[i]的值就是一样的字符串的长度
    //举个例子：s2 = acbacb;最后一个b他在next数组中，值就是2，因为从0开始ac等于从尾开始的ac。
    //再换一个例子：s2 = aabaab,换成next数组，是[-1,0,1,0,1,2]
    //
    //在s1和s2的对比中，他们都从下标为0开始，当s1的x和s2的y是一样的时候，x和y都加一，
    //当x和y不相等的时候，把s2对应的next数组中next[y]的值给到y，也就是s2的y向前移动了，再用s1中的x和s2中的y对比，以此推，
    // 当next[y]==-1的时候，就是说s1中的前x个字符都没有和s2一样的，那就让x++。
    //整体看当y越界的时候，说明有一样的，x-y就是他们相等时候的第一个字符位置，x越界的时候就说明找到最后都没有找到，失败了。
    //
    //再来看next数组的值怎么实现
    //首先next[0]=-1,next[1]=0是固定的
    //next[1]只能是0或者是1，当0和1位置相等就是1，不相等就是0
    //那就是i从2开始，要对比的就是i-1=2-1就是1位置要和cn=0位置对比，把cn设置为0
    //当i-1和cn相等，那就是next[i++] ==++cn，
    //不相等next[i++] ==0;
    //但是还有一种情况，那就是cn>0的时候，比如aabaabccb，
    //测试：第一个b,cn=0，i-1=1,str[1]==str[0],++cn=1,next[2]=1;
    //测试：第三个a,此时cn=1,i-1 = 2，str[2]!=str[1]，此时cn>0,cn=next[1]=0，然后next[3]=0,;
    //再测试：第四个a,此时cn=0,i-1=3,str[3]==str[0],++cn=1,next[4]==1;
    //再测试：第二个b,此时cn=1,i-1=4,str[4] ==str[1],++cn = 2,next[5]==2;
    //再测试：第一个c,此时cn=2,i-1=5,str[5]==str[2],++cn =3,next[6]=3;
    //在测试：第二个c,此时cn=3,i-1=6,str[6]!=str[3],此时cn>0,cn = next[3]=0，然后next[7]=0;
    //在测试：第三个b,此时cn=0,i-1=7,str[7]!=str[0],cn不变,next[8]=0;
    public static int kmp(String s1,String s2){
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0;
        int y = 0;
        int[] next = getNext(str2);
        while (x<str1.length && y<str2.length){
            if(str1[x]==str2[y]){
                x++;
                y++;
            }else if(next[y] ==-1){
                x++;
            }else {
                y=next[y];
            }
        }
        return y == str2.length ? x-y:-1;
    }

    private static int[] getNext(char[] str2) {
        if (str2.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1]=0;
        int i = 2;
        int cn =0;
        while (i<next.length){
            if(str2[i-1] == str2[cn]){
                next[i++] = ++cn;
            }else if(cn>0){
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }


}
