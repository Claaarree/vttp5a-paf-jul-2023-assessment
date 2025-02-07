package vttp2023.batch3.assessment.paf.bookings.model;

import java.util.List;

import org.bson.Document;

import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F__ID;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_AMENITIES;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_COUNTRY;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_DESCRIPTION;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_IMAGES;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_PRICE;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_STREET;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_SUBURB;

public class ListingDetails {
    private String accomodationId;
    private String description;
    private String addressStreet;
    private String addressSuburb;
    private String addressCountry;
    private String image;
    private Double price;
    private List<String> amenities;

    public String getAccomodationId() {
        return accomodationId;
    }
    public void setAccomodationId(String accomodationId) {
        this.accomodationId = accomodationId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAddressStreet() {
        return addressStreet;
    }
    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }
    public String getAddressSuburb() {
        return addressSuburb;
    }
    public void setAddressSuburb(String addressSuburb) {
        this.addressSuburb = addressSuburb;
    }
    public String getAddressCountry() {
        return addressCountry;
    }
    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public List<String> getAmenities() {
        return amenities;
    }
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public static ListingDetails toListingDetails(Document d) {
        ListingDetails ld = new ListingDetails();

        ld.setAccomodationId(d.getString(MONGO_F__ID));
        ld.setDescription(d.getString(MONGO_F_DESCRIPTION));
        Document address = d.get("address", Document.class);
        ld.setAddressStreet(address.getString("street"));
        ld.setAddressSuburb(address.getString("suburb"));
        ld.setAddressCountry(address.getString("country"));
        Document images = d.get("images", Document.class);
        ld.setImage(images.getString("picture_url"));
        ld.setPrice(d.getDouble(MONGO_F_PRICE));
        ld.setAmenities(d.getList(MONGO_F_AMENITIES, String.class));

        return ld;
    }
}
