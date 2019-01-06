package main.controller;

import main.data.Division;
import main.data.Team;
import main.data.persistence.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TeamLeaderboardController {
    private TeamRepository teamRepository;

    @Autowired
    public TeamLeaderboardController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teamranks")
    public String teamranks(Model model) {
        List<Team> noviceRanks = teamRepository.findByDivision(Division.NOVICE, sortByScoreDesc());
        model.addAttribute("noviceRanks", noviceRanks);

        List<Team> advancedRanks = teamRepository.findByDivision(Division.ADVANCED, sortByScoreDesc());
        model.addAttribute("advancedRanks", advancedRanks);
        return "teamranks";
    }

    private Sort sortByScoreDesc() {
        return new Sort(Sort.Direction.DESC, "score");
    }
}
