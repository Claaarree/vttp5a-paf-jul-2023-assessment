package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_C_LISTINGS;

@Repository
public class ListingsRepository {

	@Autowired
	private MongoTemplate template;

	// db.listings.distinct('address.country')
	//TODO: Task 2
	public List<String> getCountriesList() {
		List<String> countriesList = template.findDistinct(new Query(), "address.country", MONGO_C_LISTINGS, String.class);
		
		return countriesList;
	}
	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
