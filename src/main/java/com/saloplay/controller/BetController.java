package com.saloplay.controller;


import com.saloplay.dto.AuthResponse;
import com.saloplay.dto.BetDto;
import com.saloplay.dto.BetRequest;
import com.saloplay.service.SaloplayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final SaloplayService service;

    public BetController(SaloplayService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> placeBet(@RequestBody BetRequest request) {
        try {
            BetDto bet = service.placeBet(request);
            return ResponseEntity.ok(bet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse("ERROR: " + e.getMessage()));
        }
    }

    @GetMapping("/history/{username}")
    public List<BetDto> getHistory(@PathVariable String username) {
        return service.getHistory(username);
    }
}
