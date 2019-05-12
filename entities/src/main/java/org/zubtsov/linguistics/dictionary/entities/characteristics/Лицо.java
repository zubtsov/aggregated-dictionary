package org.zubtsov.linguistics.dictionary.entities.characteristics;

import java.util.stream.Stream;

public enum Лицо {
    ПЕРВОЕ, ВТОРОЕ, ТРЕТЬЕ, НЕИЗВЕСТНО;

    private static final Лицо[] cachedRealValues = Stream.of(values())
            .filter(лицо -> !лицо.equals(НЕИЗВЕСТНО))
            .toArray(Лицо[]::new);

    public static Лицо[] realValues() {
        return cachedRealValues;
    }
}
