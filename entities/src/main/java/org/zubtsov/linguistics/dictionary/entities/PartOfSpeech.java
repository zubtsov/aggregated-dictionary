package org.zubtsov.linguistics.dictionary.entities;

//todo: made generic & add methods, or add abstract class & add default implementation
public interface PartOfSpeech {
    default String getName() {
        return this.getClass().getName().toLowerCase();
    }
    String getInitialForm();
}
