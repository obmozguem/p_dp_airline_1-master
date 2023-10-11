package app.dto;

import app.entities.account.Account;
import app.entities.account.Role;
import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = {"roles"})
@JsonTypeName(value = "account")
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Field should not be empty")
    @Size(min = 2, max = 128, message = "Size first_name cannot be less than 2 and more than 128 characters")
    private String firstName;

    @NotBlank(message = "Field should not be empty")
    @Size(min = 2, max = 128, message = "Size last_name cannot be less than 2 and more than 128 characters")
    private String lastName;

    @NotNull(message = "Field should not be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past(message = "Date of birth can not be a future time")
    private LocalDate birthDate;

    @Email
    @NotBlank(message = "The field cannot be empty")
    private String email;

    @NotBlank(message = "Field should not be empty")
    @Size(min = 6, max = 64, message = "Size phone cannot be less than 6 and more than 64 characters")
    private String phoneNumber;

    @NotBlank(message = "The field cannot be empty")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,}", message = "min 8 characters, 1 uppercase latter" +
            "1 lowercase latter, at least 1 number, 1 special character")
    private String password;

    @NotBlank(message = "The field cannot be empty")
    private String securityQuestion;

    @NotBlank(message = "The field cannot be empty")
    private String answerQuestion;

    private Set<Role> roles;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.birthDate = account.getBirthDate();
        this.email = account.getEmail();
        this.phoneNumber = account.getPhoneNumber();
        this.password = account.getPassword();
        this.securityQuestion = account.getSecurityQuestion();
        this.answerQuestion = account.getAnswerQuestion();
        this.roles = account.getRoles();
    }
}
