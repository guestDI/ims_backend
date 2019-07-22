-- we don't know how to generate root <with-no-name> (class Root) :(
create table candidate
(
	id bigserial not null
		constraint candidate_pkey
			primary key,
	active boolean not null,
	comment text,
	firstname varchar(21) not null,
	lastname varchar(21) not null,
	level varchar(255),
	location varchar(255) not null,
	start_date timestamp,
	status varchar(255)
);

alter table candidate owner to adminims;

create table interviewer
(
	id bigserial not null
		constraint interviewer_pkey
			primary key,
	active boolean not null,
	email varchar(255) not null,
	firstname varchar(21) not null,
	lastname varchar(21) not null,
	level varchar(255),
	location varchar(255) not null,
	password varchar(255),
	profile_photo varchar(255),
	username varchar(255)
);

alter table interviewer owner to adminims;

create table interviews
(
	id bigserial not null
		constraint interviews_pkey
			primary key,
	active boolean not null,
	date timestamp,
	status varchar(255),
	candidate_id bigint
		constraint fk1lq0o8hgstsv6g2c2t4b7koos
			references candidate
);

alter table interviews owner to adminims;

create table interview_interviewer
(
	interview_id bigint not null
		constraint fk2o6m79ua07tyi21tf8gcuhq66
			references interviews,
	interviewer_id bigint not null
		constraint fk24x1yvfr1qpb3hax2dmoe88kh
			references interviewer
);

alter table interview_interviewer owner to adminims;

