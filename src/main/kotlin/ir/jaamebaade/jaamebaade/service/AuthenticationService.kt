package ir.jaamebaade.jaamebaade.service

import ir.jaamebaade.jaamebaade.model.User
import ir.jaamebaade.jaamebaade.repository.UserRepository
import ir.jaamebaade.jaamebaade.request.RegisterUserRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun register(registerUserRequest: RegisterUserRequest): Long? {
        val existingUser: Optional<User> = userRepository.findByUsername(registerUserRequest.username)
        if (existingUser.isPresent) {
            return null
        }
        val user = User(
            username = registerUserRequest.username,
            password = passwordEncoder.encode(registerUserRequest.password),
            email = registerUserRequest.email,
            firstName = registerUserRequest.firstName,
            lastName = registerUserRequest.lastName,
        )
        userRepository.save(user)
        return user.id

    }
}