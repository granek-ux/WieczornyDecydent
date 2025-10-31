/* Tworzymy tabelę dla naszych użytkowników (app_users),
   aby pasowała do encji User.java */

CREATE TABLE app_users (
                           id BIGSERIAL PRIMARY KEY,  -- BIGSERIAL to auto-inkrementujący się Long w Postgres
                           email VARCHAR(255) NOT NULL UNIQUE,
                           password VARCHAR(255) NOT NULL
);