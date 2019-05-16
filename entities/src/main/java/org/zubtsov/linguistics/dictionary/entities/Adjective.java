package org.zubtsov.linguistics.dictionary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;
import org.zubtsov.linguistics.dictionary.entities.utils.WordFormsMutableAttributesMapping;

public class Adjective extends MutablePartOfSpeech<Adjective.ImmutableAttributes, Adjective.MutableAttributes> {
    public static final int MAXIMUM_NUMBER_OF_WORD_FORMS = Род.values().length * Число.values().length * Падеж.values().length;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MutableAttributes { //аналог "грамматического разряда" по А.А. Зализняку
        public static final MutableAttributes initialFormAttributes = new MutableAttributes(Род.МУЖСКОЙ, Число.ЕДИНСТВЕННОЕ, Падеж.ИМЕНИТЕЛЬНЫЙ);

        private Род род = Род.НЕИЗВЕСТНО;
        private Число число = Число.НЕИЗВЕСТНО;
        private Падеж падеж = Падеж.НЕИЗВЕСТНО;
    }

    @Data
    public static class ImmutableAttributes {
        private РазрядПоЗначению разрядПоЗначению = РазрядПоЗначению.НЕИЗВЕСТНО;
        private Полнота полнота = Полнота.НЕИЗВЕСТНО; //mutable?
        private СтепеньСравнения степеньСравнения = СтепеньСравнения.НЕИЗВЕСТНО; //mutable?
    }

    public Adjective(String initialForm) {
        this();
        wordFormsMutableAttributesMapping.setWordForm(new Словоформа(initialForm), MutableAttributes.initialFormAttributes);
    }

    public Adjective() {
        this(new ImmutableAttributes(), new WordFormsMutableAttributesMapping<>(MAXIMUM_NUMBER_OF_WORD_FORMS, MAXIMUM_NUMBER_OF_WORD_FORMS));
    }

    public Adjective(ImmutableAttributes wordImmutableAttributes, WordFormsMutableAttributesMapping<Словоформа, MutableAttributes> wordFormsMutableAttributesMapping) {
        this.wordImmutableAttributes = wordImmutableAttributes;
        this.wordFormsMutableAttributesMapping = wordFormsMutableAttributesMapping;
    }

    @Override
    public MutableAttributes getInitialFormAttributes() {
        return MutableAttributes.initialFormAttributes;
    }
}
