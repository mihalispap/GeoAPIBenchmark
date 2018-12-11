CREATE OR REPLACE FUNCTION addGeoObject(
	lat double precision ,
	lon double precision )

RETURNS integer AS $BODY$

	DECLARE
		objId int;

	BEGIN
		select max(id)+1 into objId from geo_object;
		INSERT INTO geo_object VALUES(objId, lat, lon);
		select max(id) into objId from geo_object;

		return objId;

	END;
$BODY$
LANGUAGE plpgsql VOLATILE

CREATE TYPE compfoo AS (f1 bigint, f2 bigint);

CREATE FUNCTION getFoo(v1 bigint, v2 bigint) RETURNS compfoo AS $$
    SELECT v1, v2;
$$ LANGUAGE SQL;