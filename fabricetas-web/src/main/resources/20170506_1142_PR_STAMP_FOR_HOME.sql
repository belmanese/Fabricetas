DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `STAMPS_FOR_HOME`()
BEGIN
	SELECT S.STAMP_ID estampaId, S.NAME nombre, S.DESCRIPTION descripcion, 
	S.PATH urlImagen, IFNULL(ASD.PROM_VALOR, 0) rating, S.PRICE valor, 
	S.THEME_ID temaId, T.NAME temaNombre, U.USER_ID autorId, 
	CONCAT(U.FIRST_NAME, ' ' , U.LAST_NAME) autorNombre 
	FROM STAMP S 
	INNER JOIN USER U ON S.USER_ID = U.USER_ID 
	INNER JOIN THEME T ON S.THEME_ID = T.THEME_ID
	LEFT JOIN (
	SELECT ROUND(AVG(R.VALUE)) PROM_VALOR, S.STAMP_ID FROM RATING R
	INNER JOIN R_RATING_STAMP RRS ON R.RATING_ID = RRS.RATING_ID 
	INNER JOIN STAMP S ON RRS.STAMP_ID = S.STAMP_ID
	GROUP BY RRS.STAMP_ID
	) AS ASD ON ASD.STAMP_ID = S.STAMP_ID;
END ;;
DELIMITER ;