package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import vttp2023.batch3.assessment.paf.bookings.model.Listing;
import vttp2023.batch3.assessment.paf.bookings.model.ListingDetails;
import vttp2023.batch3.assessment.paf.bookings.model.Listings;
import vttp2023.batch3.assessment.paf.bookings.services.ListingsService;

@Controller
public class ListingsController {

	@Autowired
	private ListingsService listingsService;

	//TODO: Task 2
	@GetMapping
	public ModelAndView showLandingPage() {
		ModelAndView mav = new ModelAndView("view1");
		List<String> countriesList = listingsService.getCountriesList();
		mav.addObject("countries", countriesList);

		return mav;
	}
	
	//TODO: Task 3
	@GetMapping("/search")
	public ModelAndView showSearch(@RequestParam (required = false) MultiValueMap<String, String> params,
	HttpSession session){
		ModelAndView mav = new ModelAndView();
		if(params.isEmpty()){
			Listings listings = (Listings)session.getAttribute("listings");
			String c = (String)session.getAttribute("country");
			mav.addObject("listings", listings.getListingsList());
			mav.addObject("country", c);
			mav.setViewName("view2");
		} else{
			List<String> validation = new ArrayList<>();
			String country = params.getFirst("country");
			Integer persons = Integer.parseInt(params.getFirst("noOfPerson"));
			Double min = Double.parseDouble(params.getFirst("min"));
			Double max = Double.parseDouble(params.getFirst("max"));
	
			if(country.isEmpty()){
				validation.add("Please select a country!");
			}
			if(persons < 1 || persons >10) {
				validation.add("Please enter the number of persons from 1 to 10!");
			}
			if(min < 1 || max > 10000 || min > max){
				validation.add("Please enter a valid price range between 1 and 10000!");
			}
	
			if(!validation.isEmpty()){
				mav.setViewName("view1");
				mav.addObject("countries", listingsService.getCountriesList());
				mav.addObject("errors", validation);
			} else {
				mav.setViewName("view2");
				List<Listing> listingsList = listingsService.getSearchListings(country, persons, min, max);
				mav.addObject("listings", listingsList);
				mav.addObject("country", country);
				Listings listingsResults = new Listings(listingsList);
				session.setAttribute("listings", listingsResults);
				session.setAttribute("country", country);
			}
		}

		return mav;
	}

	//TODO: Task 4
	@GetMapping("/listing/details/{name}")
	public ModelAndView getListingDetails(@PathVariable String name){
		ModelAndView mav = new ModelAndView("view3");
		System.out.println(name);
		ListingDetails ld = listingsService.getListingDetails(name);
		mav.addObject("details", ld);

		return mav;
	}
	

	//TODO: Task 5


}
