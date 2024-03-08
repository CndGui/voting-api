package com.guilhermerodrigues.votingapi.service;

import com.guilhermerodrigues.votingapi.dto.TopicRequestDTO;
import com.guilhermerodrigues.votingapi.dto.TopicResponseDTO;
import com.guilhermerodrigues.votingapi.entity.Topic;
import com.guilhermerodrigues.votingapi.exception.NotFoundException;
import com.guilhermerodrigues.votingapi.exception.ParametersNotValidException;
import com.guilhermerodrigues.votingapi.repository.TopicRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {
    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<TopicResponseDTO> getAll() {
        return repository.findAll().stream().map(TopicResponseDTO::new).collect(Collectors.toList());
    }

    public TopicResponseDTO get(Long id) {
        return repository.findById(id).map(TopicResponseDTO::new).orElseThrow(() -> (
            new NotFoundException("The topic with ID " + id + " does not exist!")
        ));
    }

    public ResponseEntity<Topic> create(TopicRequestDTO data) {
        if (data.content() == null || data.content().isEmpty()) {
            throw new ParametersNotValidException("The body of topic is incorrect!");
        }

        Topic newTopic = repository.save(new Topic(data));

        return ResponseEntity.status(HttpStatus.CREATED).body(newTopic);
    }

    public Topic update(Long id, TopicRequestDTO data) {
        if (data.content() == null || data.content().isEmpty()) {
            throw new ParametersNotValidException("The body of topic is incorrect!");
        }

        return repository.findById(id).map(topic -> {
            topic.setContent(data.content());
            return repository.save(topic);
        }).orElseThrow(() -> (
            new NotFoundException("The topic with ID " + id + " does not exist!")
        ));
    }

    public void delete(Long id) {
        repository.findById(id).orElseThrow(() -> (
            new NotFoundException("The topic with ID " + id + " does not exist!")
        ));

        repository.deleteById(id);
    }
}
