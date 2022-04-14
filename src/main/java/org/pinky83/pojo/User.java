package org.pinky83.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class User extends BaseEntity {

    String name;

    private String password;

    private String email;

    private boolean enabled = true;

    private LocalDate registered = LocalDate.now();

    private Set<Role> roles;
}
