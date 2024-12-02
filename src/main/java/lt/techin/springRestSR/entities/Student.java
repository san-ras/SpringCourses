package lt.techin.springRestSR.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String address;
    private String phoneNo;
    private LocalDateTime enrollmentDate;
    private LocalDateTime graduationDate;
    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private List<Course> courses;
}
