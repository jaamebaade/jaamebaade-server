package ir.jaamebaade.jaamebaade.request

import java.io.Serializable

class LoginUserRequest (
    val username: String,
    val password: String,
) : Serializable