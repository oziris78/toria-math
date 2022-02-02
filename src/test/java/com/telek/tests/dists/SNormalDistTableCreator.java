package com.telek.tests.dists;

import com.telek.telekmath.core.functions.TRange;
import org.apache.commons.math3.distribution.NormalDistribution;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;


public class SNormalDistTableCreator {


    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\oguzh\\Desktop\\test.txt"; // yes i use absolute paths, cry about it...
        File file = new File(filePath);
        file.delete();
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(filePath);

        NormalDistribution nd = new NormalDistribution(0, 1);
        double value = 0.00d;
        while(value <= 40d){
            TRange range = new TRange(0, value);
            BigDecimal dec = new BigDecimal(nd.cumulativeProbability(range.left, range.right));
            String text = String.format("%f \t %s \n", value, dec.toString());
            fileWriter.write(text);
            value += 0.001d;
        }
        fileWriter.close();
    }



}
