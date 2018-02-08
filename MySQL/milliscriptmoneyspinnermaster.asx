DROP DATABASE IF EXISTS milliscriptmoneyspinnerasxtrial;

CREATE DATABASE milliscriptmoneyspinnerasxtrial;

USE milliscriptmoneyspinnerasxtrial;

CREATE TABLE `milliscriptmoneyspinnerasxtrial`.`TrialControl`
(
cTrialControlCode char(9)not null
PRIMARY KEY,
cSystemName varchar(50) not null,
dTrialStartDate datetime not null,
dTrialEndDate datetime not null,
dLastCheckDate datetime not null,
iDayLeft int not null,
iHourLeft int not null,
iMinLeft int not null,
iSecLeft int not null
);

CREATE TABLE `milliscriptmoneyspinnerasxtrial`.`Company`
(
cCompanyCode char(9)not null
PRIMARY KEY ,
vName varchar(40) unique not null,
vAddress varchar(80),
cCity char(15),
cState char(15),
cCountryCode Char(3)
REFERENCES Country(cCountryCode),
cZip char(8),
cPhone char(15),
cRegistrationNo char(10)
)
;

DELIMITER $$

DROP PROCEDURE IF EXISTS `milliscriptmoneyspinnerasxtrial`.`prnGenCompanyCode`$$

CREATE PROCEDURE `milliscriptmoneyspinnerasxtrial`.`prnGenCompanyCode`(OUT CompanyCode char(9))
BEGIN
	DECLARE COUNTER INT;
	SELECT count(cCompanyCode) into COUNTER FROM Company;
	SET COUNTER=COUNTER+1;

	CASE
		WHEN COUNTER>=0 and COUNTER<=9 THEN
		SET CompanyCode=CONCAT('CP000000',CAST(COUNTER AS CHAR(9)));
		WHEN COUNTER>9 and COUNTER<=99 THEN
		SET CompanyCode=CONCAT('CP00000',CAST(COUNTER AS CHAR(9)));
		WHEN COUNTER>99 and COUNTER<=999 THEN
		SET CompanyCode=CONCAT('CP0000',CAST(COUNTER AS CHAR(9)));
		WHEN COUNTER>999 and COUNTER<=9999 THEN
		SET CompanyCode=CONCAT('CP000',CAST(COUNTER AS CHAR(9)));
		WHEN COUNTER>9999 and COUNTER<=99999 THEN
		SET CompanyCode=CONCAT('CP00',CAST(COUNTER AS CHAR(9)));
		WHEN COUNTER>99999 and COUNTER<=999999 THEN
		SET CompanyCode=CONCAT('CP0',CAST(COUNTER AS CHAR(9)));
		WHEN COUNTER>999999 THEN SET CompanyCode=CONCAT('CP',CAST(COUNTER AS CHAR(9)));
	END CASE;
END$$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `milliscriptmoneyspinnerasxtrial`.`prnConfirmTrial`$$

CREATE PROCEDURE `milliscriptmoneyspinnerasxtrial`.`prnConfirmTrial`(
chTrialCode Char(9),
vhSystemName varchar(50)
)
BEGIN
	SELECT iDayLeft INTO @LastDayLeft FROM TrialControl WHERE cTrialControlCode=chTrialCode;
	SELECT iHourLeft INTO @LastHourLeft FROM TrialControl WHERE cTrialControlCode=chTrialCode;
	SELECT iMinLeft INTO @LastMinLeft FROM TrialControl WHERE cTrialControlCode=chTrialCode;
	SELECT iSecLeft INTO @LastSecLeft FROM TrialControl WHERE cTrialControlCode=chTrialCode;
	SELECT dTrialEndDate INTO @EndDate FROM TrialControl WHERE cTrialControlCode=chTrialCode;

	SET @CheckDate=NOW();


	SET @DayLeft=TIMESTAMPDIFF(DAY,@CheckDate,@EndDate);
	SET @HourLeft=TIMESTAMPDIFF(HOUR,@CheckDate,@EndDate);
	SET @MinLeft=TIMESTAMPDIFF(MINUTE,@CheckDate,@EndDate);
	SET @SecLeft=TIMESTAMPDIFF(SECOND,@CheckDate,@EndDate);

	IF EXISTS(SELECT * FROM TrialControl WHERE cTrialControlCode=chTrialCode)	
	THEN
		IF(@LastDayLeft>=@DayLeft AND @LastHourLeft>=@HourLeft AND @LastMinLeft>=@MinLeft AND @LastSecLeft>@SecLeft AND @LastDayLeft>0)
		THEN
			UPDATE TrialControl
			SET dLastCheckDate=@CheckDate,iDayLeft=@DayLeft,iHourLeft=@HourLeft,iMinLeft=@MinLeft,iSecLeft=@SecLeft
			WHERE cTrialControlCode=chTrialCode;
	
			SELECT 'Valid' AS "cState",@DayLeft AS "iDayLeft",@lastDayLeft AS "iLastDayLeft",@HourLeft AS "iHourLeft",@LastHourLeft AS "iLastHourLeft",@MinLeft AS "iMinLeft",@LastMinLeft AS "ilastMinLeft",@LastSecLeft AS "ilastSecLeft";
		ELSE
			SELECT 'Expired' AS "cState",@DayLeft AS "iDayLeft",@lastDayLeft AS "iLastDayLeft",@HourLeft AS "iHourLeft",@LastHourLeft AS "iLastHourLeft",@MinLeft AS "iMinLeft",@LastMinLeft AS "ilastMinLeft",@LastSecLeft AS "ilastSecLeft";
		END IF;
	ELSE

	INSERT INTO TrialControl
	VALUES(chTrialCode,vhSystemName,NOW(),DATE_ADD(NOW(),INTERVAL 30 DAY),NOW(),30,720,43200,2592000);

	SELECT 'Valid' AS "cState",30 AS "iDayLeft";

	END IF;
END$$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS `milliscriptmoneyspinnerasxtrial`.`prnInsCompany`$$

CREATE PROCEDURE `milliscriptmoneyspinnerasxtrial`.`prnInsCompany`(
vName Varchar(40),
vAddress Varchar(80),
cCity Char(15),
cState Char(15),
vCountryName Varchar(40),
cZip Char(8),
cPhone Char(15),
vRegistrationNumber varchar(10))
BEGIN
	CALL prnGenCompanyCode(@CompanyCode);
	CALL prnGenCountryCodeForName(vCountryName,@CountryCode);
	INSERT INTO Company
	VALUES(@CompanyCode,vName,vAddress,cCity,cState,@CountryCode,cZip,
		cPhone,vRegistrationNumber);
END$$

DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS `milliscriptmoneyspinnerasxtrial`.`prnUpdCompany`$$

CREATE PROCEDURE `milliscriptmoneyspinnerasxtrial`.`prnUpdCompany`(
chCompanyCode Char(9),
vhAddress Varchar(80),
chCity Char(15),
chState Char(15),
vhCountryName Varchar(40),
chZip Char(8),
chPhone Char(15),
vhRegistrationNumber varchar(10)
)
BEGIN
	CALL prnGenCountryCodeForName(vhCountryName,@CountryCode);
	UPDATE Company 
	SET vAddress=vhAddress,cCity=chCity,cState=chState,cCountryCode=@CountryCode,cZip=chZip,cPhone=chPhone,
	cRegistrationNo=vhRegistrationNumber
	WHERE cCompanyCode=chCompanyCode;
END$$

DELIMITER ;

