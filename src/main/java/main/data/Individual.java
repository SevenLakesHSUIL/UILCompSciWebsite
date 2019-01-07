package main.data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Individual implements Comparable<Individual> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Min(-80)
    @Max(240)
    private int score;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Division division;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "team_id")
    public Team team;

    private Individual() {
    }

    Individual(String name, Team team, Division division) {
        this.name = name;
        this.team = team;
        this.division = division;
    }

    public Division getDivision() {
        return division;
    }

    void setDivision(Division division) {
        this.division = division;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Team getTeam() {
        return team;
    }

    void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int compareTo(Individual o) {
        if(o.score != score) {
            return Integer.compare(o.score, score);
        }
        return name.compareTo(o.name);
    }
}
