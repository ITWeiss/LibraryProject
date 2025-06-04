CREATE TABLE books(
    id serial,
    name varchar(255) NOT NULL,
    year_of_publication INT,
    author varchar(255),
    annotation TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed_at TIMESTAMP,
    created_user_id INTEGER REFERENCES users(id),
    updated_user varchar(255),
    removed_user varchar(255),
    person_id integer references persons(id)
    constraint pk_books primary key (id)
);