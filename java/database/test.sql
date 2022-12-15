--REGISTER USER: 'LennyTheCoolCat' PASSWORD: lenny
--before running the script 
--REGISTER BREWER
BEGIN TRANSACTION;

INSERT INTO users (username, password_hash, role)
VALUES ('LennyTheCoolCat', '$2a$10$nQ5UKm3KpGdpGZcVIBemJurUjgo8sOyQ4qel2PUvtpbMV6PcaWF1i', 'ROLE_USER');
INSERT INTO users (username, password_hash, role)
VALUES ('EdgarTheBrewer', '$2a$10$b0NXb6xaewKYqK38Vv8CQed6D.fuvrTSZ2QAc8VggDSSIUKQfBk1K', 'ROLE_BREWER');


--TEST BEERS--
INSERT INTO beers (beer_name, beer_description, beer_image, beer_type, abv)
VALUES ('Camille''s Awesome Ale', 'A light ale with a hint of Michigan cherry', 'https://www.atwaterbeer.com/wp-content/uploads/2019/09/AW_TC_Cherry_12oz_CAN.png', 'Ale', 0.35);
INSERT INTO beers (beer_name, beer_description, beer_image, beer_type, abv)
VALUES ('Nick''s Bomb Brewski', 'A dark brown ale brewed in the heart of Arkansas ', 'https://restaurantclicks.com/wp-content/uploads/2022/08/Best-Brown-Ale.jpg', 'Brown Ale', 0.4);
INSERT INTO beers (beer_name, beer_description, beer_image, beer_type, abv)
VALUES ('Edgar''s Purple Porter', 'A dark porter brewed with world renowned Missouri plums', 'https://bozemanbrewing.com/wp-content/uploads/2019/06/bbc-20-9266.jpg', 'Porter', 0.5);
INSERT INTO beers (beer_name, beer_description, beer_image, beer_type, abv)
VALUES ('Gabe''s Marvelous Malt', 'A non-alcholic malt brewed for the best of programmers', 'https://hips.hearstapps.com/vader-prod.s3.amazonaws.com/1600269026-41fjQsatsKL.jpg', 'Lager', 0.0);
INSERT INTO beers (beer_name, beer_description, beer_image, beer_type, abv)
VALUES ('Mason''s SQL Seltzer', 'A stout brewed in southern Michigan', 'https://cdn.shopify.com/s/files/1/0400/4000/5800/products/white-claw-black-cherry-hard-seltzer-tallboys-cans-362_600x600.jpg?v=1650033374', 'Seltzer', 0.3);
INSERT INTO beers (beer_name, beer_description, beer_image, beer_type, abv)
VALUES ('Missouri Mule IPA', 'A mild ale brewed in the heart of Missouri', 'https://pineyriverbrewing.com/wp-content/uploads/2019/12/prbc-missouri-mule-ipa-trade-can-2019-scaled.jpg', 'IPA', 20.0);
INSERT INTO beers (beer_name, beer_description, beer_image, beer_type, abv)
VALUES ('Michigan Pale Lager', 'A lager brewed in the depths of Northern Michigan', 'https://res.cloudinary.com/graham-media-group/image/fetch/f_auto/q_auto/c_scale,w_400/https://arc-anglerfish-arc2-prod-gmg.s3.amazonaws.com/public/JAICCAXSEZE7TFXJGGEKXWAQ5E.jpg?_a=ATO2BAA0', 'Lager', 25.0);
INSERT INTO beers (beer_name, beer_description, beer_image, beer_type, abv)
VALUES ('Arkansas Razorback Red', 'A red ale brewed in the great state of Arkansas ', 'https://coreofarkansas.com/wp-content/uploads/2019/12/RR-with-pint-01.png', 'Ale', 0.42);
INSERT INTO beers (beer_name, beer_description, beer_image, beer_type, abv)
VALUES ('Buckeye Brew', 'A pilsner brewed in Northern Ohio ', 'https://products0.imgix.drizly.com/ci-elevator-bleeding-buckeye-red-ale-9e119d3f610e0798.jpeg?auto=format%2Ccompress&ch=Width%2CDPR&fm=jpg&q=20', 'Pilsner', 0.38);

--TEST BREWERIES--
INSERT INTO brewery (brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email)
VALUES ('Tech Elevator Brewery', 'brewpub', 'Java Street', 'Program City', 'Pittsburg', '48152', '8188772428', 'techelevator.com', 'techelevator@gmail.com');
INSERT INTO brewery (brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email)
VALUES ('Columber Brewery', 'micro', 'HTML Avenue', 'CSS City', 'Ohio', '32841', '3407208240', 'ohiostate.com', 'ohiobrewery@gmail.com');
INSERT INTO brewery (brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email)
VALUES ('Little Rock Brewing Co', 'brewpub', 'Spring Boulevard', 'Boot Acres', 'Arkansas', '21538', '7324567893', 'arkansasrazorbacks.com', 'littlerockbrewingco@gmail.com');
INSERT INTO brewery (brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email)
VALUES ('Missouri Brewing Co', 'micro', 'Vue Avenue', 'JavaSctipt Township', 'Missouri', '56782', '2256435482', 'mutigers.com', 'missouribrewing@gmail.com');
INSERT INTO brewery (brewery_name, brewery_type, brewery_street, brewery_city, brewery_state, postal_code, phone_number, website_url, contact_email)
VALUES ('Michgan Brewery', 'brewpub', 'Git Street', 'Intellij Town', 'Michigan', '48212', '3135863131', 'michigan.org', 'mibrewery@gmail.com');

--TETS REVIEWS--
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (4, 'Never had a cheery beer before but this was deliscious. Definately would reccomend trying it!', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Camille''s Awesome Ale' ));
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (2, 'Not a fan, don''t buy it', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Camille''s Awesome Ale' ));
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (3, 'It is an aquired taste. Very strong.', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Nick''s Bomb Brewski' ));
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (4, 'Pairs greta with pizza', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Nick''s Bomb Brewski' ));

INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (5, 'Good stuff, I love plums', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Edgar''s Purple Porter' ));
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (3, 'Definately not my favorite but it is a solid chouice!', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Edgar''s Purple Porter' ));

INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (5, 'Very similar to Odule''s, I loved it ', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Gabe''s Marvelous Malt' ));
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (1, 'I thought this was an alcohlic beverage when I bougth it, I was disappointed once I took a sip.', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Gabe''s Marvelous Malt' ));

INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (3, 'It was mid', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Mason''s SQL Seltzer' ));
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (2, 'Really not that good, not sure why it''s served at brewery when it''s a seltzer', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Mason''s SQL Seltzer' ));

INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (3, 'This stuff is VERY strong. Be careful, lord knows I wasn''t', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Missouri Mule IPA' ));
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (4, 'Great taste! It is on the stornger side, though.', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Missouri Mule IPA' ));

INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (4, 'This is surprisngly good!', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Michigan Pale Lager' ));
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (4, 'Glad my favorite brewery is not serving this!', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Michigan Pale Lager' ));

INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (5, 'Literally the best red ale on the market.', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Arkansas Razorback Red' ));
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (4, 'Always a solid choice', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Arkansas Razorback Red' ));

INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (1, 'Not a fan peronsonally.', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Buckeye Brew' ));
INSERT INTO reviews (stars, review, user_id, beer_id)
VALUES (3, 'Good middle ground for those looking fro something mild', (SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT beer_id FROM BEERS WHERE beer_name = 'Buckeye Brew' ));

INSERT INTO brewery_beerlist (brewery_id, beer_id)
VALUES ((SELECT brewery_id FROM brewery WHERE brewery_name = 'Little Rock Brewing Co'), (SELECT beer_id FROM beers WHERE beer_name = 'Arkansas Razorback Red'));

INSERT INTO favorited (user_id, brewery_id)
VALUES ((SELECT user_id FROM users WHERE username = 'LennyTheCoolCat'), (SELECT brewery_id FROM BREWERY WHERE brewery_name = 'Little Rock Brewing Co'));

Insert into brewery_user(brewery_id, user_id)
values((SELECT brewery_id FROM brewery WHERE brewery_name = 'Missouri Brewing Co'),(SELECT user_id FROM users WHERE username = 'EdgarTheBrewer'));

INSERT INTO brewery_beerlist (brewery_id, beer_id)
VALUES ((SELECT brewery_id FROM brewery WHERE brewery_name = 'Missouri Brewing Co'), (SELECT beer_id FROM beers WHERE beer_name = 'Arkansas Razorback Red'));

INSERT INTO brewery_beerlist (brewery_id, beer_id)
VALUES ((SELECT brewery_id FROM brewery WHERE brewery_name = 'Missouri Brewing Co'), (SELECT beer_id FROM beers WHERE beer_name = 'Edgar''s Purple Porter'));

INSERT INTO brewery_beerlist (brewery_id, beer_id)
VALUES ((SELECT brewery_id FROM brewery WHERE brewery_name = 'Missouri Brewing Co'), (SELECT beer_id FROM beers WHERE beer_name = 'Camille''s Awesome Ale'));

INSERT INTO brewery_beerlist (brewery_id, beer_id)
VALUES ((SELECT brewery_id FROM brewery WHERE brewery_name = 'Missouri Brewing Co'), (SELECT beer_id FROM beers WHERE beer_name = 'Nick''s Bomb Brewski'));

INSERT INTO brewery_beerlist (brewery_id, beer_id)
VALUES ((SELECT brewery_id FROM brewery WHERE brewery_name = 'Missouri Brewing Co'), (SELECT beer_id FROM beers WHERE beer_name = 'Gabe''s Marvelous Malt'));

INSERT INTO brewery_beerlist (brewery_id, beer_id)
VALUES ((SELECT brewery_id FROM brewery WHERE brewery_name = 'Missouri Brewing Co'), (SELECT beer_id FROM beers WHERE beer_name = 'Mason''s SQL Seltzer'));





--SELECT STATEMENTS TO SAVE TIME!!!!!!!!!!!--
-- SELECT * FROM USERS;
-- SELECT * FROM BREWERY_USER;
-- SELECT * FROM FAVORITED;
-- SELECT * FROM BEERS;
-- SELECT * FROM REVIEWS;
-- SELECT * FROM BREWERY;
-- SELECT * FROM BREWERY_BEERLIST;
COMMIT; 




