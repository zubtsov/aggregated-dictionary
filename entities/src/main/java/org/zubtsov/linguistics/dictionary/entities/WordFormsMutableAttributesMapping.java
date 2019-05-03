package org.zubtsov.linguistics.dictionary.entities;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.Collection;
import java.util.Map;

//bidirectional multimap in terms of guava (one to many relationship )
public class WordFormsMutableAttributesMapping<K, V> {
    public WordFormsMutableAttributesMapping() {
        wordFormsMutableAttributesMapping = LinkedHashMultimap.create();
        wordFormsMutableAttributesMappingInversed = LinkedHashMultimap.create();
    }

    public WordFormsMutableAttributesMapping(int numberOfWordForms, int maxNumberOfMutableAttributesCombinationsPerWordForm) {
        wordFormsMutableAttributesMapping = LinkedHashMultimap.create(
                numberOfWordForms,
                maxNumberOfMutableAttributesCombinationsPerWordForm
        );
        wordFormsMutableAttributesMappingInversed = LinkedHashMultimap.create(
                maxNumberOfMutableAttributesCombinationsPerWordForm,
                numberOfWordForms
        );
    }

    private boolean isMapsSynchronized = true;

    private Multimap<K, V> wordFormsMutableAttributesMapping;
    private Multimap<V, K> wordFormsMutableAttributesMappingInversed;

    public Collection<K> getWordFormByMutableAttributes(V mutableAttributes) {
        if (!isMapsSynchronized) {
            Multimaps.invertFrom(wordFormsMutableAttributesMapping, wordFormsMutableAttributesMappingInversed);
            isMapsSynchronized = true;
        }
        return wordFormsMutableAttributesMappingInversed.get(mutableAttributes);
    }

    public Collection<V> getMutableAttributesByWordForm(K wordForm) {
        return wordFormsMutableAttributesMapping.get(wordForm);
    }

    public Collection<Map.Entry<K, V>> getWordFormToMutableAttributesMappingEntries() {
        return wordFormsMutableAttributesMapping.entries();
    }

    public void setWordForm(K wordForm, V mutableAttributes) {
        wordFormsMutableAttributesMapping.put(wordForm, mutableAttributes);
        isMapsSynchronized = false;
    }
}
