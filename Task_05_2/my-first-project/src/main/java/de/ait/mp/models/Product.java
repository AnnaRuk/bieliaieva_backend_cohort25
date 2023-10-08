package de.ait.mp.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Product {

   public enum State {
       DRAFT,AVAILABLE, TEMPORARILY_UNAVAILABLE,OUTDATED
   }
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false,length = 50)
   private String productName;

   @Column(nullable = false)
   private int categoryId;

   private LocalDate expirationDate;

   @Column(nullable = false,length = 5000)
   private String description;

   @Column(nullable = false)
   //@Check(constraints = "price > 0")
   private Double price;

   @Enumerated(value = EnumType.STRING)
   //@Check(constraints = "state in ('', '',''...)")
   private State state;

/*
{
"productName": "new product",
"CategoryID": 2,
"expirationDate": "02.02.2024",
"description": "Description of the new product",
"price": 100.0
}
 */


}
