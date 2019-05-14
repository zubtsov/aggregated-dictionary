package org.zubtsov.linguistics.dictionary;

import org.zubtsov.linguistics.dictionary.entities.Adjective;
import org.zubtsov.linguistics.dictionary.entities.utils.DictionaryChunk;
import org.zubtsov.linguistics.dictionary.entities.Noun;
import org.zubtsov.linguistics.dictionary.entities.Verb;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Вид;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Одушевленность;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Род;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZaliznyakMapper {
    private static final Pattern regex = Pattern.compile("^([а-яА-Я]+)\\s+([0-9]+)\\s+(м|ж|с|мо|жо|со|мо\\-жо|св|нсв|св\\-нсв|п)\\s+[\\w\\W]*");

    public DictionaryChunk map(String line) {
        DictionaryChunk chunk = new DictionaryChunk();
        Matcher matcher = regex.matcher(line);
        if (matcher.matches()) {
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
                case "св":
                case "нсв":
                case "св-нсв":
                    Verb verb = mapLineToVerb(initialForm, modifier);
                    chunk.addVerb(verb);
                    break;
                case "п":
                    Adjective adjective = mapLineToAdjective(initialForm, modifier);
                    chunk.addAdjective(adjective);
                    break;
                default:
                    break;
            }
        }
        return chunk;
    }

    private Adjective mapLineToAdjective(String initialForm, String modifier) {
        Adjective adjective = new Adjective(initialForm);
        return adjective;
    }

    private Verb mapLineToVerb(String initialForm, String modifier) {
        Verb verb = new Verb(initialForm);

        Verb.ImmutableAttributes ia = new Verb.ImmutableAttributes();
        switch (modifier) {
            case "св":
                ia.setВид(Вид.СОВЕРШЕННЫЙ);
                break;
            case "нсв":
                ia.setВид(Вид.НЕСОВЕРШЕННЫЙ);
                break;
            case "св-нсв":
                ia.setВид(Вид.ДВУВИДОВОЙ);
                break;
        }
        verb.setWordImmutableAttributes(ia);

        return verb;
    }

    private Noun mapLineToNoun(String initialForm, String modifier) {
        Noun noun = new Noun(initialForm);

        switch (modifier) {
            case "м":
                noun.getWordImmutableAttributes().setРод(Род.МУЖСКОЙ);
                noun.getWordImmutableAttributes().setОдушевленность(Одушевленность.НЕОДУШЕВЛЕННОЕ);
                break;
            case "ж":
                noun.getWordImmutableAttributes().setРод(Род.ЖЕНСКИЙ);
                noun.getWordImmutableAttributes().setОдушевленность(Одушевленность.НЕОДУШЕВЛЕННОЕ);
                break;
            case "с":
                noun.getWordImmutableAttributes().setРод(Род.СРЕДНИЙ);
                noun.getWordImmutableAttributes().setОдушевленность(Одушевленность.НЕОДУШЕВЛЕННОЕ);
                break;
            case "мо":
                noun.getWordImmutableAttributes().setРод(Род.МУЖСКОЙ);
                noun.getWordImmutableAttributes().setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
            case "жо":
                noun.getWordImmutableAttributes().setРод(Род.ЖЕНСКИЙ);
                noun.getWordImmutableAttributes().setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
            case "со":
                noun.getWordImmutableAttributes().setРод(Род.СРЕДНИЙ);
                noun.getWordImmutableAttributes().setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
            case "мо-жо":
                noun.getWordImmutableAttributes().setРод(Род.ОБЩИЙ);
                noun.getWordImmutableAttributes().setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
        }

        return noun;
    }
}
