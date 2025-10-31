package io.github.granekux.wieczornydecydent.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
class TestController {

    @GetMapping("/elo")
    public String elo() {
        return "elo";
    }

}
