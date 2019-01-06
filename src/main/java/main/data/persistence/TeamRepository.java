package main.data.persistence;

import main.data.Division;
import main.data.Team;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findById(int id);
    Team findByUsername(String username);
    List<Team> findBySchool(String school);
    List<Team> findByDivision(Division division);
    List<Team> findByDivision(Division division, Sort sort);
}