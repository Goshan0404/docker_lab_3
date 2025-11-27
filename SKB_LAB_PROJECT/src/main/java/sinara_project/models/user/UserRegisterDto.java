package sinara_project.models.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sinara_project.validation.MailConstraint;

@Data
public class UserRegisterDto {

    @NotBlank(message = "{user.validation.name.message}")
    @Size(min = 2, max = 10, message = "{user.validation.name.message}")
    private String name;

    @MailConstraint
    private String email;

    @NotBlank(message = "{user.validation.password.message}")
    @Size(min = 5, max = 10, message = "{user.validation.password.message}")
    private String password;
}
