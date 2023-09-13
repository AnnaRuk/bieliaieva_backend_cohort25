package de.ait.task_03.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Schema(name = "Event" , description = "info+id")
public class Event {
    @Schema(description = "id" , example = "1")
    private Long id;
    @Schema(description = "todo" , example = "QA hw lesson_08")
    private String title;
    @Schema(description = "description" , example = "add Method")
    private String description;




    public Event(String title, String description) {
        this.title = title;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
