package ir.jaamebaade.jaamebaade.controller

import ir.jaamebaade.jaamebaade.dto.PoetDto
import ir.jaamebaade.jaamebaade.service.PoetService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/poet")
class PoetController(private val poetService: PoetService) {
    @GetMapping(value = ["/"], produces = ["application/json"])
    fun list(pageable: Pageable): ResponseEntity<Page<PoetDto>> {
        val poetDtoList = poetService.listPoets(pageable)
        return ResponseEntity.ok(poetDtoList)
    }
}