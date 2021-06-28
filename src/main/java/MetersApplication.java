import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import ru.achernyavskiy0n.config.MetersConfiguration;

@EnableAutoConfiguration
@Import(value = MetersConfiguration.class)
public class MetersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetersApplication.class, args);
    }
}
