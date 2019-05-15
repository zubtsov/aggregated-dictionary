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
        public static MutableAttributes initialFormAttributes = new MutableAttributes();

        private Род род = Род.МУЖСКОЙ;
        private Число число = Число.ЕДИНСТВЕННОЕ;
        private Падеж падеж = Падеж.ИМЕНИТЕЛЬНЫЙ;
    }

    @Data
    public static class ImmutableAttributes {
        private РазрядПоЗначению разрядПоЗначению;
        private Полнота полнота; //mutable?
        private СтепеньСравнения степеньСравнения; //mutable?
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
    public Словоформа getInitialForm() {
        return wordFormsMutableAttributesMapping
                .getWordFormByMutableAttributes(MutableAttributes.initialFormAttributes)
                .iterator().next();
    }
}
