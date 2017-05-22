package com.fabricetas.service;

import com.fabricetas.domain.dto.ReportDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Class used as a service for reports on the controller
 * Created on 24/04/2017
 * @author belman 
 */
@Transactional
@Service("reportService")
public interface ReportService {

	/**
	 * @return Collection of reports for view
	 */
	Collection<ReportDto> allTshirt();

	/**
	 * @return Collection of reports for view
	 */
	Collection<ReportDto> allStamps();

	/**
	 * @param userId
	 * @param initialDate
	 * @param finalDate
	 * @return Collection of reports for view
	 */
	Collection<ReportDto> stampsSoldByUserBetweenDate(
		Integer userId, String initialDate, String finalDate);

	/**
	 * @param userId
	 * @param themeId
	 * @return Collection of reports for view
	 */
	Collection<ReportDto> stampsSoldByUserTheme(
		Integer userId, Integer themeId);

	/**
	 * @param userId
	 * @return Collection of reports for view
	 */
	Collection<ReportDto> stampsSoldByUser(Integer userId);

}