package de.ait.ec.services.impl;

import de.ait.ec.dto.CourseDto;
import de.ait.ec.dto.NewCourseDto;
import de.ait.ec.models.Course;
import de.ait.ec.repositories.CoursesRepository;
import de.ait.ec.services.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;
    @Override

    //TODO
    public CourseDto addCourse(NewCourseDto newCourse) {
        Course course = Course.builder()
                .title(newCourse.getTitle())
                .description(newCourse.getDescription())
                .price(newCourse.getPrice())
                .beginDate(LocalDate.parse(newCourse.getBeginDate()))
                .endDate(LocalDate.parse(newCourse.getEndDate()))
                .state(Course.State.DRAFT)
                .build();
        coursesRepository.save(course);

        return CourseDto.from(course);
    }

    @Override
    public List<CourseDto> getCourses() {
       List<Course> courses =  coursesRepository.findAll();
        return CourseDto.from(courses);
    }
}
