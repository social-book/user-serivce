INSERT INTO "user_table" (username, password, gender) VALUES ('manca', '1234', 'female'); -- 1
INSERT INTO "user_table" (username, password, gender) VALUES ('vid', '1234', 'female'); -- 2
INSERT INTO "user_table" (username, password, gender) VALUES ('ema', '1234', 'female'); -- 3
INSERT INTO "user_table" (username, password, gender) VALUES ('nejc', '1234', 'male'); -- 4

INSERT INTO "user_table_user_table"(user_table_user_id, friends_user_id) VALUES (1, 2);
INSERT INTO "user_table_user_table"(user_table_user_id, friends_user_id) VALUES (1, 3);
INSERT INTO "user_table_user_table"(user_table_user_id, friends_user_id) VALUES (2, 1);
INSERT INTO "user_table_user_table"(user_table_user_id, friends_user_id) VALUES (3, 1);

INSERT INTO "user_table_user_table"(user_table_user_id, friends_user_id) VALUES (4, 3);
INSERT INTO "user_table_user_table"(user_table_user_id, friends_user_id) VALUES (3, 4);


-- docker run -d  -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=users -p 5432:5432 postgres:10.5