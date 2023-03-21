package Objects;

import java.util.List;

public interface Vendor {

    void sellOneItem(Item item);

    void buyOneItem();

    String getName();

    List<Item> getStock();

    int getStockLimit();

}
