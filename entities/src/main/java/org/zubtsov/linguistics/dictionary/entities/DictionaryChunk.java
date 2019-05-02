package org.zubtsov.linguistics.dictionary.entities;

import java.util.HashSet;
import java.util.Set;

public class DictionaryChunk {
    private Set<Noun> nouns = new HashSet<>();

    public void addNoun(Noun noun) {
        nouns.add(noun);
    }
}
