package com.pack.fabo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pack.fabo.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	
	@Query("SELECT DISTINCT c.state FROM Client c")
    List<String> findDistinctStates();

    @Query("SELECT DISTINCT c.city FROM Client c")
    List<String> findDistinctCities();
    
    List<Client> findByStateAndCity(String state, String city);
    
    List<Client> findByState(String state);

    List<Client> findByCity(String city);
    
    @Query("SELECT c FROM Client c WHERE " +
            "LOWER(c.storename) LIKE %:searchTerm% OR " +
            "LOWER(c.city) LIKE %:searchTerm% OR " +
            "LOWER(c.state) LIKE %:searchTerm% OR " +
            "CAST(c.storecode AS STRING) LIKE %:searchTerm% OR " +
            "LOWER(c.primaryNumber) LIKE %:searchTerm% OR " +
            "LOWER(c.fullAddress) LIKE %:searchTerm% OR " +
            "LOWER(c.pincode) LIKE %:searchTerm% OR " +
            "LOWER(c.secondaryNumber) LIKE %:searchTerm% OR " +
            "LOWER(c.gmbProfileLink) LIKE %:searchTerm% OR " +
            "LOWER(c.gstno) LIKE %:searchTerm% OR " +
            "LOWER(c.ownername) LIKE %:searchTerm%")
	List<Client> findBySearchTerm(@Param("searchTerm") String searchTerm);

	Optional<Client> findByEmail(String email);
}
