package main;

import main.data.persistence.TeamRepository;
import main.data.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupInit implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    public StartupInit(TeamRepository teamRepository, UserRepository userRepository) {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    }
}