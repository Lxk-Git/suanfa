package dcgpt.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    public static void main(String args[]) {
        String str = "";
        String pattern = "设备规格1";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }

}