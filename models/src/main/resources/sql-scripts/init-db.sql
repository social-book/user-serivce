
-- CREATE TABLE public."user"(id serial PRIMARY KEY NOT NULL,user_id varchar(255), username varchar(255), password varchar(255));
-- CREATE UNIQUE INDEX user_id_uindex ON public."user" (id);


INSERT INTO "user" (username, password) VALUES ('Petra', 'somepass');
-- INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Miha', 'Novak', 'mihanovak', 'miha.novak@gmail.com');
-- INSERT INTO lokacija (geografska_sirina, geografska_dolzina, radij) VALUES (46.050380, 14.468966, 500.0);
-- INSERT INTO lokacija (geografska_sirina, geografska_dolzina, radij) VALUES (46.056195, 14.506057, 500.0);
-- INSERT INTO opomnik (naslov, opis, lokacija_id, uporabnik_id, storitev_id) VALUES ('Obišči referat', 'V referatu dvigni potrdila o vpisu.', 1, 1, null);
-- INSERT INTO opomnik (naslov, opis, lokacija_id, uporabnik_id, storitev_id) VALUES ('LPP', 'Podaljšaj karto za LPP.', 2, 2, null);