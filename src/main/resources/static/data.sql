create schema VeraModa;

create table User(
    id int auto_increment unique,
    name varchar(200) not null,
    age int not null,
    mail varchar(320) not null unique,
    password varchar(300) not null,
    address varchar(200),
    zip_code varchar(10),
    primary key (id)
);

create table Products(
    id int auto_increment unique,
    category varchar(150) not null,
    name varchar(200) not null,
    price varchar(200) not null,
    primary key (id)
);

create table UserOrder(
    id int auto_increment unique,
    timestamp datetime not null ,
    status varchar(100),
    user_id int not null ,
    product_id int not null,
    primary key (id),
    foreign key (user_id) REFERENCES User(id),
    foreign key (product_id) REFERENCES product(id)
);