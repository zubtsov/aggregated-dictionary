package org.zubtsov.linguistics.dictionary.entities.characteristics;

import java.util.stream.Stream;

public enum Одушевленность {
    ОДУШЕВЛЕННОЕ, НЕОДУШЕВЛЕННОЕ, НЕИЗВЕСТНО;

    private static final Одушевленность[] cachedRealValues = Stream.of(values())
            .filter(одушевленность -> !одушевленность.equals(НЕИЗВЕСТНО))
            .toArray(Одушевленность[]::new);

    public static Одушевленность[] realValues() {
        return cachedRealValues;
    }
}
