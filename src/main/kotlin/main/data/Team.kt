package main.data

import com.google.common.collect.ImmutableList
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import javax.persistence.*
import java.util.ArrayList
import javax.validation.constraints.*


@Entity
class Team(val id: Int, password: String, @field:NotBlank var school: String, division: Division) : UserDetailsImpl("team$id", password), Comparable<Team> {
    @Enumerated(EnumType.STRING)
    @NotNull
    private var division: Division = division
        set(division) {
            field  = division
            for (i in individuals) {
                i.division = division
            }
        }

    @Min(0)
    @Max(1440)
    var score: Int = 0

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

    fun getIndividuals(): List<Individual> {
        return individuals.toList()
    }

    override fun compareTo(other: Team): Int {
        return if (other.score != score) {
            other.score.compareTo(score)
        } else id.compareTo(other.id)
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return ImmutableList.of(SimpleGrantedAuthority("ROLE_TEAM"))
    }
}
