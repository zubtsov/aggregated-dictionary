import logging

from dictionary import DictionaryEntry

logger = logging.getLogger(__name__)

if __name__ == '__main__':
    logging.basicConfig(level=logging.INFO, filename='output.log', encoding='UTF-8', filemode='w')

    for line in open("C:\\Users\\zubts\\My Drive\\Learning\\Books\\Linguistics\\Dictionaries\\odict.ru\\zalizniak.txt",
                     mode="rt", encoding="Windows-1251"):
        if not line.strip():
            continue

        dictionary_entry_str = line.strip()
        dictionary_entry = DictionaryEntry.parse(dictionary_entry_str)
        if dictionary_entry:
            logger.info(dictionary_entry)
