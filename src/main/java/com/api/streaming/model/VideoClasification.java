package com.api.streaming.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "videos_clasifications")
public class VideoClasification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_videos_clasifications")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_video")
    @JsonManagedReference
    private Video video;

    @Column
    private String tag;

}
