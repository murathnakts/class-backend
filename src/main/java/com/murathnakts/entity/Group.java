package com.murathnakts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group extends BaseEntity {

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private User creatorUser;

    @ManyToMany
    private List<User> members;
}
