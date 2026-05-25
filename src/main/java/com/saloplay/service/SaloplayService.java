package com.saloplay.service;


import com.saloplay.dto.BetDto;
import com.saloplay.dto.BetRequest;
import com.saloplay.dto.MatchDto;
import com.saloplay.dto.OddDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SaloplayService {

    private static final Map<String, String> users = new ConcurrentHashMap<>();

    // Partidos fijos (mock) para el proyecto
    private final List<MatchDto> matches = new ArrayList<>();

    // Apuestas por usuario: usuario -> lista de apuestas
    private final Map<String, List<BetDto>> betsByUser = new ConcurrentHashMap<>();

    public SaloplayService() {
        users.putIfAbsent("antonio", "1234");
        // Cargamos partidos de ejemplo con cuotas
        matches.add(new MatchDto(
                1,
                "Bayern vs Dortmund",
                "Hoy 8:00 PM",
                Arrays.asList(
                        new OddDto("Local", 2.10),
                        new OddDto("Empate", 3.00),
                        new OddDto("Visitante", 2.80)
                )
        ));
        matches.add(new MatchDto(
                2,
                "Real Madrid vs Barca",
                "Mañana 7:00 PM",
                Arrays.asList(
                        new OddDto("Local", 1.90),
                        new OddDto("Empate", 3.20),
                        new OddDto("Visitante", 3.10)
                )
        ));
    }

    // ---------------- AUTH ----------------

    public String register(String username, String password) {
        if (isBlank(username) || isBlank(password)) {
            return "ERROR: usuario y contraseña son obligatorios";
        }
        if (users.containsKey(username)) {
            return "ERROR: el usuario ya existe";
        }

        // Nota: sin encriptación por ser evidencia (luego se mejora)
        users.put(username, password);
        return "Registro exitoso";
    }

    public boolean login(String username, String password) {
        if (isBlank(username) || isBlank(password)) {
            return false;
        }
        return users.containsKey(username) && users.get(username).equals(password);
    }

    // ---------------- MATCHES ----------------

    public List<MatchDto> getMatches() {
        return matches;
    }

    public MatchDto findMatchById(int id) {
        return matches.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    // ---------------- BETS ----------------

    public BetDto placeBet(BetRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request no puede ser null");
        }
        if (isBlank(request.getUsername())) {
            throw new IllegalArgumentException("El username es obligatorio");
        }
        if (request.getStake() <= 0) {
            throw new IllegalArgumentException("El monto (stake) debe ser mayor a 0");
        }

        MatchDto match = findMatchById(request.getMatchId());
        if (match == null) {
            throw new IllegalArgumentException("Partido no encontrado");
        }

        BetDto bet = new BetDto(
                request.getUsername(),
                request.getMatchId(),
                match.getName(),
                request.getSelection(),
                request.getOdd(),
                request.getStake(),
                "PENDING",
                LocalDateTime.now().toString()
        );

        betsByUser.putIfAbsent(request.getUsername(), new ArrayList<>());
        betsByUser.get(request.getUsername()).add(0, bet); // agregar al inicio (más reciente primero)

        return bet;
    }

    public List<BetDto> getHistory(String username) {
        return betsByUser.getOrDefault(username, new ArrayList<>());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
