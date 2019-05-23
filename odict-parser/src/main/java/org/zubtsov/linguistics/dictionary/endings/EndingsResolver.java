package org.zubtsov.linguistics.dictionary.endings;

import org.zubtsov.linguistics.dictionary.entities.characteristics.Одушевленность;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Падеж;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Род;
import org.zubtsov.linguistics.dictionary.entities.characteristics.Число;

import java.util.stream.Stream;

public class EndingsResolver {
    private String[] getСубстантивноеОкончание1(Число число,
                                                Род род,
                                                Падеж падеж,
                                                Одушевленность одушевленность) {
        switch (число) {
            case ЕДИНСТВЕННОЕ:
                switch (род) {
                    case МУЖСКОЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                                return new String[]{""};
                            case РОДИТЕЛЬНЫЙ:
                                return new String[]{"а"};
                            case ДАТЕЛЬНЫЙ:
                                return new String[]{"у"};
                            case ВИНИТЕЛЬНЫЙ:
                                switch (одушевленность) {
                                    case ОДУШЕВЛЕННОЕ:
                                        return new String[]{""};
                                    case НЕОДУШЕВЛЕННОЕ:
                                        return new String[]{"а"};
                                    default:
                                        throw new IllegalArgumentException("Отсутствует или неизвестная одушевленность " + одушевленность);
                                }
                            case ТВОРИТЕЛЬНЫЙ:
                                return new String[]{"ом"};
                            case ПРЕДЛОЖНЫЙ:
                                return new String[]{"е"};
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    case ЖЕНСКИЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                                return new String[]{"а"};
                            case РОДИТЕЛЬНЫЙ:
                                return new String[]{"ы"};
                            case ДАТЕЛЬНЫЙ:
                            case ПРЕДЛОЖНЫЙ:
                                return new String[]{"е"};
                            case ВИНИТЕЛЬНЫЙ:
                                return new String[]{"у"};
                            case ТВОРИТЕЛЬНЫЙ:
                                return new String[]{"ой", "ою"};
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    case СРЕДНИЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                            case ВИНИТЕЛЬНЫЙ:
                                return new String[]{"о"};
                            case РОДИТЕЛЬНЫЙ:
                                return new String[]{"а"};
                            case ДАТЕЛЬНЫЙ:
                                return new String[]{"у"};
                            case ТВОРИТЕЛЬНЫЙ:
                                return new String[]{"ом"};
                            case ПРЕДЛОЖНЫЙ:
                                return new String[]{"е"};
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    default:
                        throw new IllegalArgumentException("Отсутствует или неизвестный род " + род);
                }
            case МНОЖЕСТВЕННОЕ:
                switch (род) {
                    case МУЖСКОЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                                return new String[]{"ы"};
                            case РОДИТЕЛЬНЫЙ:
                                return new String[]{"ов"};
                            case ДАТЕЛЬНЫЙ:
                                return new String[]{"ам"};
                            case ВИНИТЕЛЬНЫЙ:
                                switch (одушевленность) {
                                    case ОДУШЕВЛЕННОЕ:
                                        return new String[]{"ов"};
                                    case НЕОДУШЕВЛЕННОЕ:
                                        return new String[]{"ы"};
                                    default:
                                        throw new IllegalArgumentException("Отсутствует или неизвестная одушевленность " + одушевленность);
                                }
                            case ТВОРИТЕЛЬНЫЙ:
                                return new String[]{"ами"};
                            case ПРЕДЛОЖНЫЙ:
                                return new String[]{"ах"};
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    case ЖЕНСКИЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                                return new String[]{"ы"};
                            case РОДИТЕЛЬНЫЙ:
                                return new String[]{""};
                            case ДАТЕЛЬНЫЙ:
                                return new String[]{"ам"};
                            case ВИНИТЕЛЬНЫЙ:
                                switch (одушевленность) {
                                    case ОДУШЕВЛЕННОЕ:
                                        return new String[]{""};
                                    case НЕОДУШЕВЛЕННОЕ:
                                        return new String[]{"ы"};
                                    default:
                                        throw new IllegalArgumentException("Отсутствует или неизвестная одушевленность " + одушевленность);
                                }
                            case ТВОРИТЕЛЬНЫЙ:
                                return new String[]{"ами"};
                            case ПРЕДЛОЖНЫЙ:
                                return new String[]{"ах"};
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    case СРЕДНИЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                                return new String[]{"а"};
                            case РОДИТЕЛЬНЫЙ:
                                return new String[]{""};
                            case ДАТЕЛЬНЫЙ:
                                return new String[]{"ам"};
                            case ВИНИТЕЛЬНЫЙ:
                                switch (одушевленность) {
                                    case ОДУШЕВЛЕННОЕ:
                                        return new String[]{""};
                                    case НЕОДУШЕВЛЕННОЕ:
                                        return new String[]{"а"};
                                    default:
                                        throw new IllegalArgumentException("Отсутствует или неизвестная одушевленность " + одушевленность);
                                }
                            case ТВОРИТЕЛЬНЫЙ:
                                return new String[]{"ами"};
                            case ПРЕДЛОЖНЫЙ:
                                return new String[]{"ах"};
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    default:
                        throw new IllegalArgumentException("Отсутствует или неизвестный род " + род);
                }
            default:
                throw new IllegalArgumentException("Отсутствует или неизвестное число " + число);
        }
    }

    private String[] getСубстантивноеОкончание2(Число число,
                                                Род род,
                                                Падеж падеж,
                                                Одушевленность одушевленность,
                                                boolean ударноеОкончание) {
        switch (число) {
            case ЕДИНСТВЕННОЕ:
                switch (род) {
                    case МУЖСКОЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                                return new String[]{"ь"};
                            case РОДИТЕЛЬНЫЙ:
                                return new String[]{"я"};
                            case ДАТЕЛЬНЫЙ:
                                return new String[]{"ю"};
                            case ВИНИТЕЛЬНЫЙ:
                                switch (одушевленность) {
                                    case ОДУШЕВЛЕННОЕ:
                                        return new String[]{"я"};
                                    case НЕОДУШЕВЛЕННОЕ:
                                        return new String[]{"ь"};
                                    default:
                                        throw new IllegalArgumentException("Отсутствует или неизвестная одушевленность " + одушевленность);
                                }
                            case ТВОРИТЕЛЬНЫЙ:
                                if (ударноеОкончание) {
                                    return new String[]{"ём"};
                                } else {
                                    return new String[]{"ем"};
                                }
                            case ПРЕДЛОЖНЫЙ:
                                return getСубстантивноеОкончание1(число, род, падеж, одушевленность);
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    case ЖЕНСКИЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                                return new String[]{"я"};
                            case РОДИТЕЛЬНЫЙ:
                                return new String[]{"и"};
                            case ДАТЕЛЬНЫЙ:
                            case ПРЕДЛОЖНЫЙ:
                                return getСубстантивноеОкончание1(число, род, падеж, одушевленность);
                            case ВИНИТЕЛЬНЫЙ:
                                return new String[]{"ю"};
                            case ТВОРИТЕЛЬНЫЙ:
                                if (ударноеОкончание) {
                                    return new String[]{"ёй", "ёю"};
                                } else {
                                    return new String[]{"ей", "ею"};
                                }
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    case СРЕДНИЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                            case ВИНИТЕЛЬНЫЙ:
                                if (ударноеОкончание) {
                                    return new String[]{"ё"};
                                } else {
                                    return new String[]{"е"};
                                }
                            case РОДИТЕЛЬНЫЙ:
                                return new String[]{"я"};
                            case ДАТЕЛЬНЫЙ:
                                return new String[]{"ю"};
                            case ТВОРИТЕЛЬНЫЙ:
                                if (ударноеОкончание) {
                                    return new String[]{"ём"};
                                } else {
                                    return new String[]{"ем"};
                                }
                            case ПРЕДЛОЖНЫЙ:
                                return getСубстантивноеОкончание1(число, род, падеж, одушевленность);
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    default:
                        throw new IllegalArgumentException("Отсутствует или неизвестный род " + род);
                }
            case МНОЖЕСТВЕННОЕ:
                switch (род) {
                    case МУЖСКОЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                                return new String[]{"и"};
                            case РОДИТЕЛЬНЫЙ:
                                return new String[]{"ей"};
                            case ДАТЕЛЬНЫЙ:
                                return new String[]{"ям"};
                            case ВИНИТЕЛЬНЫЙ:
                                switch (одушевленность) {
                                    case ОДУШЕВЛЕННОЕ:
                                        return new String[]{"ей"};
                                    case НЕОДУШЕВЛЕННОЕ:
                                        return new String[]{"и"};
                                    default:
                                        throw new IllegalArgumentException("Отсутствует или неизвестная одушевленность " + одушевленность);
                                }
                            case ТВОРИТЕЛЬНЫЙ:
                                return new String[]{"ями"};
                            case ПРЕДЛОЖНЫЙ:
                                return new String[]{"ях"};
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    case ЖЕНСКИЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                                return new String[]{"и"};
                            case РОДИТЕЛЬНЫЙ:
                                if (ударноеОкончание) {
                                    return new String[]{"ей"};
                                } else {
                                    return new String[]{"ь"};
                                }
                            case ДАТЕЛЬНЫЙ:
                                return new String[]{"ям"};
                            case ВИНИТЕЛЬНЫЙ:
                                switch (одушевленность) {
                                    case ОДУШЕВЛЕННОЕ:
                                        if (ударноеОкончание) {
                                            return new String[]{"ей"};
                                        } else {
                                            return new String[]{"ь"};
                                        }
                                    case НЕОДУШЕВЛЕННОЕ:
                                        return new String[]{"и"};
                                    default:
                                        throw new IllegalArgumentException("Отсутствует или неизвестная одушевленность " + одушевленность);
                                }
                            case ТВОРИТЕЛЬНЫЙ:
                                return new String[]{"ями"};
                            case ПРЕДЛОЖНЫЙ:
                                return new String[]{"ях"};
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    case СРЕДНИЙ:
                        switch (падеж) {
                            case ИМЕНИТЕЛЬНЫЙ:
                                return new String[]{"я"};
                            case РОДИТЕЛЬНЫЙ:
                                if (ударноеОкончание) {
                                    return new String[]{"ей"};
                                } else {
                                    return new String[]{"ь"};
                                }
                            case ДАТЕЛЬНЫЙ:
                                return new String[]{"ям"};
                            case ВИНИТЕЛЬНЫЙ:
                                switch (одушевленность) {
                                    case ОДУШЕВЛЕННОЕ:
                                        if (ударноеОкончание) {
                                            return new String[]{"ей"};
                                        } else {
                                            return new String[]{"ь"};
                                        }
                                    case НЕОДУШЕВЛЕННОЕ:
                                        return new String[]{"и"};
                                    default:
                                        throw new IllegalArgumentException("Отсутствует или неизвестная одушевленность " + одушевленность);
                                }
                            case ТВОРИТЕЛЬНЫЙ:
                                return new String[]{"ями"};
                            case ПРЕДЛОЖНЫЙ:
                                return new String[]{"ях"};
                            default:
                                throw new IllegalArgumentException("Отсутствует или неизвестный падеж " + падеж);
                        }
                    default:
                        throw new IllegalArgumentException("Отсутствует или неизвестный род " + род);
                }
            default:
                throw new IllegalArgumentException("Отсутствует или неизвестное число " + число);
        }
    }

    private String[] getСубстантивноеОкончание3(Число число,
                                                Род род,
                                                Падеж падеж,
                                                Одушевленность одушевленность,
                                                String графическаяОснова) {
//        //todo: use regex
//        if (графическаяОснова.endsWith("к") ||
//                графическаяОснова.endsWith("г") ||
//                графическаяОснова.endsWith("х")) {
        return Stream.of(getСубстантивноеОкончание1(число, род, падеж, одушевленность))
                .map(s -> s.replace("ы", "и"))
                .toArray(String[]::new);
//        }
    }
}
