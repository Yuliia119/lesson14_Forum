package ait.cohort60.security;

import ait.cohort60.accounting.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomWebSecurity webSecurity;

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
                .requestMatchers(HttpMethod.POST, "/forum/post/{author}")       // добавление поста автором
                    .access(new WebExpressionAuthorizationManager("#author == authentication.name"))
                .requestMatchers(HttpMethod.PATCH,"/forum/post/{id}/comment/{author}")      // изменение коментария автором
                    .access(new WebExpressionAuthorizationManager("#author == authentication.name"))
                .requestMatchers(HttpMethod.PATCH, "/forum/post/{id}")          // изменение поста только по id
                    .access(((authentication, context) ->
                        new AuthorizationDecision(webSecurity.checkPostAuthor(context.getVariables().get("id"), authentication.get().getName()))))
                .requestMatchers(HttpMethod.DELETE, "/forum/post/{id}")     // удаление поста только по id
                    .access((authentication, context) -> {
                    boolean isAuthor = webSecurity.checkPostAuthor(context.getVariables().get("id"),
                            authentication.get().getName());
                    boolean isModerator = context.getRequest().isUserInRole(Role.MODERATOR.name());
                    return new AuthorizationDecision(isAuthor || isModerator);
                })
                .anyRequest()
                    .authenticated());      //остальные запросы требуют аутентификации
        return http.build();                //возвращает конфигурацию фильтров безопасности
    }

    @Bean  //шифруем пароли пользователей перед сохранением в базу
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
