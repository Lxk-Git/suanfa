package txxx.class14;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiuXingKun
 * @Date: 2023/1/12 21:53
 */
public class Code04_MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> nexts;
        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    public static class Info {
        public int no;//x不来
        public int yes;//x来
        public Info(int n,int y){
            no = n;
            yes = y;
        }
    }
    //x来，最大值就是x.happy + x第一层子节点不来的最大值
    //x不来，最大值就是Math.max(x第一层子节点来或者不来的最大值)
    public static Info process(Employee x){
        if(x == null){
            return new Info(0,0);
        }
        int no = 0;
        int yes = x.happy;
        for(Employee next : x.nexts){
            Info nextInfo = process(next);//就做简单的理解，nextInfo就是x下面的子节点，比如a,b,c
            no +=Math.max(nextInfo.no,nextInfo.yes);//x不来的时候，最大值就是0+max(a不来，a来)+max(b不来，b来)+max(c不来，c来)
            yes +=nextInfo.no;//x来的时候，就是x.happy+max(a不来)+max(b不来)+max(c不来)
        }
        return new Info(no,yes);
    }
    public static int maxHappy(Employee head){
        if(head == null){
            return 0;
        }
        Info nextInfo = process(head);
        return Math.max(nextInfo.no,nextInfo.yes);
    }
}
