package org.zubtsov.linguistics.dictionary.entities;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;
import org.zubtsov.linguistics.dictionary.entities.utils.WordFormsMutableAttributesMapping;

import java.util.Collection;
import java.util.Map;

public class Adjective implements PartOfSpeech {
    public static final int MAXIMUM_NUMBER_OF_WORD_FORMS = Род.values().length * Число.values().length * Падеж.values().length;

    public Adjective(String initialForm) {
        this();
        wordFormsMutableAttributesMapping.setWordForm(initialForm, MutableAttributes.initialFormAttributes);
    }

    public Adjective() {
        this(new ImmutableAttributes(), new WordFormsMutableAttributesMapping<>(MAXIMUM_NUMBER_OF_WORD_FORMS, MAXIMUM_NUMBER_OF_WORD_FORMS));
    }

    public Adjective(ImmutableAttributes wordImmutableAttributes, WordFormsMutableAttributesMapping<String, MutableAttributes> wordFormsMutableAttributesMapping) {
        this.wordImmutableAttributes = wordImmutableAttributes;
        this.wordFormsMutableAttributesMapping = wordFormsMutableAttributesMapping;
    }

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
        Adjective rhs = (Adjective) obj;
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
