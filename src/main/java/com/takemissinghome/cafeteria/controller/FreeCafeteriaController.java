package com.takemissinghome.cafeteria.controller;

import com.takemissinghome.cafeteria.domain.FreeCafeteriaResponse;
import com.takemissinghome.cafeteria.service.FreeCafeteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/careteria")
@RequiredArgsConstructor
public class FreeCafeteriaController {

    private final FreeCafeteriaService freeCafeteriaService;

    @GetMapping()
    public FreeCafeteriaResponse showCafeteriaDetails() throws IOException {
        return freeCafeteriaService.findCafeteriaInfo();

    }
}
