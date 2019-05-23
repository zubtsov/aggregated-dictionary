package org.zubtsov.linguistics.dictionary;

import org.zubtsov.linguistics.dictionary.entities.characteristics.*;
import org.zubtsov.linguistics.dictionary.entities.Слово;
import org.zubtsov.linguistics.dictionary.entities.Словоформа;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZaliznyakMapper {
    private static final Pattern regex = Pattern.compile("^([а-яА-Я]+)\\s+([0-9]+)\\s+(м|ж|с|мо|жо|со|мо\\-жо|св|нсв|св\\-нсв|п)\\s+[\\w\\W]*");

    public Слово map(String line) {
        Слово слово = new Слово();
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
                    слово = mapLineToNoun(initialForm, modifier); //субстантивное склонение
                    break;
                case "св":
                case "нсв":
                case "св-нсв":
                    слово = mapLineToVerb(initialForm, modifier);
                    break;
                case "п":
                    слово = mapLineToAdjective(initialForm, modifier); //адъективное склонение
                    break;
                default:
                    break;
            }
        }
        return слово;
    }

    private Слово mapLineToAdjective(String initialForm, String modifier) {
        Слово слово = new Слово();
        слово.setЧастьРечи(ЧастьРечи.ПРИЛАГАТЕЛЬНОЕ);
        Словоформа начальнаяФорма = new Словоформа(initialForm);
        начальнаяФорма.начальнаяФорма = true;
        начальнаяФорма.setРод(Род.МУЖСКОЙ);
        начальнаяФорма.setЧисло(Число.ЕДИНСТВЕННОЕ);
        начальнаяФорма.setПадеж(Падеж.ИМЕНИТЕЛЬНЫЙ);
        слово.словоформы.add(начальнаяФорма);
        return слово;
    }

    private Слово mapLineToVerb(String initialForm, String modifier) {
        Слово слово = new Слово();
        слово.setЧастьРечи(ЧастьРечи.ГЛАГОЛ);
        Словоформа начальнаяФорма = new Словоформа(initialForm);
        начальнаяФорма.начальнаяФорма = true;

        switch (modifier) {
            case "св":
                начальнаяФорма.setВид(Вид.СОВЕРШЕННЫЙ);
                break;
            case "нсв":
                начальнаяФорма.setВид(Вид.НЕСОВЕРШЕННЫЙ);
                break;
            case "св-нсв":
                начальнаяФорма.setВид(Вид.ДВУВИДОВОЙ);
                break;
        }
        слово.словоформы.add(начальнаяФорма);
        return слово;
    }

    private Слово mapLineToNoun(String initialForm, String modifier) {
        Слово слово = new Слово();
        слово.setЧастьРечи(ЧастьРечи.СУЩЕСТВИТЕЛЬНОЕ);
        Словоформа начальнаяФорма = new Словоформа(initialForm);
        начальнаяФорма.начальнаяФорма = true;

        switch (modifier) {
            case "м":
                начальнаяФорма.setРод(Род.МУЖСКОЙ);
                начальнаяФорма.setОдушевленность(Одушевленность.НЕОДУШЕВЛЕННОЕ);
                break;
            case "ж":
                начальнаяФорма.setРод(Род.ЖЕНСКИЙ);
                начальнаяФорма.setОдушевленность(Одушевленность.НЕОДУШЕВЛЕННОЕ);
                break;
            case "с":
                начальнаяФорма.setРод(Род.СРЕДНИЙ);
                начальнаяФорма.setОдушевленность(Одушевленность.НЕОДУШЕВЛЕННОЕ);
                break;
            case "мо":
                начальнаяФорма.setРод(Род.МУЖСКОЙ);
                начальнаяФорма.setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
            case "жо":
                начальнаяФорма.setРод(Род.ЖЕНСКИЙ);
                начальнаяФорма.setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
            case "со":
                начальнаяФорма.setРод(Род.СРЕДНИЙ);
                начальнаяФорма.setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
            case "мо-жо":
                начальнаяФорма.setРод(Род.ОБЩИЙ);
                начальнаяФорма.setОдушевленность(Одушевленность.ОДУШЕВЛЕННОЕ);
                break;
        }
        слово.словоформы.add(начальнаяФорма);
        return слово;
    }
}
