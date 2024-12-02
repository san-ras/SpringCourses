package lt.techin.springRestSR.controllers;

import jakarta.validation.Valid;
import lt.techin.springRestSR.dto.StudentDTO;
import lt.techin.springRestSR.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{id}/enrollments")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public void enrollToCourse(@Valid @PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
        studentService.enrollToCourse(id, studentDTO);
    }

    @DeleteMapping()
    public void removeFromCourse(@Valid @PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
        studentService.removeFromCourse(id, studentDTO);
    }


}
