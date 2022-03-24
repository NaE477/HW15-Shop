package entities.products;

import entities.base.BaseEntity;
import entities.users.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter @Setter @NoArgsConstructor
@Entity
public class OrderedProduct extends BaseEntity {
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;
    private Integer quantity;
    private Boolean isFinalized;
}
