package com.fabricetas.service.impls;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabricetas.domain.dto.ReportDto;
import com.fabricetas.domain.dto.ReportShirtDto;
import com.fabricetas.domain.dto.ReportStampArtistByDateDto;
import com.fabricetas.service.ReportService;
import com.fabricetas.util.CallService;
import com.google.common.collect.Lists;

/**
 * Class used as a service for stamp on the controller
 * Created on 14/04/2017
 * @author belman 
 */
@Transactional
@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CallService<ReportDto> callService;

    /**
     * @return Collection of reports for view
     */
    public Collection<ReportDto> allTshirt() {
        return callService.callProcedure(
                new ReportShirtDto(),
                "ALL_TSHIRT",
                Lists.newArrayList(),
                Lists.newArrayList("nombre","color","talla","valor","cantidad","total"));
    }

    /**
     * @return Collection of reports for view
     */
    public Collection<ReportDto> allStamps() {
        return callService.callProcedure(
            new ReportStampArtistByDateDto(),
            "ALL_STAMPS",
            Lists.newArrayList(),
            Lists.newArrayList("nombre","tema","valor","cantidad","total"));
        }

    /**
     * @param userId
     * @param initialDate
     * @param finalDate
     * @return Collection of reports for view
     */
    @SuppressWarnings("unchecked")
	public Collection<ReportDto> stampsSoldByUserBetweenDate(
            Integer userId, String initialDate, String finalDate) {

        Date initDate, finDate;
        initDate = finDate = new Date(0);

        try {
            initDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(initialDate).getTime());
            finDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(finalDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return callService.callProcedure(
            new ReportStampArtistByDateDto(),
            "STAMP_SOLD_BY_USER_BETWEEN_DATE",
            Lists.newArrayList(userId, initDate, finDate),
            Lists.newArrayList("nombre","tema","valor","cantidad","total"));
    }

    /**
     * @param userId
     * @param themeId
     * @return Collection of reports for view
     */
    public Collection<ReportDto> stampsSoldByUserTheme(
        Integer userId, Integer themeId){
        return callService.callProcedure(
            new ReportStampArtistByDateDto(),
            "STAMP_SOLD_BY_USER_THEME",
            Lists.newArrayList(userId, themeId),
            Lists.newArrayList("nombre","tema","valor","cantidad","total"));
    }

    /**
     * @param userId
     * @return Collection of reports for view
     */
    public Collection<ReportDto> stampsSoldByUser(Integer userId){
        return callService.callProcedure(
            new ReportStampArtistByDateDto(),
            "STAMP_SOLD_BY_USER",
            Lists.newArrayList(userId),
            Lists.newArrayList("nombre","tema","valor","cantidad","total"));
    }

}