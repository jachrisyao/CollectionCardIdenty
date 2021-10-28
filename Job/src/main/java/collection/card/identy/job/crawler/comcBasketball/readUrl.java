package collection.card.identy.job.crawler.comcBasketball;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class readUrl {
    public static String txt2String(File file) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result = result + s;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        File file = new File("./url/comc_football.txt");
        ArrayList list = new ArrayList(Arrays.asList(txt2String(file).split(";")));
        System.out.println(list.get(0).toString());
    }

}
