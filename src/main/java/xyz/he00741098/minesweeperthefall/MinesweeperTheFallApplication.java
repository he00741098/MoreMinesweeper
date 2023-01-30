package xyz.he00741098.minesweeperthefall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class MinesweeperTheFallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinesweeperTheFallApplication.class, args);
    }
    @GetMapping("/hello")
    public String sayHello() {
        return "home";
    }
}
