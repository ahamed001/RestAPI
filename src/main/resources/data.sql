insert into user_details(id,birth_date,name)
values(10001, current_date(), 'Ahamed');

insert into user_details(id,birth_date,name)
values(10002, current_date(), 'Irfan');

insert into user_details(id,birth_date,name)
values(10003, current_date(), 'Fazil');


insert into post(id,description,user_id)
values(20001, 'Start work on Dsimilar', 10001);

insert into post(id,description,user_id)
values(20002, 'Complete Rest API', 10002);

insert into post(id,description,user_id)
values(20003, 'Finish Mentop', 10001);