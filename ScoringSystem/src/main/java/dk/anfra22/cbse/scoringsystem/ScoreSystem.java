package dk.anfra22.cbse.scoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoreSystem {
    private int playerScore = 0;

    public static void main(String[] args) {
        SpringApplication.run(ScoreSystem.class, args);
    }

    @PutMapping("/addPoints")
    public void calculateScore(@RequestParam(value = "points", defaultValue = "0") int points) {
        this.playerScore += points;
    }

    @GetMapping("/getScore")
    public int getPlayerScore() {
        return this.playerScore;
    }
}
