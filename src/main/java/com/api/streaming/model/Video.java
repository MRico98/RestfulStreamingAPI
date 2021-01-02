package com.api.streaming.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "videos")
public class Video {

    @Id
    @Column(name = "id_video")
    private String id;

    @Column
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor")
    @JsonManagedReference
    private User autor;

    @Column(name = "average_rating")
    private float rating;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Lob
    @Column(name = "video_location")
    private String locacion;

}