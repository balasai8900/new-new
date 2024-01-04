package com.pack.fabo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pack.fabo.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
	
	//Retrive All
	List<Invoice> findById(long id);
	
	// Custom JPQL query to find invoices by searching for a term in various fields
    @Query("SELECT i FROM Invoice i WHERE " +
           "LOWER(i.invoiceType) LIKE %:searchTerm% OR " +
           "LOWER(i.storeCode) LIKE %:searchTerm% OR " +
           "LOWER(i.storeName) LIKE %:searchTerm%")
    List<Invoice> findBySearchTerm(@Param("searchTerm") String searchTerm);
	
   
    // Method to find distinct invoice types
    @Query("SELECT DISTINCT i.invoiceType FROM Invoice i")
    List<String> findDistinctInvoiceType(); 
    
    //filter for invoice type
	List<Invoice> findByInvoiceType(String invoiceType);
}
