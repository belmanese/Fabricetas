package com.fabricetas.controller;

import com.fabricetas.domain.dto.ReportDto;
import com.fabricetas.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;

	// ------------ Obtener todas las Estampas vendidas por un artista en un rango de fechas -------------------

	@RequestMapping(value = "/reporte_artista_fecha/{artistaId}_{fechaInicial}_{fechaFinal}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReportDto>> listEstampasPorArtistaFecha(
            @PathVariable("artistaId") Integer artistaId, @PathVariable("fechaInicial") String fechaInicial, @PathVariable("fechaFinal") String fechaFinal) {

		List<ReportDto> report =
			(List<ReportDto>) reportService.stampsSoldByUserBetweenDate(artistaId, fechaInicial, fechaFinal);

		if (report.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(report, HttpStatus.OK);

	}

	// ------------ Obtener todas las Estampas vendidas por un tema de artista -------------------

	@RequestMapping(value = "/reporte_artista_tema/{artistaId}_{temaId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReportDto>> listEstampasPorArtistatEMA(
			@PathVariable("artistaId") Integer artistaId, @PathVariable("temaId") Integer temaId) {

		List<ReportDto> report =
				(List<ReportDto>) reportService.stampsSoldByUserTheme(artistaId, temaId);

		if (report.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	// ------------ Obtener todas las Estampas vendidas  -------------------

	@RequestMapping(value = "/reporte_estampas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReportDto>> listEstampas() {

		List<ReportDto> report =
				(List<ReportDto>) reportService.allStamps();

		if (report.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	// ------------ Obtener todas las Estampas vendidas por artista  -------------------

	@RequestMapping(value = "/reporte_estampas_{artistaId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReportDto>> listEstampasPorArtista(@PathVariable("artistaId") Integer artistaId) {

		List<ReportDto> report =
				(List<ReportDto>) reportService.stampsSoldByUser(artistaId);

		if (report.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	// ------------ Obtener todas las Camisetas vendidas  -------------------

	@RequestMapping(value = "/reporte_camisetas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReportDto>> listCamisetas() {

		List<ReportDto> report =
				(List<ReportDto>) reportService.allTshirt();

		if (report.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<>(report, HttpStatus.OK);
	}

}
