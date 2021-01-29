package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Sculpture")
public class Sculpture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sculpture_id")
    private Long id;

    @Column
    private String author;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String criticalDescription;

}

