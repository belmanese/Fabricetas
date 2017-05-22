package com.fabricetas.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.fabricetas.domain.Invoice;
import com.fabricetas.domain.Stamp;
import com.fabricetas.domain.Tshirt;
import com.fabricetas.domain.dto.InvoiceDto;
import com.fabricetas.domain.dto.view.InvoiceForViewDto;
import com.fabricetas.domain.dto.view.ItemInvoiceDto;
import com.fabricetas.domain.dto.view.StampForInvoiceDto;
import com.fabricetas.service.InvoiceService;
import com.fabricetas.service.StampService;
import com.fabricetas.service.TshirtService;
import com.fabricetas.service.UserService;
import com.fabricetas.util.UtilNumber;
import com.google.common.collect.Lists;

/**
 * Controller that responds to http requests related to a Invoice
 * Created on 14/04/2017.
 * @author belman
 * @see org.springframework.stereotype.Controller
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TshirtService tshirtService;
    
    @Autowired
    private StampService stampService;

    /**
     * To create a invoice
     * @param invoice for create
     * @return invoice created
     * @return ucBuilder to response htt status
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> create(@RequestBody InvoiceForViewDto invoiceDto, UriComponentsBuilder ucBuilder) {
    	Invoice invoice = new Invoice();

    	String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    	String numberInvoice = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(new Date()).replace(":", "");
    	SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
    	invoice.setNumber(numberInvoice);
    	try {
			invoice.setDate(simpleFormat.parse(currentDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

    	Collection<Tshirt> tshirts = Lists.newArrayList();
    	Collection<Stamp> stamps = Lists.newArrayList();
    	Integer totalPrice = 0;
    	
    	for(ItemInvoiceDto item : invoiceDto.getItemInvoiceDto()){
    		Tshirt tshirtTmp = tshirtService.findOne(Integer.parseInt(item.getTshirtForInvoiceDto().getCamisetaId()));
    		totalPrice += Integer.parseInt(tshirtTmp.getPrice());
    		tshirts.add(tshirtTmp);
    		for(StampForInvoiceDto stamp : item.getStampForInvoiceDto()) 
    			stamps.add(stampService.findOne(Integer.parseInt(stamp.getEstampaId())));
    	}
    	
    	invoice.setTotalPrice(totalPrice+"");
    	invoice.setTshirt(tshirts);
    	invoice.setStamp(stamps);
    	invoice.setUser(userService.findOne(Integer.parseInt(invoiceDto.getUserId())));
    	
        return new ResponseEntity<>(invoiceService.create(invoice), HttpStatus.CREATED);
    }

    /**
     * Read all invoices
     * @return invoice list
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Invoice>> findAll() {
        List<Invoice> invoices = invoiceService.findAll();
        if (invoices.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    /**
     * Read a invoice by id
     * @param id of the invoice to find
     * @return found invoice
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvoiceDto> findOne(
            @PathVariable("id") Integer id,
            @RequestParam(value="fetch", required= false) String fetch) {
        InvoiceDto invoiceDto = invoiceService.findOneDto(id, fetch);
        if (invoiceDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

    /**
     * To edit a invoice
     * @param invoice to edit
     * @return edited invoice
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice) {
        if (UtilNumber.isNullOrZero(invoice.getInvoiceId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else if(!invoiceService.exist(invoice.getInvoiceId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(invoiceService.update(invoice), HttpStatus.OK);
    }

    /**
     * To remove a invoice
     * @param id of the invoice to remove
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if ( !invoiceService.exist(id) )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        invoiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}