package org.zubtsov.linguistics.dictionary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;
import org.zubtsov.linguistics.dictionary.entities.utils.WordFormsMutableAttributesMapping;

public class Verb extends MutablePartOfSpeech<Verb.ImmutableAttributes, Verb.MutableAttributes> {
    public static final int MAXIMUM_NUMBER_OF_WORD_FORMS = Наклонение.values().length *
            Время.values().length *
            Род.values().length *
            Число.values().length *
            Лицо.values().length;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MutableAttributes { //аналог "грамматического разряда" по А.А. Зализняку
        public static final MutableAttributes initialFormAttributes = new MutableAttributes(
                Наклонение.ОТСУТСТВУЕТ,
                Время.ОТСУТСТВУЕТ,
                Род.ОТСУТСТВУЕТ,
                Число.ОТСУТСТВУЕТ,
                Лицо.ОТСУТСТВУЕТ); //начальная форма глагола не имеет изменяемых атрибутов

        private Наклонение наклонение = Наклонение.НЕИЗВЕСТНО;
        private Время время = Время.НЕИЗВЕСТНО;
        private Род род = Род.НЕИЗВЕСТНО;
        private Число число = Число.НЕИЗВЕСТНО;
        private Лицо лицо = Лицо.НЕИЗВЕСТНО;
    }

    @Data
    public static class ImmutableAttributes {
        private Вид вид = Вид.НЕИЗВЕСТНО;
        private Возвратность возвратность = Возвратность.НЕИЗВЕСТНО;
        private Переходность переходность = Переходность.НЕИЗВЕСТНО;
        private Спряжение спряжение = Спряжение.НЕИЗВЕСТНО;
    }

    public Verb(String initialForm) {
        this();
        wordFormsMutableAttributesMapping.setWordForm(new Словоформа(initialForm), MutableAttributes.initialFormAttributes);
    }

    public Verb() {
        this(new ImmutableAttributes(), new WordFormsMutableAttributesMapping<>(MAXIMUM_NUMBER_OF_WORD_FORMS, MAXIMUM_NUMBER_OF_WORD_FORMS));
    }

    public Verb(ImmutableAttributes wordImmutableAttributes, WordFormsMutableAttributesMapping<Словоформа, MutableAttributes> wordFormsMutableAttributesMapping) {
        this.wordImmutableAttributes = wordImmutableAttributes;
        this.wordFormsMutableAttributesMapping = wordFormsMutableAttributesMapping;
    }

    @Override
    public MutableAttributes getInitialFormAttributes() {
        return MutableAttributes.initialFormAttributes;
    }
}
