package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.model.Listing;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@Service
public class ListingsService {
	
	@Autowired
	private ListingsRepository listingsRepository;
	
	//TODO: Task 2
	public List<String> getCountriesList() {
		return listingsRepository.getCountriesList();
	}
	
	//TODO: Task 3
	public List<Listing> getSearchListings(String country, Integer person, Double min, Double max){
		List<Document> listingDocuments = listingsRepository
				.getSearchListings(country, person, min, max);

		List<Listing> listingsList = new ArrayList<>();

		for (Document d: listingDocuments){
			// System.out.println("Document" + d);
			Listing l = Listing.toListing(d);
			listingsList.add(l);
		}

		return listingsList;
	}


	//TODO: Task 4
	public void getListingDetails(String name){
		Document details = listingsRepository.getListingDetails(name);
		System.out.println(details);
	}
	

	//TODO: Task 5


}
