package org.zubtsov.linguistics.dictionary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zubtsov.linguistics.dictionary.entities.characteristics.*;

//todo: refactor/reimplement, too many exceptions in rules & complex logic
public class Numeral extends MutablePartOfSpeech<Numeral.ImmutableAttributes, Numeral.MutableAttributes> {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MutableAttributes { //аналог "грамматического разряда" по А.А. Зализняку
        private Падеж падеж = Падеж.НЕИЗВЕСТНО;
        private Род род = Род.НЕИЗВЕСТНО;
        private Число число = Число.НЕИЗВЕСТНО;
    }

    @Data
    public static class ImmutableAttributes {
        private ТипЧислительного типЧислительного = ТипЧислительного.НЕИЗВЕСТНО;
        private ПростотаЧислительного простотаЧислительного = ПростотаЧислительного.НЕИЗВЕСТНО;
    }

    //todo: если захардкодить исключения в этом методе, тогда извне не получится получить
    // начальную форму не зная конкретную словоформу...
    @Override
    public MutableAttributes getInitialFormAttributes() {
        switch (wordImmutableAttributes.типЧислительного) {
            case КОЛИЧЕСТВЕННОЕ:
            case СОБИРАТЕЛЬНОЕ:
                return new MutableAttributes(Падеж.ИМЕНИТЕЛЬНЫЙ, Род.ОТСУТСТВУЕТ, Число.ОТСУТСТВУЕТ);
            case ПОРЯДКОВОЕ:
                return new MutableAttributes(Падеж.ИМЕНИТЕЛЬНЫЙ, Род.МУЖСКОЙ, Число.ЕДИНСТВЕННОЕ);//кроме "оба", "обе"
            default:
                return null;
        }
    }
}
