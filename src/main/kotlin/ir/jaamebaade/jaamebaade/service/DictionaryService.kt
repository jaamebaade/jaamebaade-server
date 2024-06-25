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
        val word = wordRepository.findByName(wordMeaningRequest.word)
        return word?.toDto()

    }

}