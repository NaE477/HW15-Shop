package entities.products;

import entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@Entity
public class Product extends BaseEntity {
    private String productName;
    private String description;
    private Integer inStock;

    @OneToMany(mappedBy = "product")
    Set<OrderedProduct> orderedProducts;
}
