package ait.cohort60.accounting.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter

public class RolesDto {
    private String login;
    @Singular
    private Set<String> roles;

}
