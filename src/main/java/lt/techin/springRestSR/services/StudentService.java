package lt.techin.springRestSR.services;

import jakarta.validation.Valid;
import lt.techin.springRestSR.dto.StudentDTO;
import lt.techin.springRestSR.entities.Course;
import lt.techin.springRestSR.entities.Student;
import lt.techin.springRestSR.exceptions.StudentCanNotEnrollException;
import lt.techin.springRestSR.repositories.CourseRepository;
import lt.techin.springRestSR.repositories.StudentRepository;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public void enrollToCourse(@Valid Integer courseId, StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.getId()).get();
        Course course = courseRepository.findById(courseId).get();
        if (student.getCourses().size() >= 3) {
            throw new StudentCanNotEnrollException("This student has already enrolled to max number of courses");

        } else if (student.getCourses().contains(course)) {
            throw new StudentCanNotEnrollException("This student is already enrolled to this course");
        }
        student.getCourses().add(course);
        studentRepository.save(student);
        course.getStudents().add(student);
        courseRepository.save(course);
    }

    public void removeFromCourse(@Valid Integer courseId, StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.getId()).get();
        Course course = courseRepository.findById(courseId).get();
        student.getCourses().remove(course);
        studentRepository.save(student);
        course.getStudents().remove(student);
        courseRepository.save(course);
    }
}
