package main.controller

import main.data.Division
import main.data.persistence.IndividualRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndividualLeaderboardController @Autowired
constructor(private val individualRepository: IndividualRepository) {

    @GetMapping("/indiranks")
    fun indiranks(model: Model): String {
        val noviceRanks = individualRepository.findByDivisionAndExists(Division.NOVICE, sortByScoreDesc())
        model.addAttribute("noviceRanks", noviceRanks)

        val advancedRanks = individualRepository.findByDivisionAndExists(Division.ADVANCED, sortByScoreDesc())
        model.addAttribute("advancedRanks", advancedRanks)

        return "indiranks"
    }

    private fun sortByScoreDesc(): Sort {
        return Sort(Sort.Direction.DESC, "score")
    }
}
