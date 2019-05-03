package org.zubtsov.linguistics.dictionary.entities;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;

import java.util.Collection;
import java.util.Map;

public class Noun implements PartOfSpeech {
    //is there any exceptions, e.g. when multiple word forms fit one mutable attributes vector?
    public static final int MAXIMUM_NUMBER_OF_WORD_FORMS = Число.values().length * Падеж.values().length;

    public Noun() {
        this(new ImmutableAttributes(), new WordFormsMutableAttributesMapping<>(MAXIMUM_NUMBER_OF_WORD_FORMS, MAXIMUM_NUMBER_OF_WORD_FORMS));
    }

    public Noun(String initialForm) {
        this();
        wordFormsMutableAttributesMapping.setWordForm(initialForm, MutableAttributes.initialFormAttributes);
    }

    public Noun(String едИмП, String едРодП, String едДатП, String едВинП, String едТвП, String едПредП,
                String мнИмП, String мнРодП, String мнДатП, String мнВинП, String мнТвП, String мнПредП) {
        this(new String[]{едИмП, едРодП, едДатП, едВинП, едТвП, едПредП, мнИмП, мнРодП, мнДатП, мнВинП, мнТвП, мнПредП});
    }

    public Noun(String... forms) {
        int formIndex = 0;
        for (Число число : Число.values()) {
            for (Падеж падеж : Падеж.values()) {
                wordFormsMutableAttributesMapping.setWordForm(forms[formIndex++], new MutableAttributes(число, падеж));
            }
        }
    }

    public Noun(ImmutableAttributes wordImmutableAttributes, WordFormsMutableAttributesMapping<String, MutableAttributes> wordFormsMutableAttributesMapping) {
        this.wordImmutableAttributes = wordImmutableAttributes;
        this.wordFormsMutableAttributesMapping = wordFormsMutableAttributesMapping;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MutableAttributes { //аналог "грамматического разряда" по А.А. Зализняку
        public static MutableAttributes initialFormAttributes = new MutableAttributes();

        private Число число = Число.ЕДИНСТВЕННОЕ;
        private Падеж падеж = Падеж.ИМЕНИТЕЛЬНЫЙ;
    }

    @Data
    public static class ImmutableAttributes {
        private Род род;
        private Склонение склонение;
        private Одушевленность одушевленность;
        private Собственность собственность;
    }

    @Getter
    @Setter
    private ImmutableAttributes wordImmutableAttributes;
    @Getter
    private WordFormsMutableAttributesMapping<String, MutableAttributes> wordFormsMutableAttributesMapping; //"Парадигма", по А.А. Зализняку

    public Collection<MutableAttributes> getMutableAttributes(String wordForm) {
        return wordFormsMutableAttributesMapping.getMutableAttributesByWordForm(wordForm);
    }

    public String getWordForm(MutableAttributes mutableAttributes) {
        return wordFormsMutableAttributesMapping.getWordFormByMutableAttributes(mutableAttributes).iterator().next();
    }

    public void setWordForm(MutableAttributes mutableAttributes, String wordForm) {
        wordFormsMutableAttributesMapping.setWordForm(wordForm, mutableAttributes);
    }

    @Override
    public String getInitialForm() {
        //todo: enforce presence of initial form by means of constructors?
        return wordFormsMutableAttributesMapping.getWordFormByMutableAttributes(MutableAttributes.initialFormAttributes).iterator().next();
    }

    @Override
    public boolean equals(Object obj) {
        //todo: enforce presence of initial form by means of constructors?
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Noun rhs = (Noun) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(getInitialForm(), rhs.getInitialForm()) //[STRONG ASSUMPTION] this check is sufficient
                .isEquals();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Initial form: ");
        sb.append(getInitialForm());
        sb.append("\nImmutable attributes:\n");
        sb.append(wordImmutableAttributes);
        sb.append("\nWord forms:\n");
        for (Map.Entry<String, MutableAttributes> entry : wordFormsMutableAttributesMapping.getWordFormToMutableAttributesMappingEntries()) {
            sb.append(entry.getKey());
            sb.append("[");
            sb.append(entry.getValue());
            sb.append("]\n");
        }
        return sb.toString();
    }
}
