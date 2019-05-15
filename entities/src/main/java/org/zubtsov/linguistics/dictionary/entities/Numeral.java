package org.zubtsov.linguistics.dictionary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Numeral extends MutablePartOfSpeech<Numeral.ImmutableAttributes, Numeral.MutableAttributes> {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MutableAttributes { //аналог "грамматического разряда" по А.А. Зализняку
        public static Numeral.MutableAttributes initialFormAttributes = new Numeral.MutableAttributes();
    }

    @Data
    public static class ImmutableAttributes {
    }

    @Override
    public Словоформа getInitialForm() {
        return wordFormsMutableAttributesMapping
                .getWordFormByMutableAttributes(Numeral.MutableAttributes.initialFormAttributes)
                .iterator().next();
    }
}
