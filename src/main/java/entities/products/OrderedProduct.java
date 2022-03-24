package entities.products;

import entities.base.BaseEntity;
import entities.users.Customer;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
@Entity
public class OrderedProduct extends BaseEntity {
    @ManyToOne
    @ToString.Exclude
    private Customer customer;
    @ManyToOne
    private Product product;
    private Integer quantity;
    private Boolean isFinalized;
}
