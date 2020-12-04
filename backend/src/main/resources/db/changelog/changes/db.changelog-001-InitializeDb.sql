-- liquibase formatted sql

-- changeset dhuhunsaker:001-InitializeDb

-- User Table
CREATE TABLE User
(
    id                bigint(20)        auto_increment           not null primary key,
    firstName         varchar(256)                               not null,
    lastName          varchar(256)                               not null,
    email             varchar(128)                               not null,
    createdOn         datetime(3)   default CURRENT_TIMESTAMP(3) not null,
    lastModified      datetime(3)   default CURRENT_TIMESTAMP(3) not null on update CURRENT_TIMESTAMP(3)
);

-- Account Table
CREATE TABLE Account
(
    id  bigint(20)  auto_increment not null primary key,
    userId bigint(20)   not null,
    name    varchar(256)  not null,
    institution varchar(256),
    accountType    varchar(256) not null,
    balance decimal(13, 2) default 0.00 not null,
    dateOpened datetime(3)  default CURRENT_TIMESTAMP(3) not null,
    lastModified datetime(3) default CURRENT_TIMESTAMP(3) not null on update CURRENT_TIMESTAMP(3)
);

-- Transaction Table
CREATE TABLE Transaction
(
    id bigint auto_increment not null primary key,
    userId bigint(20) not null,
    accountId bigint(20) not null,
    transactionType varchar(50) not null,
    description varchar(256),
    transactionDate datetime(3) default CURRENT_TIMESTAMP(3) not null,
    dateAdded datetime(3) default CURRENT_TIMESTAMP(3) not null on update CURRENT_TIMESTAMP(3)
);