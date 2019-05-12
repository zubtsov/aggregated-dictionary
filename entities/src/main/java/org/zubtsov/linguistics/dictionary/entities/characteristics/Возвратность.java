package org.zubtsov.linguistics.dictionary.entities.characteristics;

import java.util.stream.Stream;

public enum Возвратность {
    ВОЗВРАТНЫЙ, НЕВОЗВРАТНЫЙ, НЕИЗВЕСТНО;

    private static final Возвратность[] cachedRealValues = Stream.of(values())
            .filter(возвратность -> !возвратность.equals(НЕИЗВЕСТНО))
            .toArray(Возвратность[]::new);

    public static Возвратность[] realValues() {
        return cachedRealValues;
    }
}
