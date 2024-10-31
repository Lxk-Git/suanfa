package dcgpt.class01;

import java.io.File;
import java.util.Stack;

/**
 * @Author: LiuXingKun
 * @Date: 2023/2/28 16:01
 */
public class Code02_CountFiles {

    //这个File这个包可以拿到隐藏文件
    //用栈或者队列都可以做
    //是文件的时候count++，是文件夹的时候就把他放入栈或者队列里面
    public static int countFiles(String paths){
        File file = new File(paths);
        if(!file.isDirectory() && !file.isFile()){
            return 0;
        }
        if(file.isFile()){
            return 1;
        }
        int count = 0;
        Stack<File> stack = new Stack<>();
        stack.push(file);
        while (!stack.isEmpty()){
            File pop = stack.pop();
            for(File next:pop.listFiles()){
                if(next.isFile()){
                    count++;
                }
                if(next.isDirectory()){
                    stack.push(next);
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        String path = "C:\\Users\\admin\\Desktop\\zhtp1\\aj";
        System.out.println(countFiles(path));
    }
}
