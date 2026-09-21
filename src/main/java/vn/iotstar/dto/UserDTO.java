package vn.iotstar.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String email;
    private String fullName;
    private String images;
    private String roleName;
    private boolean enabled;
}
