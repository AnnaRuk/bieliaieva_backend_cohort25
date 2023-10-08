package de.ait.mp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String title;

    private LocalDate date;

    ///M:1 "one" one venue to one event, many events to one venue
    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    //M:M with users  = extra table
    @ManyToMany(mappedBy = "userEvents")
    private Set<User> events;

}
