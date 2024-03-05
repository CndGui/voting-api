package com.guilhermerodrigues.votingapi.entity;

import com.guilhermerodrigues.votingapi.dto.TopicRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Topic(TopicRequestDTO data) {
        this.content = data.content();
    }
}