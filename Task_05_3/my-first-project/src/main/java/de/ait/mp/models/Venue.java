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
@EqualsAndHashCode(exclude = "eventsThisVenue")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String venueName;
    private String address;
    private double square;

    //M:1 "many" with event, one venue to one event, many events to one venue
    @OneToMany(mappedBy = "venue")
    private Set<Event> eventsThisVenue;
}
