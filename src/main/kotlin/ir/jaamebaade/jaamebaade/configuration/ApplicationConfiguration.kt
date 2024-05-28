package ir.jaamebaade.jaamebaade.configuration

import ir.jaamebaade.jaamebaade.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class ApplicationConfiguration(private val userRepository: UserRepository) {

    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username: String ->
            userRepository.findByUsername(username)
                .orElseThrow { UsernameNotFoundException("User not found") }
        }
    }
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider =
        DaoAuthenticationProvider().also {
            it.setUserDetailsService(userDetailsService())
            it.setPasswordEncoder(bCryptPasswordEncoder())
        }
    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager = config.authenticationManager

}

