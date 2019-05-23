package org.zubtsov.linguistics.dictionary;

import org.zubtsov.linguistics.dictionary.entities.Слово;

import java.util.regex.Pattern;

public class FormsFactory {
    private static final Pattern отброситьОднуБукву = Pattern.compile(".*[а|А|е|Е|ё|Ё|и|И|о|О|у|У|ы|Ы|э|Э|ю|Ю|я|Я|й|Й|ь|Ь]$");
    private static final Pattern ничегоНеОтбрасывать = Pattern.compile(".*[б|Б|в|В|г|Г|д|Д|ж|Ж|з|З|к|К|л|Л|м|М|н|Н|п|П|р|Р|с|С|т|Т|ф|Ф|х|Х|ц|Ц|ч|Ч|ш|Ш|щ|Щ]$");
    private static final Pattern четыреКонечныхБуквы = Pattern.compile(".*ся$");

    public static String getОснова(Слово слово) {
        String основа = null;
        String начальнаяФорма = слово.getНачальнаяФорма().getWordForm();
        switch (слово.частьРечи) {
            case СУЩЕСТВИТЕЛЬНОЕ:
            case МЕСТОИМЕНИЕ:
                if (отброситьОднуБукву.matcher(начальнаяФорма).matches()) {
                    основа = начальнаяФорма.substring(0, начальнаяФорма.length() - 1);
                } else if (ничегоНеОтбрасывать.matcher(начальнаяФорма).matches()) {
                    основа = начальнаяФорма;
                }
                break;
            case ПРИЛАГАТЕЛЬНОЕ:
                if (четыреКонечныхБуквы.matcher(начальнаяФорма).matches()) {
                    основа = начальнаяФорма.substring(0, начальнаяФорма.length() - 4);
                } else {
                    основа = начальнаяФорма.substring(0, начальнаяФорма.length() - 2);
                }
                break;
        }
        return основа;
    }
}
