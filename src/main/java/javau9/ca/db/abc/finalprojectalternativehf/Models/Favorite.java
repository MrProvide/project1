package javau9.ca.db.abc.finalprojectalternativehf.Models;

import jakarta.persistence.*;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SimpleUser simpleUser;

    @ManyToOne
    private Product product;

    public Favorite(Long id, Product product, SimpleUser simpleUser) {
        this.id = id;
        this.product = product;
        this.simpleUser = simpleUser;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimpleUser getSimpleUser() {
        return simpleUser;
    }

    public void setSimpleUser(SimpleUser simpleUser) {
        this.simpleUser = simpleUser;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
