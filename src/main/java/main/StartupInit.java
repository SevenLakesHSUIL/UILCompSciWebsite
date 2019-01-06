package main;

import main.data.Division;
import main.data.Team;
import main.data.User;
import main.data.persistence.TeamRepository;
import main.data.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupInit implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private TeamRepository teamRepository;

    @Autowired
    public StartupInit(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        userRepository.saveAndFlush(new User("a", "a", true));
        Team t = new Team(1, "a", "SLHS", Division.NOVICE);
        t.getIndividuals().get(0).setName("Bob");
        t.getIndividuals().get(1).setName("Sam");
        teamRepository.save(t);
        t = new Team(2, "a", "SLHS", Division.ADVANCED);
        t.getIndividuals().get(0).setName("Bob");
        t.getIndividuals().get(1).setName("Sam");
        teamRepository.save(t);
        teamRepository.flush();
    }
}