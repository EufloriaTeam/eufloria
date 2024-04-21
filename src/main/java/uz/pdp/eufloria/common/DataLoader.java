package uz.pdp.eufloria.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.entity.Image;
import uz.pdp.eufloria.entity.Role;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.repository.ImageRepository;
import uz.pdp.eufloria.repository.RoleRepository;
import uz.pdp.eufloria.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    private static Image defaultPlantImage;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Override
    public void run(String... args) {
        if (ddlMode.equals("create")) {
            Role adminRole = new Role("ADMIN_ROLE");
            Role userRole = new Role("USER_ROLE");
            Role superAdminRole = new Role("SUPER_ADMIN_ROLE");

            User superAdminUser = new User("Rakhim", "rakhimdesanta@gmail.com", passwordEncoder.encode("12313Ab"), null, null, null,null, null, superAdminRole);
            User adminUser = new User("Rakhim", "rakhim.des@gmail.com", passwordEncoder.encode("123123Ab"), null, null, null,null, null, adminRole);
            User user = new User("Rakhim", "santaclousx1@gmail.com", passwordEncoder.encode("12323Ab"), null, null, null,null, null, userRole);
            adminUser.setId(UUID.randomUUID());
            superAdminUser.setId(UUID.randomUUID());
            user.setId(UUID.randomUUID());
            roleRepository.saveAll(List.of(adminRole, userRole, superAdminRole));
            userRepository.save(superAdminUser);
            userRepository.save(adminUser);
            userRepository.save(user);
        } else {
            defaultPlantImage = imageRepository.findByName("default-plant").get(0);
        }
    }

    public static Image getDefaultPlantImage() {
        return defaultPlantImage;
    }
}
