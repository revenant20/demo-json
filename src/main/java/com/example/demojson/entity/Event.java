package com.example.demojson.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "event_id")
    private String id;

    @Column(name = "payload")
    private String payload;

    // getters, setters
}
