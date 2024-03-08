package com.guilhermerodrigues.votingapi.service;

import com.guilhermerodrigues.votingapi.dto.VoteRequestDTO;
import com.guilhermerodrigues.votingapi.dto.VoteResponseDTO;
import com.guilhermerodrigues.votingapi.entity.Session;
import com.guilhermerodrigues.votingapi.entity.Vote;
import com.guilhermerodrigues.votingapi.exception.NotFoundException;
import com.guilhermerodrigues.votingapi.exception.ParametersNotValidException;
import com.guilhermerodrigues.votingapi.repository.SessionRepository;
import com.guilhermerodrigues.votingapi.repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private final VoteRepository repository;
    private final SessionRepository sessionRepository;

    public VoteService(VoteRepository repository, SessionRepository sessionRepository) {
        this.repository = repository;
        this.sessionRepository = sessionRepository;
    }

    public List<VoteResponseDTO> getAll() {
        return repository.findAll().stream().map(VoteResponseDTO::new).collect(Collectors.toList());
    }

    public VoteResponseDTO get(Long id) {
        return repository.findById(id).map(VoteResponseDTO::new).orElseThrow(() -> (
            new NotFoundException("The vote with ID " + id + " does not exist!")
        ));
    }

    public VoteResponseDTO getByCpf(Long cpf) {
        List<Vote> voteList = repository.findAll();
        Long id = -1L;
        for (Vote vote : voteList) {
            if (Objects.equals(vote.getCpf(), cpf)) {
                id = vote.getId();
            }
        }

        return repository.findById(id).map(VoteResponseDTO::new).orElseThrow(() -> (
            new NotFoundException("The vote with CPF " + cpf + " does not exist!")
        ));
    }

    public ResponseEntity<VoteResponseDTO> create(VoteRequestDTO data) {
        if (data.cpf() == null || data.choice() == null || data.sessionID() == null) {
            throw new ParametersNotValidException("The body of vote is incorrect!");
        }

        if (data.cpf().toString().length() != 11) {
            throw new ParametersNotValidException("A CPF contains 11 numbers");
        }

        Session session = sessionRepository.findById(data.sessionID()).orElseThrow(() -> (
            new NotFoundException("The session with ID " + data.sessionID() + " does not exist!")
        ));
        Vote newVote = repository.save(new Vote(data, session));

        return ResponseEntity.status(HttpStatus.CREATED).body(new VoteResponseDTO(newVote));
    }

    public ResponseEntity<VoteResponseDTO> updateChoice(Long id, VoteRequestDTO data) {
        if (data.choice() == null) {
            throw new ParametersNotValidException("The body of vote is incorrect!");
        }

        Vote updateVote = repository.findById(id).map(vote -> {
            vote.setChoice(data.choice());
            return repository.save(vote);
        }).orElseThrow(() -> (
            new NotFoundException("The session with ID " + id + " does not exist!")
        ));

        return ResponseEntity.ok(new VoteResponseDTO(updateVote));
    }

    public ResponseEntity<VoteResponseDTO> updateCpf(Long id, VoteRequestDTO data) {
        if (data.cpf() == null) {
            throw new ParametersNotValidException("The body of vote is incorrect!");
        }

        if (data.cpf().toString().length() != 11) {
            throw new ParametersNotValidException("A CPF contains 11 numbers");
        }

        Vote updateVote = repository.findById(id).map(vote -> {
            vote.setCpf(data.cpf());
            return repository.save(vote);
        }).orElseThrow(() -> (
            new NotFoundException("The session with ID " + id + " does not exist!")
        ));

        return ResponseEntity.ok(new VoteResponseDTO(updateVote));
    }

    public ResponseEntity<VoteResponseDTO> updateSessionID(Long id, VoteRequestDTO data) {
        if (data.sessionID() == null) {
            throw new ParametersNotValidException("The body of vote is incorrect!");
        }

        Session session = sessionRepository.findById(data.sessionID()).orElseThrow(() -> (
            new NotFoundException("The session with ID " + data.sessionID() + " does not exist!")
        ));

        Vote updateVote = repository.findById(id).map(vote -> {
            vote.setSession(session);
            return repository.save(vote);
        }).orElseThrow(() -> (
            new NotFoundException("The session with ID " + id + " does not exist!")
        ));

        return ResponseEntity.ok(new VoteResponseDTO(updateVote));
    }
}
