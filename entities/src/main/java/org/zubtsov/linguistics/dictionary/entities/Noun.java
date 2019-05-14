package org.zubtsov.linguistics.dictionary.entities;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;
import org.zubtsov.linguistics.dictionary.entities.utils.WordFormsMutableAttributesMapping;

import java.util.Collection;
import java.util.Map;

public class Noun implements PartOfSpeech {
    //is there any exceptions, e.g. when multiple word forms fit one mutable attributes vector?
    public static final int MAXIMUM_NUMBER_OF_WORD_FORMS = Число.values().length * Падеж.values().length;

    @Getter
    @Setter
    private ImmutableAttributes wordImmutableAttributes;
    @Getter
    private WordFormsMutableAttributesMapping<Словоформа, MutableAttributes> wordFormsMutableAttributesMapping; //"Парадигма", по А.А. Зализняку

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

    //business methods
    public Collection<MutableAttributes> getMutableAttributes(String wordForm) {
        return wordFormsMutableAttributesMapping.getMutableAttributesByWordForm(new Словоформа(wordForm));
    }

    public Collection<Словоформа> getWordForm(MutableAttributes mutableAttributes) {
        return wordFormsMutableAttributesMapping.getWordFormByMutableAttributes(mutableAttributes);
    }

    public void setWordForm(MutableAttributes mutableAttributes, String wordForm) {
        wordFormsMutableAttributesMapping.setWordForm(new Словоформа(wordForm), mutableAttributes);
    }

    public void setWordForm(MutableAttributes mutableAttributes, Словоформа wordForm) {
        wordFormsMutableAttributesMapping.setWordForm(wordForm, mutableAttributes);
    }

    public void setWordForms(MutableAttributes mutableAttributes, Collection<Словоформа> wordForms) {
        wordFormsMutableAttributesMapping.setWordForms(wordForms, mutableAttributes);
    }

    @Override
    public Словоформа getInitialForm() {
        //todo: enforce presence of initial form by means of constructors?
        return wordFormsMutableAttributesMapping
                .getWordFormByMutableAttributes(MutableAttributes.initialFormAttributes)
                .iterator().next();
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
        for (Map.Entry<Словоформа, MutableAttributes> entry : wordFormsMutableAttributesMapping.getWordFormToMutableAttributesMappingEntries()) {
            sb.append(entry.getKey());
            sb.append("[");
            sb.append(entry.getValue());
            sb.append("]\n");
        }
        return sb.toString();
    }
}
