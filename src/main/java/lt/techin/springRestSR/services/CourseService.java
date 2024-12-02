package lt.techin.springRestSR.services;

import lombok.AllArgsConstructor;
import lt.techin.springRestSR.dto.CourseDTO;
import lt.techin.springRestSR.entities.Course;
import lt.techin.springRestSR.entities.Student;
import lt.techin.springRestSR.entities.Type;
import lt.techin.springRestSR.exceptions.CourseNotFoundException;
import lt.techin.springRestSR.exceptions.InvalidCourseDetailsException;
import lt.techin.springRestSR.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public CourseDTO addCourse(CourseDTO newCourseDTO) {
        if (courseDataIsNotValid(newCourseDTO)) {
            throw new InvalidCourseDetailsException("Invalid course info: start date should be before end date, end date should be in future and Type should LIVE or ONLINE.");
        }
        Course course = convertToEntity(newCourseDTO);
        courseRepository.save(course);
        return convertToDTO(course);
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> books = courseRepository.findAll();
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteCourse(Integer id) {
        Course bookToDelete = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with specified id does not exist"));
        courseRepository.delete(bookToDelete);
    }

    public CourseDTO updateCourse(CourseDTO updatedCourseDTO, Integer id) {
        if (courseDataIsNotValid(updatedCourseDTO)) {
            throw new InvalidCourseDetailsException("Invalid course details");
        }
        Course courseWithUpdatedDetails = convertToEntity(updatedCourseDTO);
        Course courseToUpdate = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with specified id does not exist"));
        courseToUpdate.setId(id);
        courseToUpdate.setDescription(courseWithUpdatedDetails.getDescription());
        courseToUpdate.setName(courseWithUpdatedDetails.getName());
        courseToUpdate.setType(courseWithUpdatedDetails.getType());
        courseToUpdate.setEndDate(courseWithUpdatedDetails.getEndDate());
        courseToUpdate.setStartDate(courseWithUpdatedDetails.getStartDate());

        courseRepository.save(courseToUpdate);

        return convertToDTO(courseToUpdate);
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setType(course.getType());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setStartDate(course.getStartDate());
        courseDTO.setEndDate(course.getEndDate());
        return courseDTO;
    }

    private Course convertToEntity(CourseDTO newCourseDTO) {
        Course course = new Course();
        course.setId(newCourseDTO.getId());
        course.setName(newCourseDTO.getName());
        course.setDescription(newCourseDTO.getDescription());
        course.setType(newCourseDTO.getType());
        course.setStartDate(newCourseDTO.getStartDate());
        course.setEndDate(newCourseDTO.getEndDate());
        return course;
    }

    private boolean courseDataIsNotValid(CourseDTO newCourseDTO) {
        return newCourseDTO.getStartDate().isBefore(newCourseDTO.getEndDate())
                && newCourseDTO.getEndDate().isAfter(LocalDateTime.now())
                && (newCourseDTO.getType().equals(Type.LIVE) || newCourseDTO.getType().equals(Type.ONLINE));
    }

    public CourseDTO getCourseById(int id) {
        return convertToDTO(courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with specified id does not exist")));

    }

    public List<Student> getAllStudentsByCourseId(Integer id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course with specified id does not exist"));
        return course.getStudents();
    }
}
