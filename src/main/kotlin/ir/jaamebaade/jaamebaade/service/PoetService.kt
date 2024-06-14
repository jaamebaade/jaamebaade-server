package ir.jaamebaade.jaamebaade.service

import io.minio.MinioClient
import ir.jaamebaade.jaamebaade.dto.PoetDto
import ir.jaamebaade.jaamebaade.repository.PoetRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class PoetService(
    private val poetRepository: PoetRepository,
    private val minioClient: MinioClient
) {

    @Value("\${minio.bucket.name}")
    val bucketName: String? = null

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

    fun downloadPoet(poetId: Int): String {
        val prefix = "poet"
        // get resource form minio
        return try {
            minioClient.getPresignedObjectUrl(
                io.minio.GetPresignedObjectUrlArgs.builder()
                    .method(io.minio.http.Method.GET)
                    .bucket(bucketName)
                    .`object`("${prefix}_$poetId.zip")
                    .expiry(60, TimeUnit.MINUTES)
                    .build()
            )
        } catch (e: Exception) {
            throw RuntimeException("Error generating presigned URL", e)
        }
    }
}