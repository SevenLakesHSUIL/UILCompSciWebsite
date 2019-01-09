package main;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupInit implements ApplicationListener<ContextRefreshedEvent> {

    public StartupInit() {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    }
}