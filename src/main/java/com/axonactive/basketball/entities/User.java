package com.axonactive.basketball.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username;
    @JsonIgnore
    private String password;
    @OneToMany(mappedBy = "users")
    private List<UserRoleAssignment> roles = new ArrayList<>();
    @JoinColumn
    @ManyToOne
    private Owner owner;
}
