package de.ait.ec.controllers;

import de.ait.ec.dto.CourseDto;
import de.ait.ec.dto.NewCourseDto;
import de.ait.ec.services.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@Tags(value = @Tag(name = "Courses"))
public class CoursesController {


    private final CoursesService coursesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    @Operation(summary = "Adding a course", description = "Available to system administrator")
    public CourseDto addCourse(@RequestBody NewCourseDto newCourseDto){
    return coursesService.addCourse(newCourseDto);
    }


    @GetMapping
    @Operation(summary = "Getting all users", description = "Available to everybody")
    public List<CourseDto> getCourses(){
        return coursesService.getCourses();
    }

}
