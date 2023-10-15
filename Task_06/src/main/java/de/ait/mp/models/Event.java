package de.ait.mp.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String title;

    private LocalDate date;

    ///M:1 "one" one venue to one event, many events to one venue
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    //M:M with users  = extra table
    @ManyToMany(mappedBy = "userEvents")
    private Set<User> events;

}
