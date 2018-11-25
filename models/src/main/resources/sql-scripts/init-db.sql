INSERT INTO "user_table" (username, password, profileimg, firstname, surname) VALUES ('sanja', '1234', 'lol.com', 'Sanja', 'Grohar'); -- 1
INSERT INTO "user_table" (username, password, profileimg, firstname, surname) VALUES ('nina', '1234', 'lol.com', 'Nina', 'Osenar'); -- 2
INSERT INTO "user_table" (username, password, profileimg, firstname, surname) VALUES ('nejc', '1234', 'gg.com', 'Nejc', 'Ribič'); -- 4

INSERT INTO "user_table_user_table"(user_table_user_id, friends_user_id) VALUES (1, 2);
INSERT INTO "user_table_user_table"(user_table_user_id, friends_user_id) VALUES (1, 3);
INSERT INTO "user_table_user_table"(user_table_user_id, friends_user_id) VALUES (2, 1);
INSERT INTO "user_table_user_table"(user_table_user_id, friends_user_id) VALUES (3, 1);



-- docker run -d  -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=users -p 5432:5432 postgres:10.5