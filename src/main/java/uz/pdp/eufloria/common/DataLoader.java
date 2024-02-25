package uz.pdp.eufloria.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.entity.Role;
import uz.pdp.eufloria.repository.RoleRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (ddlMode.equals("create")) {
            Role adminRole = new Role("ADMIN_ROLE");
            Role userRole = new Role("USER_ROLE");
            Role superAdminRole = new Role("SUPER_ADMIN_ROLE");
            roleRepository.saveAll(List.of(adminRole, userRole, superAdminRole));
        }
    }
}
