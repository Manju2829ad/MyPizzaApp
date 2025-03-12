package basepackage.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private Long lid;  // Optional, if needed for logging purposes

    private LocalDateTime localDateTime;

    private String username; // This can be an email or a mobile number

    private String password; // User's password for authentication
}
