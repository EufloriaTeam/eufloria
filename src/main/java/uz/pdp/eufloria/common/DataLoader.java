package uz.pdp.eufloria.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.entity.Role;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.repository.RoleRepository;
import uz.pdp.eufloria.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (ddlMode.equals("create")) {
            Role adminRole = new Role("ADMIN_ROLE");
            Role userRole = new Role("USER_ROLE");
            Role superAdminRole = new Role("SUPER_ADMIN_ROLE");

            User superAdminUser = new User("Rakhim", "rakhimdesanta@gmail.com", "12313Ab", null, null, null,null, null, superAdminRole);
            User adminUser = new User("Rakhim", "rakhim.des@gmail.com", "12313Ab", null, null, null,null, null, adminRole);
            User user = new User("Rakhim", "santaclousx1@gmail.com", "12313Ab", null, null, null,null, null, userRole);
            adminUser.setId(UUID.randomUUID());
            superAdminUser.setId(UUID.randomUUID());
            user.setId(UUID.randomUUID());
            roleRepository.saveAll(List.of(adminRole, userRole, superAdminRole));
            userRepository.save(superAdminUser);
            userRepository.save(adminUser);
            userRepository.save(user);
        }
    }
}
