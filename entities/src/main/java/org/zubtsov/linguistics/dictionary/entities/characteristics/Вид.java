package org.zubtsov.linguistics.dictionary.entities.characteristics;

import java.util.stream.Stream;

public enum Вид {
    СОВЕРШЕННЫЙ, НЕСОВЕРШЕННЫЙ, ДВУВИДОВОЙ, НЕИЗВЕСТНО;

    private static final Вид[] cachedRealValues = Stream.of(values())
            .filter(вид -> !вид.equals(НЕИЗВЕСТНО))
            .toArray(Вид[]::new);

    public static Вид[] realValues() {
        return cachedRealValues;
    }
}
