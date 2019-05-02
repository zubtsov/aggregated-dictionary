package org.zubtsov.linguistics.dictionary;

import com.opencsv.CSVReader;

import java.io.*;

public class ODictParser {
    public static void main(String[] args) {
        String dictionaryFilePath = "D:\\Learning\\Books\\Linguistics\\Dictionaries\\odict.ru\\odict.csv";
        new ODictParser().parse(dictionaryFilePath);
    }

    public void parse(String dictionaryFilePath) {
        try(CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFilePath), "Windows-1251")))) {
            String[] line;
            int counter = 0;
            while ((line = reader.readNext()) != null && counter < 10) {
                System.out.println(line[0]); counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
