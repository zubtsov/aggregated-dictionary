package org.zubtsov.linguistics.dictionary;

import lombok.extern.slf4j.Slf4j;
import org.zubtsov.linguistics.dictionary.entities.DictionaryChunk;

@Slf4j
public class CsvLineObjectMapper {
    //todo: implement
    public DictionaryChunk map(String[] line) {
        String modifier = line[1];
        switch (modifier) {
            case "с":

                break;
            case "мо":

                break;
            case "св-нсв":

                break;
            case "числ.":

                break;
            case "жо":

                break;
            case "мо-жо":

                break;
            case "мс-п":

                break;
            case "числ.-п":

                break;
            case "св":

                break;
            case "межд.":

                break;
            case "предл.":

                break;
            case "ж":

                break;
            case "вводн.":

                break;
            case "союз":

                break;
            case "част.":

                break;
            case "сравн.":

                break;
            case "м":

                break;
            case "предик.":

                break;
            case "н":

                break;
            case "мн.":

                break;
            case "со":

                break;
            case "нсв":

                break;
            case "п":

                break;
            default:
                log.error("Unknown modifier: {}", modifier);
                break;
        }
        return null;
    }
}
