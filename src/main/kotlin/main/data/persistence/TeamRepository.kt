package main.data.persistence

import main.data.Division
import main.data.Team
import org.springframework.data.domain.Sort
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional
interface TeamRepository : PagingAndSortingRepository<Team, Int> {
    fun findByUsername(username: String): Optional<Team>
    fun findBySchool(school: String): Iterable<Team>
    fun findByDivision(division: Division): Iterable<Team>
    fun findByDivision(division: Division, sort: Sort): Iterable<Team>
}
