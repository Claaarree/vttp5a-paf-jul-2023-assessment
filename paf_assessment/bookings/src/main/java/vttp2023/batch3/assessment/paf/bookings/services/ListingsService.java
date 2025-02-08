package vttp2023.batch3.assessment.paf.bookings.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import vttp2023.batch3.assessment.paf.bookings.model.Listing;
import vttp2023.batch3.assessment.paf.bookings.model.ListingDetails;
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
	public ListingDetails getListingDetails(String name){
		Document details = listingsRepository.getListingDetails(name);
		System.out.println(details);
		return ListingDetails.toListingDetails(details);
	}
	

	//TODO: Task 5
	public Integer checkVacancy(String accommodationId, Integer stay) {
		Integer vacancy = listingsRepository.getVacancy(accommodationId);
		return vacancy - stay;
	}

	@Transactional
	public String makeReservation(MultiValueMap<String, String> booking, 
	String accommodationId, Integer updatedVacancy) {
		String reservationId = UUID.randomUUID().toString().substring(0, 8);
		String username = booking.getFirst("username");
		String email = booking.getFirst("email");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date arrival = null;
		try {
			arrival = sdf.parse(booking.getFirst("arrival"));
		} catch (ParseException e) {
			System.out.println("Error in parsing arrival date!");
		}

		Integer stay = Integer.parseInt(booking.getFirst("stay"));

		listingsRepository.makeReservation(reservationId, 
				username, email, accommodationId, arrival, stay);

		listingsRepository.updateVacancy(updatedVacancy, accommodationId);

		return reservationId;
	}


}
