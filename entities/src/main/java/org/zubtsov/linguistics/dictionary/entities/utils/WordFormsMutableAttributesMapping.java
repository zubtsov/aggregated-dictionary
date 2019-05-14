package org.zubtsov.linguistics.dictionary.entities.utils;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.Collection;
import java.util.Map;

//bidirectional multimap in terms of guava ( almost many to many relationship )
public class WordFormsMutableAttributesMapping<K, V> {
    public WordFormsMutableAttributesMapping() {
        wordFormsMutableAttributesMapping = LinkedHashMultimap.create();
        mutableAttributesWordFormsMapping = LinkedHashMultimap.create();
    }

    public WordFormsMutableAttributesMapping(int numberOfWordForms, int maxNumberOfMutableAttributesCombinationsPerWordForm) {
        wordFormsMutableAttributesMapping = LinkedHashMultimap.create(
                numberOfWordForms,
                maxNumberOfMutableAttributesCombinationsPerWordForm
        );
        mutableAttributesWordFormsMapping = LinkedHashMultimap.create(
                maxNumberOfMutableAttributesCombinationsPerWordForm,
                numberOfWordForms
        );
    }

    private boolean isMapsSynchronized = true;

    private Multimap<K, V> wordFormsMutableAttributesMapping;
    private Multimap<V, K> mutableAttributesWordFormsMapping;

    public Collection<K> getWordFormByMutableAttributes(V mutableAttributes) {
        if (!isMapsSynchronized) {
            Multimaps.invertFrom(wordFormsMutableAttributesMapping, mutableAttributesWordFormsMapping);
            isMapsSynchronized = true;
        }
        return mutableAttributesWordFormsMapping.get(mutableAttributes);
    }

    public Collection<V> getMutableAttributesByWordForm(K wordForm) {
        return wordFormsMutableAttributesMapping.get(wordForm);
    }

    public Collection<Map.Entry<K, V>> getWordFormToMutableAttributesMappingEntries() {
        return wordFormsMutableAttributesMapping.entries();
    }

    public Collection<Map.Entry<V, K>> getMutableAttributesToWordFormMappingEntries() {
        return mutableAttributesWordFormsMapping.entries();
    }

    public void setWordForm(K wordForm, V mutableAttributes) {
        wordFormsMutableAttributesMapping.put(wordForm, mutableAttributes);
        isMapsSynchronized = false;
    }

    public void setWordForms(Collection<K> wordForms, V mutableAttributes) {
        for (K wordForm : wordForms) {
            wordFormsMutableAttributesMapping.put(wordForm, mutableAttributes);
        }
        isMapsSynchronized = false;
    }
}
