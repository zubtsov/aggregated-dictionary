package org.zubtsov.linguistics.dictionary.entities.characteristics;

import java.util.stream.Stream;

public enum Наклонение {
    ИЗЪЯВИТЕЛЬНОЕ, ПОВЕЛИТЕЛЬНОЕ, УСЛОВНОЕ, НЕИЗВЕСТНО;

    private static final Наклонение[] cachedRealValues = Stream.of(values())
            .filter(наклонение -> !наклонение.equals(НЕИЗВЕСТНО))
            .toArray(Наклонение[]::new);

    public static Наклонение[] realValues() {
        return cachedRealValues;
    }
}
