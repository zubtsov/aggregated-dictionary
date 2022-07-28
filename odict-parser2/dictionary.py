import logging
import re
from typing import List, Optional

logger = logging.getLogger(__name__)


class DictionaryEntry:
    def __init__(self,
                 dictionary_entry_str,
                 initial_form: str,
                 stressed_vowels_positions: List[str],
                 part_of_speech_indicator: str,
                 part_of_speech: str,
                 inflection_type: str
                 ):
        self._dictionary_entry_str = dictionary_entry_str
        split_dictionary_entry = self._dictionary_entry_str.split()

        if len(split_dictionary_entry) < 3:
            raise Exception('Invalid dictionary entry: ' + dictionary_entry_str)

        self.initial_form = initial_form
        self.stressed_vowels_positions = stressed_vowels_positions
        self.part_of_speech_indicator = part_of_speech_indicator
        self.part_of_speech = part_of_speech
        self.inflection_type = inflection_type

    @staticmethod
    def parse(dictionary_entry_str: str) -> Optional['DictionaryEntry']:
        try:
            initial_form = DictionaryEntry.extract_initial_form(dictionary_entry_str)
            stressed_vowels_positions = DictionaryEntry.extract_stressed_vowels_positions(dictionary_entry_str)
            part_of_speech_indicator = DictionaryEntry.extract_part_of_speech_indicator(dictionary_entry_str)
            part_of_speech = DictionaryEntry.extract_part_of_speech(part_of_speech_indicator)
            inflection_type = DictionaryEntry.extract_inflection_type(dictionary_entry_str)

            return DictionaryEntry(
                dictionary_entry_str=dictionary_entry_str,
                initial_form=initial_form,
                stressed_vowels_positions=stressed_vowels_positions,
                part_of_speech_indicator=part_of_speech_indicator,
                part_of_speech=part_of_speech,
                inflection_type=inflection_type,
            )
        except Exception:
            logger.exception("Failed to parse dictionary entry: %s", dictionary_entry_str)
            return None

    @staticmethod
    def extract_initial_form(dictionary_entry: str):
        return dictionary_entry.split()[0]

    @staticmethod
    def extract_stressed_vowels_positions(dictionary_entry: str):
        return dictionary_entry.split()[1]

    @staticmethod
    def extract_part_of_speech_indicator(dictionary_entry: str):
        removed_extra_parts = re \
            .sub(r'_наст[А-Яа-я_\.\s0-9]+от_', '', dictionary_entry)
        removed_extra_parts = re \
            .sub(r'\(_в знач\. \"[А-Яа-я]+\"_\)', '', removed_extra_parts) \
            .replace('_повел. от_', '') \
            .replace('(_нормально без удар._)', '') \
            .replace('(_часто без удар._)', '') \
            .replace('(_без удар._)', '') \
            .replace('предикативное ', '')

        result = re \
            .sub(r'^[^А-Яа-я]+', '', removed_extra_parts.split()[2])
        result = re \
            .sub(r'[^А-Яа-я]+$', '', result)

        return result

    @staticmethod
    def extract_inflection_type(dictionary_entry: str):
        try:
            return dictionary_entry.split()[3]
        except IndexError:
            return None

    def __str__(self):
        r = vars(self)
        del r['_dictionary_entry_str']
        return str(r)

    @staticmethod
    def extract_part_of_speech(part_of_speech_indicator):
        match part_of_speech_indicator:
            case 'н':
                return 'наречие'
            case 'нсв' | 'св' | 'св-нсв' | 'св/нсв':
                return 'глагол'
            case 'м' | 'ж' | 'с' | \
                 'мо' | 'жо' | 'со' | \
                 'мо-жо' | 'мн' | \
                 'с//мн' | 'с//ж' | 'м//мо' | 'ж//жо' | 'мо//жо' | 'с//м' | 'ж//с' | 'мо//м' | 'с//со' | 'м//с' | 'жо//ж' | 'м//ж' | 'мо//со' | 'со//с' | 'мн.//с' | 'мн.//м' | 'м//мн':
                return 'существительное'
            case 'мс':
                return 'местоимение-существительное'
            case 'мс-п':
                return 'местоименное-прилагательное'
            case 'п':
                return 'прилагательное'
            case 'числ' | 'числ.-п':
                return 'числительное'
            case 'межд':
                return 'междометие'
            case 'предл':
                return 'предлог'
            case 'союз':
                return 'союз'
            case 'част':
                return 'частица'
            case 'вводн':
                return 'вводное слово'
            case 'предик':
                return 'предикатив'
            case 'сравн':
                return 'сравнительная степень'

            case _:
                if 'част.' in part_of_speech_indicator:
                    return 'частица'
                else:
                    raise Exception('Unknown part of speech indicator: ' + part_of_speech_indicator)
