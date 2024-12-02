package lt.techin.springRestSR.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDTO {

    @NotNull
    private Integer id;
}
