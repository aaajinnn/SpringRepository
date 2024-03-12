drop table memo;

create table memo(
	no number(8),
	name varchar2(30) not null,
	msg varchar2(100),
	wdate timestamp default systimestamp,
	primary key (no)
);

drop sequence memo_seq;

create sequence memo_seq nocache;