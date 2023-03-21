package comparators;

import Objects.Item;

public class CustomComparatorType implements java.util.Comparator<Objects.Item> {

    @Override
    public int compare(Item o1, Item o2) {

        return (int)o1.getPrice() - (int)o2.getPrice();
    }
}
