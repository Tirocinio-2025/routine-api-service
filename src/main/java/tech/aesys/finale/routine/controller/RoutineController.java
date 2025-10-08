package tech.aesys.finale.routine.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.aesys.finale.routine.service.RoutineService;
import tech.aesys.finale.routine.swagger.api.RoutinesApi;
import tech.aesys.finale.routine.swagger.model.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
public class RoutineController implements RoutinesApi {


    private final RoutineService routineService;

    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
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
    @Operation(
            operationId = "createRoutine",
            summary = "crea una Routine",
            description = "crea una nuova Routine contenente il body passato",
            tags = {"Routine"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "routine creata con successo", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = RoutineOutput.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "invalid input"),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/routines",
            produces = {"application/json"},
            consumes = {"application/json"}
    )

    public ResponseEntity<RoutineOutput> createRoutine(
            @Parameter(name = "RoutineInput", description = "", required = true) @Valid @RequestBody RoutineInput routineInput
    ) {
        RoutineOutput response = routineService.createRoutine(routineInput);
        return ResponseEntity.ok().body(response);
    }


    /**
     * DELETE /routines/{id} : elimina una Routine
     * elimina la Routine avente l&#39;id specificato
     *
     * @param id ID della routine (required)
     * @return eliminazione avvenuta con successo (status code 204)
     * or routine not found (status code 404)
     * or errore interno del server (status code 500)
     */
    @Operation(
            operationId = "deleteRoutine",
            summary = "elimina una Routine",
            description = "elimina la Routine avente l'id specificato",
            tags = {"Routine"},
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
    ) {
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
    @Operation(
            operationId = "getAlertsForRoutine",
            summary = "recupera gli Alert di una Routine",
            description = "recupera tutti gli Alert associati alla Routine avente id passato",
            tags = {"associazione Alert Routine"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "lista di alert associati alla routine", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AlertOutput.class)))
                    }),
                    @ApiResponse(responseCode = "404", description = "routine not found"),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/routines/{id}/alerts",
            produces = {"application/json"}
    )

    public ResponseEntity<List<AlertOutput>> getAlertsForRoutine(
            @Parameter(name = "id", description = "ID della routine", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
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
    @Operation(
            operationId = "getAllRoutines",
            summary = "recupera tutte le Routine",
            description = "restituisce tutte le routine registrate",
            tags = {"Routine"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "lista di tutte le routine", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoutineOutput.class)))
                    }),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/routines",
            produces = {"application/json"}
    )

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
    @Operation(
            operationId = "getRoutineById",
            summary = "recupera una Routine",
            description = "recupera una Routine univoca tramite il suo id specificato",
            tags = {"Routine"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "singola routine", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = RoutineOutput.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "routine not found"),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/routines/{id}",
            produces = {"application/json"}
    )

    public ResponseEntity<RoutineOutput> getRoutineById(
            @Parameter(name = "id", description = "ID della routine", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
        RoutineOutput response = routineService.getRoutineById(id);
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
    @Operation(
            operationId = "unlinkAlertFromRoutine",
            summary = "elimina un Alert da una Routine",
            description = "elimina l'associazione tra una Routine e un Alert specificati dai parametri id e alertId",
            tags = {"associazione Alert Routine"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "associazione rimossa"),
                    @ApiResponse(responseCode = "404", description = "routine or alert not found"),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/routines/{id}/alerts/{alertId}"
    )

    public ResponseEntity<Void> unlinkAlertFromRoutine(
            @Parameter(name = "id", description = "ID della routine", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "alertId", description = "ID dell'alert", required = true, in = ParameterIn.PATH) @PathVariable("alertId") Long alertId
    ) {
        routineService.unlinkAlertFromRoutine(id, alertId);
        return ResponseEntity.noContent().build();
    }


    /**
     * POST /routines/{id}/alerts : crea un Alert e lo associa alla Routine
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
    public ResponseEntity<AlertOutput> createAlertForRoutine(@PathVariable("id") Long id, @RequestBody AlertInput alertInput) {
        var start = System.currentTimeMillis();
        log.info("Chiamata a createAlertForRoutine con id: {} e alertInput: {}", id, alertInput);

        AlertOutput response = routineService.createAlertForRoutine(id, alertInput);

        log.info("Chiamata a createAlertForRoutine con id: {} e response: {} spent {} ms", id, response, System.currentTimeMillis()-start);
        return ResponseEntity.ok().body(response);
    }
}
