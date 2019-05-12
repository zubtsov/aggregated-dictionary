package org.zubtsov.linguistics.dictionary.entities.characteristics;

import java.util.stream.Stream;

public enum Время {
    НАСТОЯЩЕЕ, ПРОШЕДШЕЕ, БУДУЩЕЕ, НЕИЗВЕСТНО;

    private static final Время[] cachedRealValues = Stream.of(values())
            .filter(время -> !время.equals(НЕИЗВЕСТНО))
            .toArray(Время[]::new);

    public static Время[] realValues() {
        return cachedRealValues;
    }
}
