package org.zubtsov.linguistics.dictionary.entities;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Падеж;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Число;

import java.util.Collection;

public class BiMultiMap<K, V> {
    private boolean isMapsSynchronized = true;

    private Multimap<K, V> wordFormsMutableAttributesMapping = HashMultimap.create(
            Число.values().length * Падеж.values().length,
            Число.values().length * Падеж.values().length
    );
    private Multimap<V, K> wordFormsMutableAttributesMappingInversed = HashMultimap.create(
            Число.values().length * Падеж.values().length,
            Число.values().length * Падеж.values().length
    );

    public Collection<K> getKeys(V many) {
        if (!isMapsSynchronized) {
            Multimaps.invertFrom(wordFormsMutableAttributesMapping, wordFormsMutableAttributesMappingInversed);
            isMapsSynchronized = true;
        }
        return wordFormsMutableAttributesMappingInversed.get(many);
    }

    public Collection<V> getValues(K one) {
        return wordFormsMutableAttributesMapping.get(one);
    }

    public void put(K key, V value) {
        wordFormsMutableAttributesMapping.put(key, value);
        isMapsSynchronized = false;
    }
}
