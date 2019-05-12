package org.zubtsov.linguistics.dictionary.entities.characteristics;

import java.util.stream.Stream;

public enum Падеж {
    ИМЕНИТЕЛЬНЫЙ, РОДИТЕЛЬНЫЙ, ДАТЕЛЬНЫЙ, ВИНИТЕЛЬНЫЙ, ТВОРИТЕЛЬНЫЙ, ПРЕДЛОЖНЫЙ, НЕИЗВЕСТНО;

    private static final Падеж[] cachedRealValues = Stream.of(values())
            .filter(падеж -> !падеж.equals(НЕИЗВЕСТНО))
            .toArray(Падеж[]::new);

    public static Падеж[] realValues() {
        return cachedRealValues;
    }
}
