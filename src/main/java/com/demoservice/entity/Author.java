package com.demoservice.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "EMAIL")
    @Email
    private String email;

    @Column(name = "PHONE_NUMBER")
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "INFORMATION")
    private String information;

    @ManyToMany(mappedBy = "authors",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private Set<Book> books = new HashSet<>();
}
