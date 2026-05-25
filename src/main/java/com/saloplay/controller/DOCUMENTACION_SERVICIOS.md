# Documentación de Servicios - Saloplay API (EV03)

Base URL: http://localhost:8082  
Swagger UI: http://localhost:8082/swagger-ui/index.html

## AuthController
### POST /api/auth/register
Body:
{ "username": "antonio", "password": "1234" }
Respuestas:
- 200 OK: Registro exitoso
- 400 Bad Request: ERROR: ...

### POST /api/auth/login
Body:
{ "username": "antonio", "password": "1234" }
Respuestas:
- 200 OK: Autenticación satisfactoria
- 401 Unauthorized: ERROR: autenticación fallida

## MatchController
### GET /api/matches
Lista partidos disponibles con cuotas.

## BetController
### POST /api/bets
Body:
{ "username":"antonio","matchId":1,"selection":"Local","odd":2.1,"stake":10000 }
Respuestas:
- 200 OK: apuesta registrada (status PENDING)
- 400 Bad Request: ERROR: ...

### GET /api/bets/history/{username}
Ejemplo: /api/bets/history/antonio
Retorna el historial de apuestas del usuario.