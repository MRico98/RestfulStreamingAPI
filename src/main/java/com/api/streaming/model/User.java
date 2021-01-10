package com.api.streaming.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private UserPreferencesTags userPreferencesTags;
    
    @Column
    private String name;

    @Column
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "role")
    @JsonManagedReference
    private Role role;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "autor")
    @JsonBackReference
    private List<Video> videos;
}
