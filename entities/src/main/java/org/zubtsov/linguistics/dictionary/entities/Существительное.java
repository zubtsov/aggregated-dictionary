package org.zubtsov.linguistics.dictionary.entities;

import org.zubtsov.linguistics.dictionary.entities.characteristics.*;

import java.util.List;

public class Существительное {
    public class ИзменяемыеХарактеристики {
        public Число число = Число.ЕДИНСТВЕННОЕ;
        public Падеж падеж = Падеж.ИМЕНИТЕЛЬНЫЙ;
    }

    public class ПостоянныеХарактеристики {
        public Род род;
        public Склонение склонение;
        public Одушевленность одушевленность;
        public Собственность собственность;
    }

    //todo: implement

    public List<ИзменяемыеХарактеристики> getChangingAttributes(String словоформа) {
        return null;
    }

    public ПостоянныеХарактеристики getConstantAttributes(String словоформа) {
        return null;
    }

    public String getWordForm(ИзменяемыеХарактеристики changingAttributes, String начальнаяФорма) {
        return null;
    }

    public void setWordForm(ИзменяемыеХарактеристики constantAttributes, String wordForm) {

    }
}
