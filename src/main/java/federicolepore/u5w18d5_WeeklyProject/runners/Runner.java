package federicolepore.u5w18d5_WeeklyProject.runners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

    // services


    @Autowired
    public Runner() {
        //riempire con i serices
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
