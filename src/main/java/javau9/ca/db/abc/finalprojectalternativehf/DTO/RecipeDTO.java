package javau9.ca.db.abc.finalprojectalternativehf.DTO;

import java.util.List;

public class RecipeDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> productIds;


    public RecipeDTO() {
    }

    public RecipeDTO(String description, List<Long> productIds, String name) {
        this.description = description;
        this.productIds = productIds;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
