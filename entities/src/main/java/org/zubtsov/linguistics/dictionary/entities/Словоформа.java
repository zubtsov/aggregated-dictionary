package org.zubtsov.linguistics.dictionary.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//todo: добавить транскрипцию (произношение)
//todo: добавить морфемный состав слова (приставка, корень, суффикс, основа, окончание, соединительная гласная)
@RequiredArgsConstructor
public class Словоформа {
    @Getter @Setter @NonNull
    private String wordForm;
    @Getter @Setter
    private int stressedSyllableIndex;
    @Getter @Setter
    private String[] syllables;

    @Override
    public String toString() {
        return wordForm; //todo: возможно стоит как - то представлять слоги/морфемный состав/ударения?
    }
}
