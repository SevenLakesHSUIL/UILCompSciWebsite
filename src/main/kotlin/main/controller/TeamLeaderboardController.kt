package main.controller

import main.data.Division
import main.data.Team
import main.data.persistence.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

import javax.validation.constraints.NotBlank

@Controller
class TeamLeaderboardController @Autowired
constructor(private val teamRepository: TeamRepository) {

    @GetMapping("/data/teamranks")
    fun teamranks(model: Model): String {
        val noviceRanks = teamRepository.findByDivision(Division.NOVICE).map{ TeamTotalDTO(it) }.sorted()
        model.addAttribute("noviceRanks", noviceRanks)

        val advancedRanks = teamRepository.findByDivision(Division.ADVANCED).map{ TeamTotalDTO(it) }.sorted()
        model.addAttribute("advancedRanks", advancedRanks)
        return "data/teamranks"
    }

    private class TeamTotalDTO internal constructor(t: Team) : Comparable<TeamTotalDTO> {
        override fun compareTo(other: TeamTotalDTO): Int {
            return if(score != other.score) {
                Integer.compare(score, other.score)
            } else {
                if(school != other.school) {
                    school.compareTo(other.school)
                } else {
                    0
                }
            }
        }

        val score: Int = t.score + t.getIndividuals().map { it.score }.sum()
        @NotBlank
        val school: String = t.school
        val id: Int = t.id
    }
}
