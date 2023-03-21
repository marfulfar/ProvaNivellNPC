package Objects;

import java.util.ArrayList;
import java.util.List;

public class City {

    private String name;

    private List<Vendor> vendors = new ArrayList<>();


    public City(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }


    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }


}
