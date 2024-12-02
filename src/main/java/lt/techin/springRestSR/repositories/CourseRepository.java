package lt.techin.springRestSR.repositories;

import lt.techin.springRestSR.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
