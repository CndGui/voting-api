package com.guilhermerodrigues.votingapi.service;

import com.guilhermerodrigues.votingapi.dto.SessionRequestDTO;
import com.guilhermerodrigues.votingapi.dto.SessionResponseDTO;
import com.guilhermerodrigues.votingapi.entity.Session;
import com.guilhermerodrigues.votingapi.entity.Topic;
import com.guilhermerodrigues.votingapi.exception.NotFoundException;
import com.guilhermerodrigues.votingapi.exception.ParametersNotValidException;
import com.guilhermerodrigues.votingapi.repository.SessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {
    private final SessionRepository repository;

    public SessionService(SessionRepository repository, TopicService topicService) {
        this.repository = repository;
        this.topicService = topicService;
    }

    private final TopicService topicService;

    public List<SessionResponseDTO> getAll() {
        return repository.findAll().stream()
            .map(SessionResponseDTO::new)
            .collect(Collectors.toList());
    }

    public SessionResponseDTO get(Long id) {
        return repository.findById(id).map(SessionResponseDTO::new).orElseThrow(() -> (
            new NotFoundException("The session with ID " + id + " does not exist!")
        ));
    }

    public ResponseEntity<SessionResponseDTO> create(SessionRequestDTO data) {
        if (data.topicID() == null) {
            throw new ParametersNotValidException("The body of session is incorrect!");
        }

        Topic topic = topicService.get(data.topicID());
        Session newSession = repository.save(new Session(topic));

        return ResponseEntity.status(HttpStatus.CREATED).body(new SessionResponseDTO(newSession));
    }

    public ResponseEntity<SessionResponseDTO> update(Long id, SessionRequestDTO data) {
        Session session = repository.findById(id).orElseThrow(() -> (
            new NotFoundException("The session with ID " + id + " does not exist!")
        ));

        if (data.topicID() == null) {
            throw new ParametersNotValidException("The body of session is incorrect!");
        }

        Topic topic = topicService.get(data.topicID());

        session.setTopic(topic);
        Session updateSession = repository.save(session);

        return ResponseEntity.ok(new SessionResponseDTO(updateSession));
    }

    public void delete(Long id) {
        repository.findById(id).orElseThrow(() -> (
            new NotFoundException("The session with ID " + id + " does not exist!")
        ));

        repository.deleteById(id);
    }
}
