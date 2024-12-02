package lt.techin.springRestSR.repositories;

import lt.techin.springRestSR.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
