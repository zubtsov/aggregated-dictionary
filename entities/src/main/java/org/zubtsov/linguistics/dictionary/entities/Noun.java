package org.zubtsov.linguistics.dictionary.entities;

import lombok.Data;
import lombok.Getter;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;

import java.util.Collection;

public class Noun implements PartOfSpeech {
    public Noun() {
        wordImmutableAttributes = new ImmutableAttributes();
        wordFormsMutableAttributesMapping = new BiMultiMap<>();
    }

    public Noun(ImmutableAttributes wordImmutableAttributes, BiMultiMap<String, MutableAttributes> wordFormsMutableAttributesMapping) {
        this.wordImmutableAttributes = wordImmutableAttributes;
        this.wordFormsMutableAttributesMapping = wordFormsMutableAttributesMapping;
    }

    @Data
    public static class MutableAttributes {
        public static Noun.MutableAttributes initialFormAttributes = new Noun.MutableAttributes();

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
    private ImmutableAttributes wordImmutableAttributes;
    @Getter
    private BiMultiMap<String, MutableAttributes> wordFormsMutableAttributesMapping;

    public Collection<MutableAttributes> getChangingAttributes(String wordForm) {
        return wordFormsMutableAttributesMapping.getValues(wordForm);
    }

    public String getWordForm(MutableAttributes changingAttributes) {
        return wordFormsMutableAttributesMapping.getKeys(changingAttributes).iterator().next();
    }

    public void setWordForm(MutableAttributes changingAttributes, String wordForm) {
        wordFormsMutableAttributesMapping.put(wordForm, changingAttributes);
    }

    @Override
    public String getInitialForm() {
        return wordFormsMutableAttributesMapping.getKeys(MutableAttributes.initialFormAttributes).iterator().next();
    }

    @Override
    public boolean equals(Object obj) {
        return false; //todo: use equals builder from apache commons
    }
}
