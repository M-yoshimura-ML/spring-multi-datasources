CREATE PROCEDURE `get_area_by_super_id`(
    IN superId INT(11),
    IN countryCode VARCHAR(3) -- jp, hk, us, etc
)
BEGIN
    /* CREATE CONDITION */

    /* GET AREA DATA */
    SET @sqlSelectArea = CONCAT(
            "SELECT a.id, a.code, ",
            "name_", countryCode," as name ",
            " FROM area as a  ",
			" WHERE a.super_id = ",superId,
			" ORDER BY a.id");

    -- For Debugging, uncomment below
    -- SELECT @sqlSelectArea AS '** DEBUG:';

    -- For Debugging, comment code below
    PREPARE stmtSelect FROM @sqlSelectArea;
    EXECUTE stmtSelect;
    DEALLOCATE PREPARE stmtSelect;
END
