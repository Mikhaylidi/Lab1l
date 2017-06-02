import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Created by Alexey on 02.06.2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        HashMap<String, Integer> hashMap = new HashMap<>();



        try(Scanner s=new Scanner(new File("1.txt"), "Cp1251")){
            while (s.hasNext()){
                String word = s.next().toLowerCase();
                if (!hashMap.containsKey(word)){
                    hashMap.put(word, 1);
                } else {
                    int i = 0;
                    i = hashMap.get(word)+1;
                    hashMap.put(word,i);
                }
            }
        }

        ArrayList<Map.Entry<String,Integer>> top = new ArrayList<>(hashMap.entrySet());
        top.sort((e1,e2)->-e1.getValue().compareTo(e2.getValue()));
        for (Map.Entry entry : top) {
            System.out.println(entry.getKey() + ": "
                    + entry.getValue());
        }

        DefaultCategoryDataset data = new DefaultCategoryDataset();
        String category = "Words";

        for (Map.Entry<String, Integer> entry : top) {
            data.addValue(entry.getValue(), category, entry.getKey());
            
        }

        JFreeChart chart = ChartFactory.createBarChart("Частота слов", "Слово", "частота", data);
        BufferedImage image = chart.createBufferedImage(600, 400);
        File file = new File("C:\\temp\\chart.png");
        ImageIO.write(image, "png", file);
        Desktop.getDesktop().open(file);

    }
}
