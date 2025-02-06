package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_C_LISTINGS;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_ACCOMMODATES;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_AMENITIES;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_COUNTRY;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_DESCRIPTION;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_IMAGES;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_NAME;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_PRICE;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_STREET;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F_SUBURB;
import static vttp2023.batch3.assessment.paf.bookings.utils.Constants.MONGO_F__ID;

@Repository
public class ListingsRepository {

	@Autowired
	private MongoTemplate template;

	//TODO: Task 2
	// db.listings.distinct('address.country')
	public List<String> getCountriesList() {
		List<String> countriesList = template.findDistinct(new Query(), MONGO_F_COUNTRY, MONGO_C_LISTINGS, String.class);
		
		return countriesList;
	}

	//TODO: Task 3
	// db.listings.aggregate([
    // {$match: {
    //     'address.country':{$regex: 'australia', $options: "i"},
    //     accommodates: 2,
    //     price: {$lte: 200, $gte: 100}}
    // },
    // {$project: {
    //     _id:0, name:1, 'address.street':1, price:1, "images.picture_url":1
    //     }
    // },
    // {$sort: {price: -1}}
	// ])
	public List<Document> getSearchListings(String country, Integer person, Double min, Double max){
		Criteria criteria = Criteria.where(MONGO_F_COUNTRY)
				.regex(country, "i")
				.and(MONGO_F_ACCOMMODATES).is(person)
				.and(MONGO_F_PRICE).gte(min).lte(max);
		MatchOperation searchMatch = Aggregation.match(criteria);

		ProjectionOperation showRequiredAttributes = Aggregation
				.project(MONGO_F_PRICE, MONGO_F_NAME, MONGO_F_STREET, MONGO_F_IMAGES)
				.andExclude("_id");

		SortOperation sortByPrice = Aggregation.sort(Direction.DESC, MONGO_F_PRICE);

		Aggregation pipeline = Aggregation
				.newAggregation(searchMatch, showRequiredAttributes,sortByPrice);

		AggregationResults<Document> results = template
				.aggregate(pipeline, MONGO_C_LISTINGS, Document.class);

		return results.getMappedResults();
	}


	//TODO: Task 4
	// db.listings.find(
    // {name: 'Sydney Hyde Park City Apartment (checkin from 6am)'}
	// ).projection(
    //     {_id:1, description: 1, 
    //     'address.street':1, 
    //     'address.suburb': 1,
    //     'address.country':1,
    //     "images.picture_url":1, 
    //     price:1, 
    //     amenities:1
    //     }
	// )
	public Document getListingDetails(String name){
		Criteria criteria = Criteria.where("name")
				.is(name);
		Query query = Query.query(criteria);

		query.fields().include(
				MONGO_F__ID, MONGO_F_DESCRIPTION,
				MONGO_F_STREET,MONGO_F_SUBURB,
				MONGO_F_COUNTRY, MONGO_F_IMAGES,
				MONGO_F_PRICE, MONGO_F_AMENITIES);

		Document details = template
				.findOne(query, Document.class, MONGO_C_LISTINGS);
		
		return details;
	}
	

	//TODO: Task 5


}
