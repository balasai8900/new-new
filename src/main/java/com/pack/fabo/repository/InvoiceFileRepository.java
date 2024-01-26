package com.pack.fabo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.fabo.entity.InvoiceFile;

@Repository
public interface InvoiceFileRepository extends JpaRepository<InvoiceFile, Long> {
    // You can define custom query methods if needed
}