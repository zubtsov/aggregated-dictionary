package org.zubtsov.linguistics.dictionary.entities.utils;

import org.zubtsov.linguistics.dictionary.entities.Adjective;
import org.zubtsov.linguistics.dictionary.entities.Noun;
import org.zubtsov.linguistics.dictionary.entities.Verb;

import java.util.HashSet;
import java.util.Set;

//todo: add conflicts check; add merge functionality?
public class DictionaryChunk {
    private Set<Noun> nouns = new HashSet<>();
    private Set<Verb> verbs = new HashSet<>();
    private Set<Adjective> adjectives = new HashSet<>();

    public void addNoun(Noun noun) {
        nouns.add(noun);
    }

    public void addVerb(Verb verb) {
        verbs.add(verb);
    }

    public void addAdjective(Adjective adjective) {
        adjectives.add(adjective);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!nouns.isEmpty()) {
            sb.append("Nouns:\n");
            for (Noun noun : nouns) {
                sb.append(noun);
            }
        }
        if (!verbs.isEmpty()) {
            sb.append("Verbs:\n");
            for (Verb verb : verbs) {
                sb.append(verb);
            }
        }
        if (!adjectives.isEmpty()) {
            sb.append("Adjectives:\n");
            for (Adjective adjective : adjectives) {
                sb.append(adjective);
            }
        }
        return sb.toString();
    }
}
