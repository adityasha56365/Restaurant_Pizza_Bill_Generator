package gui;
public class PriceCalculator {
    public static int calculateTotal(Order order) {
        int total = order.getPizza().getBasePrice();
        if (order.hasExtraCheese()) total += 50;
        if (order.hasExtraToppings()) total += 70;
        if (order.hasColdDrink()) total += 40;
        return total;
    }
}
