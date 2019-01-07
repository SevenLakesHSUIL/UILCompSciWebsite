package main.data;

import com.google.common.collect.ImmutableList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
public class Team extends UserDetailsImpl implements Comparable<Team> {
    private int id;
    @NotBlank
    private String school;
    @Min(0)
    private int score;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Division division;

    @NotNull
    @Size(max = 3)
    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Individual> individuals;

    protected Team() {
    }

    public Team(int id, String password, String school, Division division) {
        super("team" + id, password);
        this.id = id;
        this.school = school;
        this.division = division;
        this.individuals = new ArrayList<>(3);

        for (int i = 0; i < 3; i++) {
            individuals.add(new Individual("", this, division));
        }
    }

    public int getId() {
        return id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Individual> getIndividuals() {
        return Collections.unmodifiableList(individuals);
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
        for (Individual i : individuals) {
            i.setDivision(division);
        }
    }

    @Override
    public int compareTo(Team o) {
        if (o.score != score) {
            return Integer.compare(o.score, score);
        }
        return Integer.compare(id, o.id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ImmutableList.of(new SimpleGrantedAuthority("ROLE_TEAM"));
    }
}
