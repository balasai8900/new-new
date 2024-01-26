package com.pack.fabo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pack.fabo.entity.ClientSupport;

public interface ClientSupportRepository extends JpaRepository<ClientSupport, Long>{

	List<ClientSupport> findAll();

	List<ClientSupport> findByActiveStatusTrue();

	List<ClientSupport> findByStatus(String statusDropdown);

	@Query("SELECT c FROM ClientSupport c WHERE " +
	        "LOWER(c.storeName) LIKE %:searchTerm% OR " +
	        "LOWER(c.storeCode) LIKE %:searchTerm% OR " +
	        "LOWER(c.supportRequestType) LIKE %:searchTerm% OR " +
	        "LOWER(c.storeContact) LIKE %:searchTerm% OR " +
	        "LOWER(c.issueSubject) LIKE %:searchTerm% OR " +
	        "LOWER(c.issueDescription) LIKE %:searchTerm% OR " +
	        "LOWER(c.status) LIKE %:searchTerm% OR " +
	        "LOWER(c.externalComments) LIKE %:searchTerm% OR " +
	        "LOWER(c.commentsToAdmin) LIKE %:searchTerm%")
	List<ClientSupport> findBySearchTerm(@Param("searchTerm") String searchTerm);

	List<ClientSupport> findBySupportRequestTypeContaining(String supportRequesttype);
}
