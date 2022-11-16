create table meetup
(
    id          serial primary key,
    agenda      varchar(64) not null,
    description text        not null,
    organizer   varchar(64) not null,
    date_time   timestamp   not null,
    location    varchar(64) not null
);
