package tech.aesys.finale.routine.controller;


import org.springframework.web.bind.annotation.*;
import tech.aesys.finale.routine.service.RoutineServiceImpl;

@RestController
@RequestMapping("api/routine")
public class RoutineController {


    private final RoutineServiceImpl routineService;

    public RoutineController(RoutineServiceImpl routineService) {
        this.routineService = routineService;
    }



}
