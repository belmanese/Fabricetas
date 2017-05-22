package com.fabricetas.service;

import java.util.List;

import com.fabricetas.domain.dto.InvoiceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.Invoice;

/**
 * Class used as a service for invoice on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("invoiceService")
public interface InvoiceService {

	/**
	 * To create a invoice
	 * @param invoice for create
	 * @return invoice created
	 */
	Invoice create(Invoice invoice);

	/**
	 * To remove a invoice
	 * @param id of the invoice to remove
	 */
	void delete(Integer id);

	/**
	 * Check if a invoice exist
	 * @param id of the invoice to check
	 * @return if invoice exist
	 */
	Boolean exist(Integer id);

	/**
	 * Read a invoice by id
	 * @param id of the invoice to find
	 * @return found invoice
	 */
	Invoice findOne(Integer id);

	/**
	 * Read a InvoiceDto by id
	 * @param id of the invoice to find
	 * @param fetch String of entities for fetch
	 * @return found invoice
	 */
	InvoiceDto findOneDto(Integer id, String fetch);

	/**
	 * Read all invoices
	 * @return invoice list
	 */
	List<Invoice> findAll();

	/**
	 * To edit a invoice
	 * @param invoice to edit
	 * @return edited invoice
	 */
	Invoice update(Invoice invoice);

}