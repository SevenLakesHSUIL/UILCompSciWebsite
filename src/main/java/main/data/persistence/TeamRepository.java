package main.data.persistence;

import main.data.Division;
import main.data.Team;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TeamRepository extends PagingAndSortingRepository<Team, Integer> {
    Team findById(int id);
    Team findByUsername(String username);
    Iterable<Team> findBySchool(String school);
    Iterable<Team> findByDivision(Division division);
    Iterable<Team> findByDivision(Division division, Sort sort);
}