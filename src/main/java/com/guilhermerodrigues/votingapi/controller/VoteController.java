package com.guilhermerodrigues.votingapi.controller;

import com.guilhermerodrigues.votingapi.dto.VoteRequestDTO;
import com.guilhermerodrigues.votingapi.dto.VoteResponseDTO;
import com.guilhermerodrigues.votingapi.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("votes")
public class VoteController {

    private final VoteService service;

    public VoteController(VoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<VoteResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public VoteResponseDTO get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @GetMapping("cpf/{cpf}")
    public VoteResponseDTO getByCpf(@PathVariable("cpf") Long cpf) {
        return service.getByCpf(cpf);
    }

    @PostMapping
    public ResponseEntity<VoteResponseDTO> create(@RequestBody VoteRequestDTO data) {
        return service.create(data);
    }

    @PutMapping("{id}/choice")
    public ResponseEntity<VoteResponseDTO> updateChoice(@PathVariable("id") Long id, @RequestBody VoteRequestDTO data) {
        return service.updateChoice(id, data);
    }

    @PutMapping("{id}/cpf")
    public ResponseEntity<VoteResponseDTO> updateCpf(@PathVariable("id") Long id, @RequestBody VoteRequestDTO data) {
        return service.updateCpf(id, data);
    }

    @PutMapping("{id}/session")
    public ResponseEntity<VoteResponseDTO> updateSessionID(@PathVariable("id") Long id, @RequestBody VoteRequestDTO data) {
        return service.updateSessionID(id, data);
    }
}
