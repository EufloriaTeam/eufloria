package uz.pdp.eufloria.common;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.security.UserPrincipal;
import uz.pdp.eufloria.entity.User;

@Component
public class CommonUtils {

    public static User getCurrentUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser();
    }
}
