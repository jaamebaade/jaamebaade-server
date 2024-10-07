package ir.jaamebaade.jaamebaade.repository

import ir.jaamebaade.jaamebaade.model.Poet
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PoetRepository : PagingAndSortingRepository<Poet, Int> {
    fun findById(id: Int): Poet

    fun findByNameContains(name: String, pageable: Pageable): Page<Poet>
}