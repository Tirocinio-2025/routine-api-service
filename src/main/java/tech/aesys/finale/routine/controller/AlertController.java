package tech.aesys.finale.routine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.aesys.finale.routine.service.AlertService;
import tech.aesys.finale.routine.swagger.api.AlertsApi;
import tech.aesys.finale.routine.swagger.model.AlertInput;
import tech.aesys.finale.routine.swagger.model.AlertOutput;

import java.util.List;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class AlertController implements AlertsApi {
    private final AlertService alertService;


    /**
     * POST /alerts : crea un Alert
     * crea un nuovo Alert contenente il body passato
     *
     * @param alertInput  (required)
     * @return alert creato (status code 201)
     *         or invalid input (status code 400)
     *         or errore interno del server (status code 500)
     */
    @Override
    @Operation(
            operationId = "createAlert",
            summary = "crea un Alert",
            description = "crea un nuovo Alert contenente il body passato",
            tags = { "Alerts" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "alert creato", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AlertOutput.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "invalid input"),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/alerts",
            produces = { "application/json" },
            consumes = { "application/json" }
    )

    public ResponseEntity<AlertOutput> createAlert(
            @Parameter(name = "AlertInput", description = "", required = true) @Valid @RequestBody AlertInput alertInput
    ) {
        AlertOutput createdAlert = alertService.createAlert(alertInput);
        return ResponseEntity.status(201).body(createdAlert);
    }


    /**
     * DELETE /alerts/{id} : elimina un Alert
     * elimina l&#39;Alert avente l&#39;id specificato
     *
     * @param id ID dell&#39;alert (required)
     * @return eliminazione avvenuta con successo (status code 204)
     *         or alert not found (status code 404)
     *         or errore interno del server (status code 500)
     */
    @Override
    @Operation(
            operationId = "deleteAlert",
            summary = "elimina un Alert",
            description = "elimina l'Alert avente l'id specificato",
            tags = { "Alerts" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "eliminazione avvenuta con successo"),
                    @ApiResponse(responseCode = "404", description = "alert not found"),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/alerts/{id}"
    )

    public ResponseEntity<Void> deleteAlert(
            @Parameter(name = "id", description = "ID dell'alert", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * GET /alerts/{id} : recupera un Alert
     * restituisce un Alert univoco avente id passato
     *
     * @param id ID dell&#39;alert (required)
     * @return alert trovato (status code 200)
     *         or alert not found (status code 404)
     *         or errore interno del server (status code 500)
     */
    @Override
    @Operation(
            operationId = "getAlertById",
            summary = "recupera un Alert",
            description = "restituisce un Alert univoco avente id passato",
            tags = { "Alerts" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "alert trovato", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AlertOutput.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "alert not found"),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/alerts/{id}",
            produces = { "application/json" }
    )

    public ResponseEntity<AlertOutput> getAlertById(
            @Parameter(name = "id", description = "ID dell'alert", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
        AlertOutput alert = alertService.getAlertById(id);
        return ResponseEntity.ok(alert);
    }


    /**
     * GET /alerts : recupera tutti gli Alert
     * restituisce tutti gli Alert registrati
     *
     * @return lista di alert (status code 200)
     *         or errore interno del server (status code 500)
     */
    @Override
    @Operation(
            operationId = "getAllAlerts",
            summary = "recupera tutti gli Alert",
            description = "restituisce tutti gli Alert registrati",
            tags = { "Alerts" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "lista di alert", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AlertOutput.class)))
                    }),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/alerts",
            produces = { "application/json" }
    )

    public ResponseEntity<List<AlertOutput>> getAllAlerts(

    ) {
        List<AlertOutput> alerts = alertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }

    /**
     * PATCH /alerts/{id} : patch Alert
     * aggiorna parzialmente l&#39;Alert avente id passato col body della request
     *
     * @param id ID dell&#39;alert (required)
     * @param alertInput  (required)
     * @return alert aggiornato (status code 200)
     *         or invalid input (status code 400)
     *         or alert not found (status code 404)
     *         or errore interno del server (status code 500)
     */
    @Override
    @Operation(
            operationId = "patchAlert",
            summary = "patch Alert",
            description = "aggiorna parzialmente l'Alert avente id passato col body della request",
            tags = { "Alerts" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "alert aggiornato", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AlertOutput.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "invalid input"),
                    @ApiResponse(responseCode = "404", description = "alert not found"),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/alerts/{id}",
            produces = { "application/json" },
            consumes = { "application/json" }
    )

    public ResponseEntity<AlertOutput> patchAlert(
            @Parameter(name = "id", description = "ID dell'alert", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "AlertInput", description = "", required = true) @Valid @RequestBody AlertInput alertInput
    ) {
        AlertOutput updatedAlert = alertService.patchAlert(id, alertInput);
        return ResponseEntity.ok(updatedAlert);
    }


    /**
     * PUT /alerts/{id} : replace Alert
     * sostituisce completamente l&#39;Alert avente id passato, se non esiste lo crea
     *
     * @param id ID dell&#39;alert (required)
     * @param alertInput  (required)
     * @return alert sostituito (status code 200)
     *         or alert creato con successo (status code 201)
     *         or invalid input (status code 400)
     *         or errore interno del server (status code 500)
     */
    @Override
    @Operation(
            operationId = "updateAlert",
            summary = "replace Alert",
            description = "sostituisce completamente l'Alert avente id passato, se non esiste lo crea",
            tags = { "Alerts" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "alert sostituito", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AlertOutput.class))
                    }),
                    @ApiResponse(responseCode = "201", description = "alert creato con successo", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AlertOutput.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "invalid input"),
                    @ApiResponse(responseCode = "500", description = "errore interno del server")
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/alerts/{id}",
            produces = { "application/json" },
            consumes = { "application/json" }
    )

    public ResponseEntity<AlertOutput> updateAlert(
            @Parameter(name = "id", description = "ID dell'alert", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "AlertInput", description = "", required = true) @Valid @RequestBody AlertInput alertInput
    ) {
        AlertOutput updatedAlert = alertService.updateAlert(id, alertInput);
        if (updatedAlert != null) {
            return ResponseEntity.ok(updatedAlert);
        } else {
            AlertOutput createdAlert = alertService.createAlert(alertInput);
            return ResponseEntity.status(201).body(createdAlert);
        }
    }
}
