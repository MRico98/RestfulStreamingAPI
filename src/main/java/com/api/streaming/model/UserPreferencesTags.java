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
@Table(name = "user_preferences_tags")
public class UserPreferencesTags {

    @Id
    @Column(name = "id_user")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL , optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "tag")
    private String tag;

}
