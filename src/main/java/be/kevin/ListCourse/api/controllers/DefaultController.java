package be.kevin.ListCourse.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
public class DefaultController {

    private static Logger logger = LoggerFactory.getLogger(DefaultController.class);


    /**
     *  renvoi une réponse serveur
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<String> pong() {
        logger.info("Démarrage des services ok .....");
        return new ResponseEntity<String>("Réponse du serveur: " + HttpStatus.OK.name(), HttpStatus.OK);
    }

    /**
     * renvoi une réponse serveur sur /api
     * @return
     */
    @GetMapping("/api")
    public @ResponseBody
    String ping() {
        logger.info("Réponse du serveur sur /api : " + HttpStatus.OK);
        return "{status : OK, timeStamp : " + System.currentTimeMillis() + " }";
    }

}
