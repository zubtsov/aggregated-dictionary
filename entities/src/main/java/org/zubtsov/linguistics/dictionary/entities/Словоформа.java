package org.zubtsov.linguistics.dictionary.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;

//todo: добавить транскрипцию (произношение)
//todo: добавить морфемный состав слова (приставка, корень, суффикс, основа, окончание, соединительная гласная)
@RequiredArgsConstructor
public class Словоформа {
    @Getter @Setter @NonNull
    public boolean начальнаяФорма = false;
    @Getter @Setter @NonNull
    public Вид вид = Вид.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Возвратность возвратность = Возвратность.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Время время = Время.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Лицо лицо = Лицо.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Наклонение наклонение = Наклонение.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Одушевленность одушевленность = Одушевленность.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Падеж падеж = Падеж.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Переходность переходность = Переходность.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Полнота полнота = Полнота.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public ПростотаЧислительного простотаЧислительного = ПростотаЧислительного.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public РазрядПоЗначению разрядПоЗначению = РазрядПоЗначению.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Род род = Род.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Склонение склонение = Склонение.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Собственность собственность = Собственность.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Спряжение спряжение = Спряжение.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public СтепеньСравнения степеньСравнения = СтепеньСравнения.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public ТипЧислительного типЧислительного = ТипЧислительного.НЕИЗВЕСТНО;
    @Getter @Setter @NonNull
    public Число число = Число.НЕИЗВЕСТНО;

    @Getter @Setter @NonNull
    private String wordForm;
    @Getter @Setter
    private int stressedSyllableIndex;
    @Getter @Setter
    private int stressedLetterIndex;
    @Getter @Setter
    private String[] syllables;



    @Override
    public String toString() {
        return wordForm; //todo: возможно стоит как - то представлять слоги/морфемный состав/ударения?
    }
}
