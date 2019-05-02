package org.zubtsov.linguistics.dictionary.entities;

import org.zubtsov.linguistics.dictionary.entities.characteristics.*;

public class Существительное {
    public class Характеристики {
        //непостоянные признаки
        public Число число = Число.ЕДИНСТВЕННОЕ;
        public Падеж падеж = Падеж.ИМЕНИТЕЛЬНЫЙ;

        //постоянные признаки
        public Род род;
        public Склонение склонение;
        public Одушевленность одушевленность;
        public Собственность собственность;
    }

    private String[] словоформы;

    public String get(Характеристики характеристики, String начальнаяФорма) {
        return null;
    }

    public void set(Характеристики характеристики, String словоформа) {

    }
}
