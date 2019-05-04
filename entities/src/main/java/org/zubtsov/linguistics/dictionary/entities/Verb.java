package org.zubtsov.linguistics.dictionary.entities;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;

import java.util.Map;

public class Verb implements PartOfSpeech {
    public static final int MAXIMUM_NUMBER_OF_WORD_FORMS = Наклонение.values().length *
            Время.values().length *
            Род.values().length *
            Число.values().length *
            Лицо.values().length;

    public Verb(String initialForm) {
        this();
        wordFormsMutableAttributesMapping.setWordForm(initialForm, MutableAttributes.initialFormAttributes);
    }

    public Verb() {
        this(new ImmutableAttributes(), new WordFormsMutableAttributesMapping<>(MAXIMUM_NUMBER_OF_WORD_FORMS, MAXIMUM_NUMBER_OF_WORD_FORMS));
    }

    public Verb(ImmutableAttributes wordImmutableAttributes, WordFormsMutableAttributesMapping<String, MutableAttributes> wordFormsMutableAttributesMapping) {
        this.wordImmutableAttributes = wordImmutableAttributes;
        this.wordFormsMutableAttributesMapping = wordFormsMutableAttributesMapping;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MutableAttributes { //аналог "грамматического разряда" по А.А. Зализняку
        public static MutableAttributes initialFormAttributes = new MutableAttributes();

        private Наклонение наклонение;
        private Время время;
        private Род род;
        private Число число;
        private Лицо лицо;
    }

    @Data
    public static class ImmutableAttributes {
        private Вид вид;
        private Возвратность возвратность;
        private Переходность переходность;
        private Спряжение спряжение;
    }

    @Getter
    @Setter
    private ImmutableAttributes wordImmutableAttributes;
    @Getter
    private WordFormsMutableAttributesMapping<String, MutableAttributes> wordFormsMutableAttributesMapping; //"Парадигма", по А.А. Зализняку

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
        Verb rhs = (Verb) obj;
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
