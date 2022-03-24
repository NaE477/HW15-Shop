package entities.users;

import entities.products.OrderedProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@ToString
@Entity
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private Set<OrderedProduct> orderedProducts;
    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private Set<Notification> notifications;
}
