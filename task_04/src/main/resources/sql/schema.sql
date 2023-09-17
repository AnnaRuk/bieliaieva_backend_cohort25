create table account (
                         id identity primary key,
                         title varchar(50),
                         description varchar(255),
                         begin_date date,
                         end_date date,
                         budget double
);