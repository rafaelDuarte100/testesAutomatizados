package br.com.duarte.unitTestRestControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/http")
public class HelloWorldController {

    @GetMapping("/200")
    public ResponseEntity get200() {
        return ok("Http Status 200");
    }

    @GetMapping("/404")
    public ResponseEntity get404() {
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/201")
    public ResponseEntity post201(@RequestBody TesteDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/400")
    public ResponseEntity post400(@RequestBody TesteDTO body) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
