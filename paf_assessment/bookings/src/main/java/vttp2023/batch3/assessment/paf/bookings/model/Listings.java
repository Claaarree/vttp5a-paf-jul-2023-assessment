package vttp2023.batch3.assessment.paf.bookings.model;

import java.util.List;

public class Listings {
    private List<Listing> listingsList;

    public Listings(List<Listing> listingsList) {
        this.listingsList = listingsList;
    }

    public List<Listing> getListingsList() {
        return listingsList;
    }

    public void setListingsList(List<Listing> listingsList) {
        this.listingsList = listingsList;
    }

    
}
