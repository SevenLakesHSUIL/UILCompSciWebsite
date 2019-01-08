package main.controller;

import main.data.Division;
import main.data.Individual;
import main.data.Team;
import main.data.persistence.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
public class TeamLeaderboardController {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamLeaderboardController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/data/teamranks")
    public String teamranks(Model model) {
        Iterable<TeamTotalDTO> noviceRanks = stream(teamRepository.findByDivision(Division.NOVICE)).map(TeamTotalDTO::new).sorted(Comparator.comparingInt(TeamTotalDTO::getScore).thenComparing(TeamTotalDTO::getSchool)).collect(Collectors.toList());
        model.addAttribute("noviceRanks", noviceRanks);

        Iterable<TeamTotalDTO> advancedRanks = stream(teamRepository.findByDivision(Division.ADVANCED)).map(TeamTotalDTO::new).sorted(Comparator.comparingInt(TeamTotalDTO::getScore).thenComparing(TeamTotalDTO::getSchool)).collect(Collectors.toList());
        model.addAttribute("advancedRanks", advancedRanks);
        return "data/teamranks";
    }

    private <T> Stream<T> stream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    private static class TeamTotalDTO {
        private int score;
        @NotBlank
        private String school;
        private int id;
        private TeamTotalDTO(Team t) {
            this.school = t.getSchool();
            this.id = t.getId();
            this.score = t.getScore() + t.getIndividuals().stream().mapToInt(Individual::getScore).sum();
        }

        public int getScore() {
            return score;
        }

        public String getSchool() {
            return school;
        }

        public int getId() {
            return id;
        }
    }
}
