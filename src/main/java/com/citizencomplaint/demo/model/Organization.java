package com.citizencomplaint.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "organizations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Replace Location and Category with appropriate entities (such as City and Category)
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private BangaloreLocation city;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @PrePersist
    public void prePersist() {
        // Additional logic before persisting if necessary
    }
}
