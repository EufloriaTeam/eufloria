package uz.pdp.eufloria.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.repository.UserRepository;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AppConstants.AUTH_HEADER_NAME);
        if (Objects.nonNull(token) &&
                token.startsWith("Bearer ")) {
            String userId = tokenProvider.claimFromToken(token);
            User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> ApiException.throwException("User not found"));

            UserPrincipal principal = UserPrincipal.create(user);
            var passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
        }
        doFilter(request, response, filterChain);
    }
}
