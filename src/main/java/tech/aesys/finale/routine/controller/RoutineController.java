package tech.aesys.finale.routine.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.aesys.finale.routine.service.RoutineService;
import tech.aesys.finale.routine.service.RoutineServiceImpl;
import tech.aesys.finale.routine.swagger.api.RoutinesApi;
import tech.aesys.finale.routine.swagger.model.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RoutineController implements RoutinesApi {


    private final RoutineService routineService;

    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }


    /**
     * PUT /routines/{id}/alerts : crea un Alert e lo associa alla Routine
     * crea una nuova risorsa Alert a partire dal body e la associa alla Routine identificata da id
     *
     * @param id         ID della routine (required)
     * @param alertInput (required)
     * @return alert creato e associato con successo alla routine (status code 201)
     * or invalid input (status code 400)
     * or routine not found (status code 404)
     * or errore interno del server (status code 500)
     */
    @Override
    public ResponseEntity<AlertOutput> createAlertForRoutine(Long id, AlertInput alertInput) {
        return null; //routineService.createAlertForRoutine(id, alertInput);
    }

    /**
     * POST /routines : crea una Routine
     * crea una nuova Routine contenente il body passato
     *
     * @param routineInput (required)
     * @return routine creata con successo (status code 201)
     * or invalid input (status code 400)
     * or errore interno del server (status code 500)
     */
    @Override
    public ResponseEntity<RoutineOutput> createRoutine(RoutineInput routineInput) {
        RoutineOutput response = routineService.createRoutine(routineInput);
        return ResponseEntity.ok().body(response);
    }


    @Operation(
            operationId = "deleteRoutine",
            summary = "elimina una Routine",
            description = "elimina la Routine avente l'id specificato",
            tags = { "Routine" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "eliminazione avvenuta con successo"),
                    @ApiResponse(responseCode = "404", description = "routine not found"),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/routines/{id}"
    )

    public ResponseEntity<Void> deleteRoutine(
            @Parameter(name = "id", description = "ID della routine", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ){
        routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /routines/{id}/alerts : recupera gli Alert di una Routine
     * recupera tutti gli Alert associati alla Routine avente id passato
     *
     * @param id ID della routine (required)
     * @return lista di alert associati alla routine (status code 200)
     * or routine not found (status code 404)
     * or errore interno del server (status code 500)
     */
    @Override
    public ResponseEntity<List<AlertOutput>> getAlertsForRoutine(Long id) {
        List<AlertOutput> response = routineService.getAlertsForRoutine(id);
        return ResponseEntity.ok().body(response);
    }

    /**
     * GET /routines : recupera tutte le Routine
     * restituisce tutte le routine registrate
     *
     * @return lista di tutte le routine (status code 200)
     * or errore interno del server (status code 500)
     */
    @Override
    public ResponseEntity<List<RoutineOutput>> getAllRoutines() {
        List<RoutineOutput> response = routineService.getAllRoutines();
        return ResponseEntity.ok().body(response);
    }

    /**
     * GET /routines/{id} : recupera una Routine
     * recupera una Routine univoca tramite il suo id specificato
     *
     * @param id ID della routine (required)
     * @return singola routine (status code 200)
     * or routine not found (status code 404)
     * or errore interno del server (status code 500)
     */
    @Override
    public ResponseEntity<RoutineOutput> getRoutineById(Long id) {
        RoutineOutput response = routineService.getRoutineById(id);
        return ResponseEntity.ok().body(response);
    }

    /**
     * POST /routines/{id}/alerts : associa un Alert alla Routine
     * Associa un alert esistente avente id passato nel body ad una routine specificata tramite il parametro id
     *
     * @param id                        ID della routine (required)
     * @param linkAlertToRoutineRequest (required)
     * @return alert associato con successo alla routine (status code 201)
     * or invalid input (status code 400)
     * or routine or alert not found (status code 404)
     * or errore interno del server (status code 500)
     */
    @Override
    public ResponseEntity<AlertOutput> linkAlertToRoutine(Long id, LinkAlertToRoutineRequest linkAlertToRoutineRequest) {
        return null;
    }

    /**
     * PATCH /routines/{id} : patch Routine
     * aggiorna parzialmente la Routine avente id passato col body della request
     *
     * @param id           ID della routine (required)
     * @param routineInput (required)
     * @return routine aggiornata (status code 200)
     * or invalid input (status code 400)
     * or routine not found (status code 404)
     * or errore interno del server (status code 500)
     */
    @Override
    public ResponseEntity<RoutineOutput> patchRoutine(Long id, RoutineInput routineInput) {
        RoutineOutput response = routineService.patchRoutine(id, routineInput);
        return ResponseEntity.ok().body(response);
    }

    /**
     * DELETE /routines/{id}/alerts/{alertId} : elimina un Alert da una Routine
     * elimina l&#39;associazione tra una Routine e un Alert specificati dai parametri id e alertId
     *
     * @param id      ID della routine (required)
     * @param alertId ID dell&#39;alert (required)
     * @return associazione rimossa (status code 204)
     * or routine or alert not found (status code 404)
     * or errore interno del server (status code 500)
     */
    @Override
    public ResponseEntity<Void> unlinkAlertFromRoutine(Long id, Long alertId) {
        return null;
    }

    /**
     * PUT /routines/{id} : replace Routine
     * sostituisce completamente la Routine avente id passato, se non esiste la crea
     *
     * @param id           ID della routine (required)
     * @param routineInput (required)
     * @return routine sostituita (status code 200)
     * or routine creata con successo (status code 201)
     * or invalid input (status code 400)
     * or errore interno del server (status code 500)
     */
    //SBAGLIATA
    @Override
    public ResponseEntity<RoutineOutput> updateRoutine(Long id, RoutineInput routineInput) {
        return null;
    }
}
