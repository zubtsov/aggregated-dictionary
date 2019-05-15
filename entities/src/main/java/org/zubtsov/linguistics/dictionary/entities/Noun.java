package org.zubtsov.linguistics.dictionary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;
import org.zubtsov.linguistics.dictionary.entities.utils.WordFormsMutableAttributesMapping;

public class Noun extends MutablePartOfSpeech<Noun.ImmutableAttributes, Noun.MutableAttributes> {
    //is there any exceptions, e.g. when multiple word forms fit one mutable attributes vector?
    private static final int MAXIMUM_NUMBER_OF_WORD_FORMS = Число.values().length * Падеж.values().length;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MutableAttributes { //аналог "грамматического разряда" по А.А. Зализняку
        public static MutableAttributes initialFormAttributes = new MutableAttributes(Число.ЕДИНСТВЕННОЕ, Падеж.ИМЕНИТЕЛЬНЫЙ);

        private Число число = Число.НЕИЗВЕСТНО;
        private Падеж падеж = Падеж.НЕИЗВЕСТНО;
    }

    @Data
    public static class ImmutableAttributes {
        private Род род = Род.НЕИЗВЕСТНО;
        private Склонение склонение = Склонение.НЕИЗВЕСТНО;
        private Одушевленность одушевленность = Одушевленность.НЕИЗВЕСТНО;
        private Собственность собственность = Собственность.НЕИЗВЕСТНО;
    }

    public Noun() {
        this(new ImmutableAttributes(), new WordFormsMutableAttributesMapping<>(MAXIMUM_NUMBER_OF_WORD_FORMS, MAXIMUM_NUMBER_OF_WORD_FORMS));
    }

    public Noun(ImmutableAttributes wordImmutableAttributes, WordFormsMutableAttributesMapping<Словоформа, MutableAttributes> wordFormsMutableAttributesMapping) {
        this.wordImmutableAttributes = wordImmutableAttributes;
        this.wordFormsMutableAttributesMapping = wordFormsMutableAttributesMapping;
    }

    public Noun(String initialForm) {
        this();
        wordFormsMutableAttributesMapping.setWordForm(new Словоформа(initialForm), MutableAttributes.initialFormAttributes);
    }

    public Noun(String едИмП, String едРодП, String едДатП, String едВинП, String едТвП, String едПредП,
                String мнИмП, String мнРодП, String мнДатП, String мнВинП, String мнТвП, String мнПредП) {
        this(new String[]{едИмП, едРодП, едДатП, едВинП, едТвП, едПредП, мнИмП, мнРодП, мнДатП, мнВинП, мнТвП, мнПредП});
    }

    public Noun(String... forms) {
        int formIndex = 0; //todo: find out if order is preserved
        for (Число число : Число.realValues()) {
            for (Падеж падеж : Падеж.realValues()) {
                wordFormsMutableAttributesMapping.setWordForm(new Словоформа(forms[formIndex++]), new MutableAttributes(число, падеж));
            }
        }
    }

    @Override
    public Словоформа getInitialForm() {
        //todo: enforce presence of initial form by means of constructors?
        return wordFormsMutableAttributesMapping
                .getWordFormByMutableAttributes(MutableAttributes.initialFormAttributes)
                .iterator().next();
    }
}
