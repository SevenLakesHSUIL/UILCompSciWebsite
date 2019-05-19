package main.data

import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import java.time.LocalDateTime

@Entity
class Individual internal constructor(@field:NotNull
                                      var name: String, team: Team, division: Division) : Comparable<Individual> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Int = 0
    @Min(-80)
    @Max(240)
    var score: Int = 0

    @Enumerated(EnumType.STRING)
    @NotNull
    var division: Division? = null
        internal set

    @NotNull
    @ManyToOne
    @JoinColumn(name = "team_id")
    var team: Team
        internal set

    @Version
    val timestamp: LocalDateTime? = null

    init {
        this.team = team
        this.division = division
    }

    override fun compareTo(other: Individual): Int {
        return if (other.score != score) {
            other.score.compareTo(score)
        } else name.compareTo(other.name)
    }
}
