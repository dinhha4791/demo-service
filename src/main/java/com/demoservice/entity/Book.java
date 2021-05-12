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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", nullable = false)})
    @EqualsAndHashCode.Exclude
    private Set<Author> authors = new HashSet();
}