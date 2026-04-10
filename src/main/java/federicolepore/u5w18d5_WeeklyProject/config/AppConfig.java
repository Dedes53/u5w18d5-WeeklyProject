package federicolepore.u5w18d5_WeeklyProject.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class AppConfig {

    //    metto qua il bean per il faker come best practice
    @Bean
    public Faker faker() {
        return new Faker(Locale.ITALIAN);
    }

}
