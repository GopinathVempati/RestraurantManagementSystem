public class TotalSales {
    String dishName;
    int dishQty;
    double costOfDish;

    public TotalSales(String dishName, int dishQty, double costOfDish) {
        this.dishName = dishName;
        this.dishQty = dishQty;
        this.costOfDish = costOfDish;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishQty() {
        return dishQty;
    }

    public void setDishQty(int dishQty) {
        this.dishQty = dishQty;
    }

    public double getCostOfDish() {
        return costOfDish;
    }

    public void setCostOfDish(double costOfDish) {
        this.costOfDish = costOfDish;
    }

    @Override
    public String toString() {
        return "dishName='" + dishName + ", dishQty=" + dishQty + ", costOfDish=" + costOfDish;
    }

    public String dishNCost(){
        return "dishName='" + dishName + ", costOfDish=" + costOfDish;
    }
}
