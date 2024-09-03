package ir.jaamebaade.jaamebaade

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class JaamebaadeApplication

fun main(args: Array<String>) {
    runApplication<JaamebaadeApplication>(*args)
}
