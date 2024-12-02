package lt.techin.springRestSR.controllers;

import jakarta.validation.Valid;
import lt.techin.springRestSR.dto.CourseDTO;
import lt.techin.springRestSR.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    private final CourseService courseService;

    @Autowired
    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@Valid @RequestBody CourseDTO newCourseDTO) {
        CourseDTO courseDTO = courseService.addCourse(newCourseDTO);
        return new ResponseEntity<>(courseDTO, HttpStatus.CREATED);
    }

    @GetMapping()
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO updatedCourseDTO, @PathVariable Integer id) {
        CourseDTO bookDTO = courseService.updateCourse(updatedCourseDTO, id);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }




}
