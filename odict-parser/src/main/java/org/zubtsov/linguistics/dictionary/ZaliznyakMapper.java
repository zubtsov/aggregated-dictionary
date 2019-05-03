package org.zubtsov.linguistics.dictionary;

import org.zubtsov.linguistics.dictionary.entities.DictionaryChunk;
import org.zubtsov.linguistics.dictionary.entities.Noun;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Одушевленность;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Род;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZaliznyakMapper {
    private static final Pattern regex = Pattern.compile("^([а-яА-Я]+)\\s+([0-9]+)\\s+(м|ж|с|мо|жо|со|мо\\-жо)\\s+[\\w\\W]*");
    private static final Pattern regex2 = Pattern.compile("^([\\u0430-\\u044F\\u0410-\\u042F]+)\\s+([0-9]+)\\s+(\\u043C|\\u0436|\\u0441|\\u043C\\u043E|\\u0436\\u043E|\\u0441\\u043E|\\u043C\\u043E\\-\\u0436\\u043E)\\s+[\\w\\W]*");

    public DictionaryChunk map(String line) {
        DictionaryChunk chunk = new DictionaryChunk();
        Matcher matcher = regex.matcher(line);
        if(matcher.matches()) {
            String initialForm = matcher.group(1);
            String modifier = matcher.group(3);

            switch (modifier) {
                case "м":
                case "ж":
                case "с":
                case "мо":
                case "жо":
                case "со":
                case "мо-жо":
                    Noun noun = mapLineToNoun(initialForm, modifier);
                    chunk.addNoun(noun);
                    break;
            }
        }
        return chunk;
    }

    private Noun mapLineToNoun(String initialForm, String modifier) {
        Noun noun = new Noun(initialForm);

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

        return noun;
    }
}
