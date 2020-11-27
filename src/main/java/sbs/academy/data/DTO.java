package sbs.academy.data;

public class DTO {

    private UserOrder userOrder;
    private Product product;

    public DTO() {
    }

    public DTO(UserOrder userOrder, Product product) {
        this.userOrder = userOrder;
        this.product = product;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public Product getProducts() {
        return product;
    }

    public void setProducts(Product product) {
        this.product = product;
    }
}
