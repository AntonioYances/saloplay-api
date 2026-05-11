package com.saloplay.controller;


import com.saloplay.dto.MatchDto;
import com.saloplay.service.SaloplayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final SaloplayService service;

    public MatchController(SaloplayService service) {
        this.service = service;
    }

    @GetMapping
    public List<MatchDto> getMatches() {
        return service.getMatches();
    }
}
