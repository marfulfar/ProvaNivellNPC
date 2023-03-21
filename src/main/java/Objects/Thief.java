package Objects;

import java.util.ArrayList;
import java.util.List;

public class Thief implements Vendor{

    private String name;
    private final float TAXES = 0.00f;
    private List<Item> stock = new ArrayList<>(3);

    private final float DAMAGE = 0.25f;

    private static final int STOCK_LIMIT = 3;



    public Thief(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTAXES() {
        return TAXES;
    }

    public List<Item> getStock() {
        return stock;
    }

    @Override
    public int getStockLimit() {
        return STOCK_LIMIT;
    }

    public void setStock(List<Item> stock) {
        this.stock = stock;
    }


    @Override
    public String toString() {
        return "Merchant{" +
                "name='" + name + '\'' +
                ", TAXES=" + TAXES +
                ", stock=" + stock +
                '}';
    }

    @Override
    public void sellOneItem(Item item) {
        float finalPrice = item.getPrice() + (item.getPrice() * TAXES);
        float finalDamage = item.getHealth() - (item.getHealth() * DAMAGE);
        stock.add(new Item(item.getName(), item.getType(), finalPrice,finalDamage));
    }

    @Override
    public void buyOneItem() {

    }
}
