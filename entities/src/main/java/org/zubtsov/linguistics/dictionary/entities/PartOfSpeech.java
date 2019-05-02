package org.zubtsov.linguistics.dictionary.entities;

public interface PartOfSpeech {
    default String getName() {
        return this.getClass().getName().toLowerCase();
    }
    String getInitialForm();
}
