package org.zubtsov.linguistics.dictionary;

import lombok.extern.slf4j.Slf4j;
import org.zubtsov.linguistics.dictionary.entities.DictionaryChunk;
import org.zubtsov.linguistics.dictionary.entities.Noun;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Одушевленность;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Падеж;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Род;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Число;

@Slf4j
public class CsvLineObjectMapper {
    public DictionaryChunk map(String[] line) {
        DictionaryChunk chunk = new DictionaryChunk();
        String modifier = line[1];

        switch (modifier) {
            //СУЩЕСТВИТЕЛЬНОЕ
            case "м":
            case "ж":
            case "с":
            case "мо":
            case "жо":
            case "со":
                Noun noun = mapLineToNoun(line, modifier);
                chunk.addNoun(noun);
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
        return chunk;
    }

    private Noun mapLineToNoun(String[] line, String modifier) {
        Noun noun = new Noun();

        Noun.ImmutableAttributes ia = new Noun.ImmutableAttributes();
        switch (modifier) {
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
                ia.setРод(Род.ОБЩИЙ);
                ia.setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
        }
        noun.setWordImmutableAttributes(ia);

        int indexInLine = 2;
        switch (line.length) {
            case 9:
                for (Падеж падеж : Падеж.values()) {
                    Noun.MutableAttributes ma = new Noun.MutableAttributes();
                    ma.setЧисло(Число.ЕДИНСТВЕННОЕ);
                    ma.setПадеж(падеж);

                    if (падеж == Падеж.ИМЕНИТЕЛЬНЫЙ) {
                        noun.setWordForm(ma, line[0]);
                    } else {
                        noun.setWordForm(ma, line[indexInLine++]);
                    }
                }
                indexInLine++; //workaround
                break;
            case 15:
                for (Число число : Число.values()) {
                    for (Падеж падеж : Падеж.values()) {
                        Noun.MutableAttributes ma = new Noun.MutableAttributes();
                        ma.setЧисло(число);
                        ma.setПадеж(падеж);

                        if (число == Число.ЕДИНСТВЕННОЕ && падеж == Падеж.ИМЕНИТЕЛЬНЫЙ) {
                            noun.setWordForm(ma, line[0]);
                        } else {
                            noun.setWordForm(ma, line[indexInLine]);
                            indexInLine++;
                        }
                    }
                    indexInLine++; //workaround
                }
                break;
        }
        return noun;
    }
}
