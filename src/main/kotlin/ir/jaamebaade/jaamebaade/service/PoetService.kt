package ir.jaamebaade.jaamebaade.service

import ir.jaamebaade.jaamebaade.dto.PoetDto
import ir.jaamebaade.jaamebaade.repository.PoetRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PoetService(private val poetRepository: PoetRepository) {
    fun listPoets(pageable: Pageable): Page<PoetDto>? {
        val poetDtoList = poetRepository.findAll(pageable)
            .map {
                PoetDto(
                    id = it.id!!,
                    name = it.name!!,
                    description = it.description
                )
            }
        return poetDtoList
    }
}