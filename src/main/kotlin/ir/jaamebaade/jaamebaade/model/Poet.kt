package ir.jaamebaade.jaamebaade.model

import jakarta.persistence.*

@Entity
@Table(name = "poets")
class Poet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(columnDefinition = "TEXT", nullable = true)
    var description: String? = null
}