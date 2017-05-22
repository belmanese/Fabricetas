package com.fabricetas.service.impls;

import com.fabricetas.domain.Invoice;
import com.fabricetas.domain.dto.InvoiceDto;
import com.fabricetas.repos.InvoiceRepository;
import com.fabricetas.service.InvoiceService;
import com.fabricetas.util.FetchService;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class used as a service for invoice on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private FetchService<Invoice, InvoiceDto> fetchService;

    /**
     * To create a invoice
     * @param invoice for create
     * @return invoice created
     */
    public Invoice create(Invoice invoice) {
        return saveOrUpdate(invoice);
    }

    /**
     * To remove a invoice
     * @param id of the invoice to remove
     */
    public void delete(Integer id){
        invoiceRepository.delete(id);
    }

    /**
     * Check if a invoice exist
     * @param id of the invoice to check
     * @return if invoice exist
     */
    public Boolean exist(Integer id){
        return invoiceRepository.exists(id);
    }

    /**
     * Read a invoice by id
      * @param id of the invoice to find
     * @return found invoice
     */
    public Invoice findOne(Integer id){
        return invoiceRepository.findOne(id);
    }

    /**
     * Read a InvoiceDto by id
     * @param id of the invoice to find
     * @param fetch String of entities for fetch
     * @return found invoice
     */
    public InvoiceDto findOneDto(Integer id, String fetch) {
        return fetchService.doFetch(findOne(id), fetch);
    }

    /**
     * Read all invoices
     * @return invoice list
     */
    public List<Invoice> findAll(){
        return Lists.newArrayList(invoiceRepository.findAll());
    }

    /**
     * To edit a invoice
     * @param invoice to edit
     * @return edited invoice
     */
    public Invoice update(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    /**
     * Private method for create or update a invoice
     * @param invoice to create or to update
     * @return invoice created or updated
     */
    private Invoice saveOrUpdate(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

}