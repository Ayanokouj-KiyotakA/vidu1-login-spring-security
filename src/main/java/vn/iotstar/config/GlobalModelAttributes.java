package vn.iotstar.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import vn.iotstar.dto.UserDTO;
import vn.iotstar.service.UserService;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {

    private final UserService userService;

    @ModelAttribute("currentUser")
    public UserDTO currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return null;
        }
        return userService.findByEmail(auth.getName());
    }
}
