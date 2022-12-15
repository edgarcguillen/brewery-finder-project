BEGIN TRANSACTION;

DROP TABLE IF EXISTS brewery, beers, reviews, brewery_beerlist, brewery_user, favorited;
CREATE TABLE brewery (
	brewery_id SERIAL NOT NULL,
	brewery_name varchar(150) NOT NULL,
	brewery_type varchar(150),
	brewery_street varchar(150),
	brewery_city varchar(150) NOT NULL,
	brewery_state varchar(125) NOT NULL,
	postal_code varchar(132) NOT NULL,
	phone_number varchar(110),
	website_url varchar(150),
	contact_email varchar(150),
	brewery_image varchar(1000),
	brewery_status varchar(100)
);
ALTER TABLE brewery ADD CONSTRAINT pk_brewery_id PRIMARY KEY (brewery_id);
GRANT ALL ON brewery TO final_capstone_owner, final_capstone_appuser;
GRANT ALL ON brewery_brewery_id_seq TO final_capstone_owner, final_capstone_appuser;
CREATE TABLE beers (
	beer_id SERIAL NOT NULL,
	beer_name varchar(50) NOT NULL,
	beer_description varchar(200) NOT NULL,
	beer_image varchar(2000) NOT NULL,
	beer_type varchar(100) NOT NULL,
	abv decimal NOT NULL
);
ALTER TABLE beers ADD CONSTRAINT pk_beer_id PRIMARY KEY (beer_id);
GRANT ALL ON beers TO final_capstone_owner, final_capstone_appuser;
GRANT ALL ON beers_beer_id_seq TO final_capstone_owner, final_capstone_appuser;
CREATE TABLE reviews (
	review_id SERIAL NOT NULL,
	stars int NOT NULL,
	review varchar(1500) NOT NULL,
	user_id int NOT NULL,
	beer_id int NOT NULL
	
	
);
ALTER TABLE reviews ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE reviews ADD CONSTRAINT fk_beer_id FOREIGN KEY (beer_id) REFERENCES beers (beer_id);
ALTER TABLE reviews ADD CONSTRAINT pk_review_id PRIMARY KEY (review_id);
GRANT ALL ON reviews TO final_capstone_owner, final_capstone_appuser;
GRANT ALL ON reviews_review_id_seq TO final_capstone_owner, final_capstone_appuser;
CREATE TABLE brewery_beerlist (
	brewery_id int NOT NULL,
	beer_id int NOT NULL
	
);
ALTER TABLE brewery_beerlist ADD CONSTRAINT fk_brewery_id FOREIGN KEY (brewery_id) REFERENCES brewery (brewery_id);
ALTER TABLE brewery_beerlist ADD CONSTRAINT fk_beer_id FOREIGN KEY (beer_id) REFERENCES beers (beer_id);
GRANT ALL ON brewery_beerlist TO final_capstone_owner, final_capstone_appuser;
CREATE TABLE brewery_user (
	brewery_id int NOT NULL,
	user_id int NOT NULL
	
);

ALTER TABLE brewery_user ADD CONSTRAINT fk_brewery_id FOREIGN KEY (brewery_id) REFERENCES brewery (brewery_id);
ALTER TABLE brewery_user ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);
GRANT ALL ON brewery_user TO final_capstone_owner, final_capstone_appuser;
CREATE TABLE favorited (
	brewery_id int NOT NULL,
	user_id int NOT NULL
	
);
ALTER TABLE favorited ADD CONSTRAINT fk_brewery_id FOREIGN KEY (brewery_id) REFERENCES brewery (brewery_id);
ALTER TABLE favorited ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);
GRANT ALL ON favorited TO final_capstone_owner, final_capstone_appuser;

COMMIT;
