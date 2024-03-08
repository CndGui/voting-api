package com.guilhermerodrigues.votingapi.controller;

import com.guilhermerodrigues.votingapi.dto.SessionRequestDTO;
import com.guilhermerodrigues.votingapi.dto.SessionResponseDTO;
import com.guilhermerodrigues.votingapi.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sessions")
public class SessionController {
    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @GetMapping
    public List<SessionResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public SessionResponseDTO get(@PathVariable(name = "id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<SessionResponseDTO> create(@RequestBody SessionRequestDTO data) {
        return service.create(data);
    }

    @PutMapping("{id}")
    public ResponseEntity<SessionResponseDTO> update(@PathVariable(name = "id") Long id, @RequestBody SessionRequestDTO data) {
        return service.update(id, data);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
