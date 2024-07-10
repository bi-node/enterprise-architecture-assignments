package sender.springconfigurationpartc;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Validated
@ConfigurationProperties(prefix = "custom")
public class DemoProperties {
    @NotBlank
    private String applicationName;
    @NotBlank
    private String version;
    @NotBlank
    private String url;
    private String serverName;
    private User user = new User();
    private List<String> countries;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Valid
    static class
    User{

        private String firstname;
        private String lastname;

        @Size(max=15, min=8)
        private String username;

        @Size(max=15, min=8)
        private String password;
    }
}
