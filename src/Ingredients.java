public class Ingredients {
    String ingredientName;
    double quantity;

    public Ingredients(String ingredientName, double quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
