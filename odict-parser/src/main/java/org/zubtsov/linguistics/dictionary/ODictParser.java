package org.zubtsov.linguistics.dictionary;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class ODictParser {
    private ZaliznyakMapper mapper = new ZaliznyakMapper();

    public static void main(String[] args) {
        String dictionaryFilePath = "D:\\Learning\\Books\\Linguistics\\Dictionaries\\odict.ru\\zalizniak.txt";
        new ODictParser().parse(dictionaryFilePath);
    }

    public void parse(String dictionaryFilePath) {
        int lineNumber = 1;
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFilePath), "Windows-1251"))) {
            while ((line = reader.readLine()) != null && lineNumber++ < 1000) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                System.out.println(mapper.map(line));
            }
        } catch (IOException e) {
            log.error("Error occured while reading file" + dictionaryFilePath);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Out of bounds for line " + lineNumber, e);
        }
    }
}
