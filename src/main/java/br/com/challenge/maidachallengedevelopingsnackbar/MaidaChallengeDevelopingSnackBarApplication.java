package br.com.challenge.maidachallengedevelopingsnackbar;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaidaChallengeDevelopingSnackBarApplication {

  public static void main(String[] args) {

    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    SpringApplication.run(MaidaChallengeDevelopingSnackBarApplication.class, args);
  }

}
