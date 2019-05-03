package org.zubtsov.linguistics.dictionary;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ODictParser {
    private CsvLineObjectMapper mapper = new CsvLineObjectMapper();

    public static void main(String[] args) {
        String dictionaryFilePath = "D:\\Learning\\Books\\Linguistics\\Dictionaries\\odict.ru\\odict.csv";
        new ODictParser().parse(dictionaryFilePath);
//        new ODictParser().testStrangeValues(dictionaryFilePath);
//        new ODictParser().parseModifiers(dictionaryFilePath).forEach(s -> System.out.println(s));
    }

    public void parse(String dictionaryFilePath) {
        int lineNumber = 1;
        String[] line;
        try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFilePath), "Windows-1251")))) {
            while ((line = reader.readNext()) != null && lineNumber++ < 1000) {
                System.out.println(mapper.map(line));
            }
        } catch (IOException e) {
            log.error("Error occured while reading file" + dictionaryFilePath);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Out of bounds for line " + lineNumber, e);
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
            log.error("Error occurred while reading file" + dictionaryFilePath, e);
        }
        return modifiers;
    }

    public void testStrangeValues(String dictionaryFilePath) {
        int lineNumber = 0;
        try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryFilePath), "Windows-1251")))) {
            String[] line;
            Set<String> nounsModifiers = new HashSet<>(Arrays.asList("м", "ж", "с", "мо", "жо", "со"));

            while ((line = reader.readNext()) != null) {
                lineNumber++;
                if (nounsModifiers.contains(line[2])) {
                    if (!line[6].equals(line[7])) {
                        System.out.println(line[6] + "!=" + line[7]);
                    }
                    if (!line[13].equals(line[14])) {
                        System.out.println(line[13] + "!=" + line[14]);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error occurred while reading file" + dictionaryFilePath, e);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Out of bounds for line " + lineNumber, e);
        }
    }
}
