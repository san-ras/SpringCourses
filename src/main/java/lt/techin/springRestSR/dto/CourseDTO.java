package lt.techin.springRestSR.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import lt.techin.springRestSR.entities.Type;

import java.time.LocalDateTime;

@Data
public class CourseDTO {

    private Integer id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    @NotNull(message = "Description cannot be null")
    private String description;
    @NotNull(message = "Type cannot be null")
    private Type type;
    @NotNull(message = "Start date cannot be null")
    private LocalDateTime startDate;
    @NotNull(message = "End date cannot be null")
    private LocalDateTime endDate;
}
