package com.guilhermerodrigues.votingapi.controller;

import com.guilhermerodrigues.votingapi.dto.TopicRequestDTO;
import com.guilhermerodrigues.votingapi.dto.TopicResponseDTO;
import com.guilhermerodrigues.votingapi.entity.Topic;
import com.guilhermerodrigues.votingapi.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("topics")
public class TopicController {
    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping
    public List<TopicResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public TopicResponseDTO get(@PathVariable(name = "id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<Topic> create(@RequestBody TopicRequestDTO data) {
        return service.create(data);
    }

    @PutMapping("{id}")
    public Topic update(@PathVariable(name = "id") Long id, @RequestBody @Validated TopicRequestDTO data) {
        return service.update(id, data);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
