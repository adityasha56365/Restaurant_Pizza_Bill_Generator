package gui;
public class Order {
    private Pizza pizza;
    private boolean extraCheese;
    private boolean extraToppings;
    private boolean coldDrink;

    public Order(Pizza pizza, boolean extraCheese, boolean extraToppings, boolean coldDrink) {
        this.pizza = pizza;
        this.extraCheese = extraCheese;
        this.extraToppings = extraToppings;
        this.coldDrink = coldDrink;
    }

    public Pizza getPizza() {
        return pizza; }
    public boolean hasExtraCheese() {
        return extraCheese; }
    public boolean hasExtraToppings() {
        return extraToppings; }
    public boolean hasColdDrink() {
        return coldDrink; }
}