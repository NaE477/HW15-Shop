package entities.users;

import entities.base.BaseEntity;
import entities.products.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Notification extends BaseEntity {
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;

    private String message;
    private Boolean isRead;
}
