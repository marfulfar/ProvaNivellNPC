package Objects;

public class Item {

    private String name;
    private String type;
    private float price;
    private static float health = 100f;

    public Item(String name, String type, float price,float health) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }


    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", damage=" + health +
                '}';
    }



}
