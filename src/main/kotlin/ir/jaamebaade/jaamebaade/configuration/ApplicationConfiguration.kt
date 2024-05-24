package ir.jaamebaade.jaamebaade.configuration

import ir.jaamebaade.jaamebaade.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Configuration
class ApplicationConfiguration(private val userRepository: UserRepository) {

    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username: String ->
            userRepository.findByUsername(username)
                .orElseThrow { UsernameNotFoundException("User not found") }
        }
    }
}

