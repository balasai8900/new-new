package com.pack.fabo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pack.fabo.entity.ClientProduct;


public interface ClientProductRepository extends JpaRepository<ClientProduct, Long>{

	List<ClientProduct> findAll();

	List<ClientProduct> findByActiveStatusTrue();

	List<ClientProduct> findByStatus(String statusDropdown);

	@Query("SELECT c FROM ClientProduct c WHERE " +
	        "LOWER(c.storeName) LIKE %:searchTerm% OR " +
	        "LOWER(c.storeCode) LIKE %:searchTerm% OR " +
	        "LOWER(c.productRequestType) LIKE %:searchTerm% OR " +
	        "LOWER(c.storeContact) LIKE %:searchTerm% OR " +
	        "LOWER(c.issueSubject) LIKE %:searchTerm% OR " +
	        "LOWER(c.issueDescription) LIKE %:searchTerm% OR " +
	        "LOWER(c.status) LIKE %:searchTerm% OR " +
	        "LOWER(c.externalComments) LIKE %:searchTerm% OR " +
	        "LOWER(c.commentsToAdmin) LIKE %:searchTerm%")
	List<ClientProduct> findBySearchTerm(@Param("searchTerm") String searchTerm);

	List<ClientProduct> findByProductRequestTypeContaining(String productRequesttype);
}
