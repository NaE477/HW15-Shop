package entities.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter @Setter @NoArgsConstructor
@ToString
@Entity
public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
}
