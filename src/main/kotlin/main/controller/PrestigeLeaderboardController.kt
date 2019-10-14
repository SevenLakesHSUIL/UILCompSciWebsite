package main.controller

import main.data.persistence.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PrestigeLeaderboardController @Autowired
constructor(private val teamRepository: TeamRepository) {

    @GetMapping("/presranks")
    fun progranks(model: Model): String {
        val ranks = teamRepository.findAll(sortByPrestigeDesc())
        model.addAttribute("ranks", ranks)

        return "presranks"
    }
}

fun sortByPrestigeDesc(): Sort = Sort(Sort.Direction.DESC, "prestige")