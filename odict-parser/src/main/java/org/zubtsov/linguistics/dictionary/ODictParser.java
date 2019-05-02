package org.zubtsov.linguistics.dictionary;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ODictParser {
    public static void main(String[] args) {
        String dictionaryFilePath = "D:\\Learning\\Books\\Linguistics\\Dictionaries\\odict.ru\\odict.csv";
//        new ODictParser().parse(dictionaryFilePath);
        new ODictParser().parseModifiers(dictionaryFilePath).forEach(s -> System.out.println(s));
    }

    public void parse(String dictionaryFilePath) {
        try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFilePath), "Windows-1251")))) {
            String[] line;
            int counter = 0;
            while ((line = reader.readNext()) != null && counter < 10) {
                System.out.println(line[0]);
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<String> parseModifiers(String dictionaryFilePath) {
        Set<String> modifiers = new HashSet<>();
        try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFilePath), "Windows-1251")))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                modifiers.add(line[1]);
            }
        } catch (IOException e) {
            log.error("Error occurred while reading file", e);
        }
        return modifiers;
    }
}
