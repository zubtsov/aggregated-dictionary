package org.zubtsov.linguistics.dictionary.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.zubtsov.linguistics.dictionary.entities.utils.WordFormsMutableAttributesMapping;

import java.util.Collection;
import java.util.Map;

//todo: made generic & add methods, or add abstract class & add default implementation
@ToString
public abstract class MutablePartOfSpeech<I, M> {

    @Getter
    @Setter
    protected I wordImmutableAttributes;
    @Getter
    protected WordFormsMutableAttributesMapping<Словоформа, M> wordFormsMutableAttributesMapping; //"Парадигма", по А.А. Зализняку

    public String getName() {
        return this.getClass().getName().toLowerCase();
    }

    //business methods
    public Collection<M> getMutableAttributes(String wordForm) {
        return wordFormsMutableAttributesMapping.getMutableAttributesByWordForm(new Словоформа(wordForm));
    }

    public Collection<Словоформа> getWordForm(M mutableAttributes) {
        return wordFormsMutableAttributesMapping.getWordFormByMutableAttributes(mutableAttributes);
    }

    public void setWordForm(M mutableAttributes, String wordForm) {
        wordFormsMutableAttributesMapping.setWordForm(new Словоформа(wordForm), mutableAttributes);
    }

    public void setWordForm(M mutableAttributes, Словоформа wordForm) {
        wordFormsMutableAttributesMapping.setWordForm(wordForm, mutableAttributes);
    }

    public void setWordForms(M mutableAttributes, Collection<Словоформа> wordForms) {
        wordFormsMutableAttributesMapping.setWordForms(wordForms, mutableAttributes);
    }

    public abstract Словоформа getInitialForm();

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
        MutablePartOfSpeech rhs = (MutablePartOfSpeech) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(getInitialForm(), rhs.getInitialForm()) //[STRONG ASSUMPTION] this check is sufficient
                .isEquals();
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Initial form: ");
//        sb.append(getInitialForm());
//        sb.append("\nImmutable attributes:\n");
//        sb.append(wordImmutableAttributes);
//        sb.append("\nWord forms:\n");
//        for (Map.Entry<Словоформа, M> entry : wordFormsMutableAttributesMapping.getWordFormToMutableAttributesMappingEntries()) {
//            sb.append(entry.getKey());
//            sb.append("[");
//            sb.append(entry.getValue());
//            sb.append("]\n");
//        }
//        return sb.toString();
//    }
}
