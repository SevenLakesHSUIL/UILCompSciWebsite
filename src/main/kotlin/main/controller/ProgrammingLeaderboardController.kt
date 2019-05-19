package main.controller

import main.data.Division
import main.data.persistence.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ProgrammingLeaderboardController @Autowired
constructor(private val teamRepository: TeamRepository) {

    @GetMapping("/progranks")
    fun progranks(model: Model): String {
        val noviceRanks = teamRepository.findByDivision(Division.NOVICE, sortByScoreDesc())
        model.addAttribute("noviceRanks", noviceRanks)

        val advancedRanks = teamRepository.findByDivision(Division.ADVANCED, sortByScoreDesc())
        model.addAttribute("advancedRanks", advancedRanks)
        return "progranks"
    }

    private fun sortByScoreDesc(): Sort {
        return Sort(Sort.Direction.DESC, "score")
    }
}
