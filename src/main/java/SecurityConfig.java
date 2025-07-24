//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Configura CORS
//                .csrf(csrf -> csrf.disable())  // Desabilita CSRF (porque estamos trabalhando com um REST simples)
//                .formLogin().disable()  // Desabilita login via formulário
//                .httpBasic().disable()  // Desabilita autenticação básica
//                .authorizeRequests()
//                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // Permite requisições OPTIONS (pré-vôo)
//                .requestMatchers("/login/authentic").permitAll()  // Permite acesso ao endpoint de login
//                .anyRequest().permitAll();  // Permite todas as outras requisições sem autenticação
//
//        return http.build();
//    }
//
//    // Configuração CORS
//    private CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.addAllowedOrigin("http://localhost:5173");  // Permite o frontend
//        corsConfig.addAllowedMethod("*");  // Permite todos os métodos (GET, POST, OPTIONS, etc.)
//        corsConfig.addAllowedHeader("*");  // Permite todos os cabeçalhos
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);  // Aplica a configuração CORS a todos os endpoints
//        return source;
//    }
//}
