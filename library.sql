drop database if exists library;

create database library;
use library;

create table book (
	isbn char(10),
    title varchar(50) not null unique,
    descr varchar(255),
    rented boolean not null default false,
    added_to_library date not null,
    primary key (isbn)
);
#describe book;

insert into book(isbn, title, descr, added_to_library) 
	values('1234567890', 'The Great Gatsy', 
    'A social satire on the glamourous twenties in the US.', 
    current_date());
    
insert into book(isbn, title, descr, added_to_library) 
	values('1234567891', 'Don Quixote', 
    'A retired country gentleman lives in an unnamed section of La Mancha with his niece and housekeeper.', 
    current_date());
    
insert into book(isbn, title, descr, added_to_library) 
	values('1234567892', 'Moby Dick', 
    'Captain Ahab and his monomaniacal pursuit of the white whale.', 
    current_date());
 
insert into book(isbn, title, descr, added_to_library) 
	values('1234567893', 'Hamlet', 
    'The Tragedy of Hamlet, Prince of Denmark.', 
    current_date());
    
insert into book(isbn, title, descr, added_to_library) 
	values('1234567894', 'Alice in Wonderland', 
    'A little girl stumbles down a rabbit hole.', 
    current_date());
    
insert into book(isbn, title, descr, added_to_library) 
	values('1234567895', 'The Grapes of Wrath', 
    'Set during the Great Depression, the novel focuses on a poor family of sharecroppers.', 
    current_date());
    
insert into book(isbn, title, descr, added_to_library) 
	values('1234567896', 'The Lord of the Rings', 
    'The story follows hobbits Frodo, Sam, Merry, and Pippin across Middle-earth to stop the Dark Lord Sauron.', 
    current_date());    
    
insert into book(isbn, title, descr, added_to_library) 
	values('1234567897', '1984', 
    'Written in 1949, it depicted a future (1984) when government surveillance had reached a totalitarian state.', 
    current_date());     
    
    
    
create table patron (
	patron_id int auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    username varchar(50) not null unique,
    password varchar(50) not null,
    account_frozen boolean not null default true,
    primary key(patron_id)
);

insert into patron(first_name, last_name, username, password, account_frozen) 
	values('Piers', 'Johns', 'pj123', '1234', false);
    
insert into patron(first_name, last_name, username, password, account_frozen) 
	values('Velma', 'Finch', 'vfinch', '1234', true);
insert into patron(first_name, last_name, username, password, account_frozen) 
	values('Menna', 'Clark', 'mennack', '1234', false);
    
insert into patron(first_name, last_name, username, password, account_frozen) 
	values('Chris', 'Kim', 'kimc', '1234', false);
    
insert into patron(first_name, last_name, username, password, account_frozen) 
	values('Maria', 'Nunez', 'm_nunez', '1234', true);
    
insert into patron(first_name, last_name, username, password, account_frozen) 
	values('Kody', 'Brook', 'kodybrook', '1234', false);
    
insert into patron(first_name, last_name, username, password, account_frozen) 
	values('Amrit', 'Daly', 'daly', '1234', false);


create table book_checkout 
(
	checkout_id int auto_increment,
    patron_id int not null,
    isbn char(10) not null,
    checkedout date not null,
    due_date date not null,
    returned date,
    primary key(checkout_id),
    foreign key(patron_id) references patron(patron_id),
    foreign key(isbn) references book(isbn)
);

insert into book_checkout(patron_id, isbn, checkedout, due_date, returned) 
	values(1, '1234567890', current_date() - interval 30 day, 
    current_date() - interval 20 day, current_date() - interval 25 day);

insert into book_checkout(patron_id, isbn, checkedout, due_date, returned) 
	values(3, '1234567890', current_date() - interval 10 day, 
    current_date() - interval 3 day, current_date() - interval 4 day);
    
insert into book_checkout(patron_id, isbn, checkedout, due_date, returned) 
	values(1, '1234567893', current_date() - interval 5 day, 
    current_date() + interval 5 day, null);
update book set rented = true where isbn = '1234567893';

insert into book_checkout(patron_id, isbn, checkedout, due_date, returned) 
	values(4, '1234567894', current_date() - interval 8 day, 
    current_date() - interval 1 day, null);
update book set rented = true where isbn = '1234567894';

insert into book_checkout(patron_id, isbn, checkedout, due_date, returned) 
	values(7, '1234567895', current_date() - interval 9 day, 
    current_date() - interval 2 day, current_date() - interval 2 day);


create table librarian (
	librarian_id int auto_increment,
    username varchar(50) not null unique,
    password varchar(50) not null,
    primary key(librarian_id)
);

insert into librarian(username, password) values('librarian1', '1234');
insert into librarian(username, password) values('librarian2', '1234');
