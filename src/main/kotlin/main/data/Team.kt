package main.data

import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import javax.persistence.*
import java.util.ArrayList
import javax.validation.constraints.*

@Entity
class Team(val id: Int, password: String, @field:NotBlank var school: String, division: Division) : UserDetails("team$id", password), Comparable<Team> {
    @Enumerated(EnumType.STRING)
    @NotNull
    var division: Division = division
        set(division) {
            field = division
            for (i in individuals) {
                i.division = division
            }
        }

    @Min(0)
    @Max(720)
    var score: Int = 0

    @Min(0)
    var prestige: Int = 0

    @NotNull
    @Size(max = 3)
    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    private val individuals: List<Individual>

    init {
        this.individuals = ArrayList(3)
        for (i in 0..2) {
            individuals.add(Individual("", this, division))
        }
    }

    fun getIndividuals(): List<Individual> =
            individuals.toImmutableList()

    override fun compareTo(other: Team): Int =
            if (other.score != score) {
                other.score.compareTo(score)
            } else id.compareTo(other.id)

    override fun getAuthorities(): Collection<GrantedAuthority> =
            persistentListOf(SimpleGrantedAuthority("ROLE_TEAM"))
}
