create table User(
    ID int not null AUTO_INCREMENT,
    NAME varchar(100) not null,
    ROLE varchar(100) not null,
    USERNAME varchar(100) not null,
    PASSWORD varchar(100) not null,
    PRIMARY KEY ( ID )
);