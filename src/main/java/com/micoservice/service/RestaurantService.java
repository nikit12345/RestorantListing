package com.micoservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.micoservice.dto.RestaurantDTO;
import com.micoservice.entity.Restaurant;
import com.micoservice.repository.RestaurantRepo;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepo restaurantRepo;

	public List<RestaurantDTO> findAllRestaurants() {
		List<Restaurant> restaurants = restaurantRepo.findAll();

		List<RestaurantDTO> restaurantDTOList = restaurants.stream()
				.map(restaurant -> RestaurantDTO.builder().name(restaurant.getName()).id(restaurant.getId()).city(restaurant.getCity())
						.address(restaurant.getAddress()).restaurantDescription(restaurant.getRestaurantDescription())
						.build())
				.collect(Collectors.toList());
		return restaurantDTOList;
	}

	public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
		Restaurant restaurant = Restaurant.builder().id(restaurantDTO.getId()).address(restaurantDTO.getAddress()).name(restaurantDTO.getName())
				.address(restaurantDTO.getAddress()).restaurantDescription(restaurantDTO.getRestaurantDescription())
				.city(restaurantDTO.getCity()).build();

		Restaurant savedRestaurant = restaurantRepo.save(restaurant);
		return RestaurantDTO.builder().id(restaurant.getId()).name(restaurant.getName()).address(restaurant.getAddress())
				.city(restaurant.getCity()).restaurantDescription(restaurant.getRestaurantDescription()).build();
	}
	
	  public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
		  
	  Optional<Restaurant> restaurant = restaurantRepo.findById(id);
	  if(restaurant.isPresent()) {
		  Restaurant restaurant2 = restaurant.get();
		  
		  RestaurantDTO restaurantDTO = RestaurantDTO.builder().name(restaurant2.getName()).id(restaurant2.getId())
				  .city(restaurant2.getCity())
				  .address(restaurant2.getAddress())
				  .restaurantDescription(restaurant2.getRestaurantDescription()).build();
		  return new ResponseEntity<RestaurantDTO>(restaurantDTO, HttpStatus.OK);
	  }return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	  
	  }
	 
	 }