--liquibase formatted sql
--changeset pasko_nv:270420222012 objectQuotingStrategy="QUOTE_ALL_OBJECTS" failOnError: true
-- http://www.liquibase.org/documentation/sql_format.html
CREATE TABLE city
(
    geonameid         INTEGER PRIMARY KEY,
    name              VARCHAR(200),
    asciiname         VARCHAR(200),
    alternatenames    TEXT,
    latitude          DECIMAL,
    longitude         DECIMAL,
    feature_class     VARCHAR(1),
    feature_code      VARCHAR(10),
    country_code      VARCHAR(2),
    cc2               VARCHAR(60),
    admin1_code       VARCHAR(20),
    admin2_code       VARCHAR(20),
    admin3_code       VARCHAR(20),
    admin4_code       VARCHAR(20),
    population        BIGINT,
    elevation         INTEGER,
    dem               INTEGER,
    timesone          VARCHAR(40),
    modification_date DATE
);

CREATE INDEX IF NOT EXISTS city_name_index
    ON city (name);

CREATE INDEX IF NOT EXISTS city_asciiname_index
    ON city (asciiname);

CREATE INDEX IF NOT EXISTS city_alt_name_index
    ON city (alternatenames);

