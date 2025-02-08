package vttp2023.batch3.assessment.paf.bookings.utils;

public class Constants {
    
    public static final String MONGO_C_LISTINGS = "listings";

    public static final String MONGO_F_COUNTRY = "address.country";
    public static final String MONGO_F_ACCOMMODATES = "accommodates";
    public static final String MONGO_F_PRICE = "price";
    public static final String MONGO_F_NAME = "name";
    public static final String MONGO_F_STREET = "address.street";
    public static final String MONGO_F_IMAGES = "images.picture_url";
    public static final String MONGO_F__ID = "_id";
    public static final String MONGO_F_DESCRIPTION = "description";
    public static final String MONGO_F_SUBURB = "address.suburb";
    public static final String MONGO_F_AMENITIES = "amenities";

    public static final String MYSQL_CHECK_VACANCY = """
        select * from acc_occupancy
                where acc_id = ?;
        """;

    public static final String MYSQL_INSERT_RESERVATION = """
        INSERT into reservations (resv_id, name, email, acc_id, arrival_date, duration)
            values (?, ?, ?, ?, ?, ?);
        """;

    public static final String MYSQL_UPDATE_VACANCY = """
        update acc_occupancy SET vacancy = ?
	        where acc_id = ?;
        """;
}
