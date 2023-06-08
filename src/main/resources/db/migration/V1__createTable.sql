CREATE TABLE public."user" (
	id serial NOT NULL,
	about varchar(255) NULL,
	email_id varchar(255) NULL,
	"name" varchar(255) NULL,
	"password" varchar(255) NULL,
	CONSTRAINT user_pkey PRIMARY KEY (id)
);