package com.guilhermerodrigues.votingapi.entity;

import com.guilhermerodrigues.votingapi.dto.TopicRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "topic")
    private List<Session> sessions = new ArrayList<>();

    public Topic(TopicRequestDTO data) {
        this.content = data.content();
    }
}