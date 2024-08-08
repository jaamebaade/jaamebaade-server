package ir.jaamebaade.jaamebaade.service

import ir.jaamebaade.jaamebaade.dto.WordDto
import ir.jaamebaade.jaamebaade.repository.WordRepository
import ir.jaamebaade.jaamebaade.request.WordMeaningRequest
import org.springframework.stereotype.Service

@Service
class DictionaryService(
    private val wordRepository: WordRepository,
) {

    fun getWordMeaning(wordMeaningRequest: WordMeaningRequest): WordDto? {
        val words = wordRepository.findAllByName(wordMeaningRequest.word)
        if (words.isEmpty())
            return WordDto(
                name = wordMeaningRequest.word,
                meaning = ""
            )

        val wordDto = WordDto(
            name = words.first().name!!,
            meaning = words.map { it.meaning }.joinToString { it + '\n' }.trim()
        )
        return wordDto

    }

}