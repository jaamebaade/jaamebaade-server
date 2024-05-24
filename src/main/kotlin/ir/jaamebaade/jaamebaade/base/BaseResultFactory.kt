package ir.jaamebaade.jaamebaade.base

import org.springframework.http.ResponseEntity
import java.io.Serializable

object BaseResultFactory {

    fun ok(result: BaseResult?): ResponseEntity<BaseResult> {
        return ResponseEntity.ok().body(result)
    }

    fun ok(result: Serializable?): ResponseEntity<BaseResult> {
        return ResponseEntity.ok().body(BaseResult(result ?: ""))
    }

    fun badRequest(result: BaseResult?): ResponseEntity<BaseResult>{
        return ResponseEntity.badRequest().body(result)
    }

    fun badRequest(result: String): ResponseEntity<BaseResult> {
        return ResponseEntity.badRequest().body(BaseResult(result = result))
    }

}