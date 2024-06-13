package ir.jaamebaade.jaamebaade.repository

import ir.jaamebaade.jaamebaade.model.Poet
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PoetRepository : PagingAndSortingRepository<Poet, Int> {
}