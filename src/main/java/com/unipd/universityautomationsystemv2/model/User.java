package com.unipd.universityautomationsystemv2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "users")
public class
User extends BaseEntity{

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @GeneratedValue(generator = "uuid2")
    private Long id;

    @Column(name = "first_name",nullable = false)
    private  String firstName;

    @Column(name = "last_name",nullable = false)
    private  String lastName;

    @Column(name = "email",nullable = false)
    private  String email;

    @Column
    private UUID guid ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToMany(mappedBy = "UsersMap")
    @JsonIgnore
    Set<Course> coursesMap;

}
