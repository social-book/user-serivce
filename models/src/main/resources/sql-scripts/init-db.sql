INSERT INTO "user_table" (username, password, gender) VALUES ('manca', '1234', 'female');
INSERT INTO "user_table" (username, password, gender) VALUES ('vesna', '1234', 'female');
INSERT INTO "user_table" (username, password, gender) VALUES ('ema', '1234', 'female');
INSERT INTO "user_table" (username, password, gender) VALUES ('nejc', '1234', 'male');


-- docker run -d  -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=users -p 5432:5432 postgres:10.5