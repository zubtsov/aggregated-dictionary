package org.zubtsov.linguistics.dictionary.entities;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.zubtsov.linguistics.dictionary.entities.characteristics.ЧастьРечи;

import java.util.ArrayList;
import java.util.List;

public class Слово {
    @Getter
    @Setter
    public ЧастьРечи частьРечи = ЧастьРечи.НЕИЗВЕСТНО;
    @Getter
    @Setter
    public List<Словоформа> словоформы = new ArrayList<>(12);

    public Словоформа getНачальнаяФорма() {
        for (Словоформа ф : словоформы) {
            if (ф.начальнаяФорма) {
                return ф;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Часть речи: " + частьРечи + "; Словоформы: " + StringUtils.join(словоформы, ",");
    }
}
