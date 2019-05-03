package org.zubtsov.linguistics.dictionary.entities;

import java.util.HashSet;
import java.util.Set;

//todo: add conflicts check
public class DictionaryChunk {
    private Set<Noun> nouns = new HashSet<>();

    public void addNoun(Noun noun) {
        nouns.add(noun);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nouns:\n");
        for (Noun noun : nouns) {
            sb.append(noun);
        }
        return sb.toString();
    }
}
