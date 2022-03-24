package entities.users;

import entities.base.BaseEntity;
import entities.products.Product;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
@Entity
public class Notification extends BaseEntity {
    @ManyToOne
    @ToString.Exclude
    private Customer customer;
    @ManyToOne
    @ToString.Exclude
    private Product product;

    private String message;
    private Boolean isRead;
}
