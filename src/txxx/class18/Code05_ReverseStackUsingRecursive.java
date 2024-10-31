package txxx.class18;

import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/10 14:27
 */
public class Code05_ReverseStackUsingRecursive {

    //给你一个栈，请你逆序这个栈，
    //不能申请额外的数据结构，
    //只能使用递归函数



    //把1,2，3压入栈里面，第一个递归函数写把先进去的栈低元素取出来，上面的元素位置不要变再压下去。第二个递归函数先得到1,2,3，然后再按照3,2,1压入栈中，就达到了栈中元素逆序的效果
    public static int f1(Stack<Integer> stack){
        int res = stack.pop();//每次弹出一个元素
        if(stack.isEmpty()){//只有stack空了，才是栈底元素
            return res;//结束递归
        }else{//否则就不是栈底元素，还要把它按照原来的顺序压回去
            //把他们按照3,2取到，再按照2,3压进去
            //要注意返回最后递归结果的时候得到res=1,所以下面的last就是等于1了，stack.push(res)往回压都是递归改跑完的过程
            int last = f1(stack);
            stack.push(res);
            return last;
        }
    }

    public static void reverse2(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int i = f1(stack);
        reverse2(stack);//依次得到1,2,3
        stack.push(i);//再归去按3,2,1的顺序压入
    }


    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
/*        test.push(4);
        test.push(5);*/
        reverse2(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
