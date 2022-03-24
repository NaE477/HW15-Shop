package entities.users;

import entities.products.OrderedProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@Entity
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    private Set<OrderedProduct> orderedProducts;
    @OneToMany(mappedBy = "customer")
    private Set<Notification> notifications;
}
