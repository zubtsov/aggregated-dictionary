package org.zubtsov.linguistics.dictionary;

import lombok.extern.slf4j.Slf4j;
import org.zubtsov.linguistics.dictionary.entities.DictionaryChunk;
import org.zubtsov.linguistics.dictionary.entities.Noun;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Одушевленность;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Род;

@Slf4j
public class CsvLineObjectMapper {
    //todo: implement
    public DictionaryChunk map(String[] line) {
        DictionaryChunk chunk = new DictionaryChunk();
        String modifier = line[1];

        Noun.ImmutableAttributes ia = new Noun.ImmutableAttributes();

        switch (modifier) {
            //СУЩЕСТВИТЕЛЬНОЕ
            //неодушевленные
            case "м":
                ia.setРод(Род.МУЖСКОЙ);
                ia.setОдушевленность(Одушевленность.НЕОДУШЕВЛЕННОЕ);
                break;
            case "ж":
                ia.setРод(Род.ЖЕНСКИЙ);
                ia.setОдушевленность(Одушевленность.НЕОДУШЕВЛЕННОЕ);
                break;
            case "с":
                ia.setРод(Род.СРЕДНИЙ);
                ia.setОдушевленность(Одушевленность.НЕОДУШЕВЛЕННОЕ);
                break;
            //одушевленные
            case "мо":
                ia.setРод(Род.МУЖСКОЙ);
                ia.setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
            case "жо":
                ia.setРод(Род.ЖЕНСКИЙ);
                ia.setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
            case "со":
                ia.setРод(Род.СРЕДНИЙ);
                ia.setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
            case "мо-жо":
                //общий род
                break;
            case "мн.":
                //существительное множественное число
                break;

            //ГЛАГОЛ
            //вид
            case "св":

                break;
            case "нсв":

                break;
            case "св-нсв":

                break;

            //ПРИЛАГАТЕЛЬНОЕ
            case "п":
                //прилагательное
                break;
            case "сравн.":

                break;

            //ДРУГИЕ
            case "числ.":
                //числительное
                break;
            case "мс-п":
                //местоимение, что значит п?
                break;
            case "числ.-п":

                break;
            case "межд.":

                break;
            case "предл.":

                break;
            case "вводн.":

                break;
            case "союз":

                break;
            case "част.":

                break;
            case "предик.":

                break;
            case "н":
                //наречие
                break;
            default:
                log.error("Unknown modifier: {}", modifier);
                break;
        }
        return null;
    }

    private Noun.MutableAttributes mapNounAttributes(String[] line) {
        Noun.MutableAttributes ma = new Noun.MutableAttributes();
        //todo: implement
        return ma;
    }
}
