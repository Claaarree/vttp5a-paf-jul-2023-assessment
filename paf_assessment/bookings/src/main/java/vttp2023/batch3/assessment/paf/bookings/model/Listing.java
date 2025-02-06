package vttp2023.batch3.assessment.paf.bookings.model;

import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_NAME;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_PRICE;
import org.bson.Document;

public class Listing {
    private String name;
    private Double price;
    private String image;
    private String street;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public static Listing toListing(Document d){
        Listing l = new Listing();
        l.setName(d.getString(MONGO_F_NAME));
        l.setPrice(d.getDouble(MONGO_F_PRICE));
        l.setImage(d.getString("picture_url"));
        l.setStreet(d.getString("street"));

        return l;
    }
}
