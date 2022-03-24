package entities.users;

import entities.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
public abstract class User extends BaseEntity {
    @Column(nullable = false
            , unique = true)
    private String username;
    @Column(nullable = false)
    @ToString.Exclude
    private String password;
}
