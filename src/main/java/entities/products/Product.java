package entities.products;

import entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {
    private String productName;
    private String description;
    private Integer inStock;

    @OneToMany(mappedBy = "product")
    Set<OrderedProduct> orderedProducts;

    @Override
    public String toString() {
        return "Product{" +
                "ID: " + super.getId() + '\'' +
                "productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", inStock=" + inStock +
                '}';
    }
}
