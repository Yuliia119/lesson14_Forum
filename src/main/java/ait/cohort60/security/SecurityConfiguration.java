package ait.cohort60.security;

import ait.cohort60.accounting.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean  // метод настраивает поведение безопасности (фильтры)
    SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());   // аутентификация по умолчанию (логин,пароль)
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(authorize -> authorize    // авторизация запросов
                .requestMatchers("/account/register", "/forum/posts/**")            // доступ для всех к регистрации и постам
                    .permitAll()
                .requestMatchers("/account/user/{login}/role/{role}")           // доступ для админа к ролям
                    .hasRole(Role.ADMINISTRATOR.name())
                .requestMatchers(HttpMethod.PATCH, "/account/user/{login}")     // доступ для юзера к аккаунту по имени
                    .access(new WebExpressionAuthorizationManager("#login == authentication.name"))
                .requestMatchers(HttpMethod.DELETE, "/account/user/{login}")        //доступ к удалению аккаунта для юзера или админа
                    .access(new WebExpressionAuthorizationManager("#login == authentication.name or hasRole('ADMINISTRATOR')"))
                .requestMatchers(HttpMethod.POST, "/forum/post/{author}")
                    .access(new WebExpressionAuthorizationManager("#author == authentication.name"))
                .requestMatchers(HttpMethod.PATCH,"/forum/post/{id}/comment/{author}")
                    .access(new WebExpressionAuthorizationManager("#author == authentication.name"))

                .anyRequest()
                    .authenticated());      //остальные запросы требуют аутентификации
        return http.build();                //возвращает конфигурацию фильтров безопасности
    }

    @Bean  //шифруем пароли пользователей перед сохранением в базу
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
