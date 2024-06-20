package ir.jaamebaade.jaamebaade.service

import ir.jaamebaade.jaamebaade.dto.UserDto
import ir.jaamebaade.jaamebaade.repository.PoetRepository
import ir.jaamebaade.jaamebaade.repository.UserRepository
import ir.jaamebaade.jaamebaade.request.UserUpdateRequest
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val poetRepository: PoetRepository,
) {
    fun updateUser(id: Long, userUpdateRequest: UserUpdateRequest) {
        userRepository.findById(id).get().also { user ->
            userUpdateRequest.firstName?.let { user.firstName = it }
            userUpdateRequest.lastName?.let { user.lastName = it }
            userUpdateRequest.downloadedPoets?.let {
                user.downloadedPoets = it.map { poetId -> poetRepository.findById(poetId) }
            }
            userRepository.save(user)
        }
    }

    fun getUser(id: Long): UserDto {
        return userRepository.findById(id).get().toDto()
    }
}