package org.zubtsov.linguistics.dictionary.entities;

import java.util.HashSet;
import java.util.Set;

//todo: add conflicts check; add merge functionality?
public class DictionaryChunk {
    private Set<Noun> nouns = new HashSet<>();
    private Set<Verb> verbs = new HashSet<>();

    public void addNoun(Noun noun) {
        nouns.add(noun);
    }

    public void addVerb(Verb verb) {
        verbs.add(verb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!nouns.isEmpty()) {
            sb.append("Nouns:\n");
            for (Noun noun : nouns) {
                sb.append(noun);
            }
        }if (!verbs.isEmpty()) {
            sb.append("Verbs:\n");
            for (Verb verb : verbs) {
                sb.append(verb);
            }
        }
        return sb.toString();
    }
}
