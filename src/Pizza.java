package gui;
public enum Pizza {
    Margherita(200),
    Farmhouse(300),
    PeppyPaneer(350),
    VegExtravaganza(400);

    private final int basePrice;

    Pizza(int price) {
        this.basePrice = price;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
