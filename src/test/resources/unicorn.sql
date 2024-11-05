insert into unicorn (unicorn_uuid, name) values ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'Bubbles Starbreeze');

insert into unicorn_leg (unicorn_id, leg_position, color) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'FRONT_LEFT', 'ORANGE');
insert into unicorn_leg (unicorn_id, leg_position, color) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'FRONT_RIGHT', 'ORANGE');
insert into unicorn_leg (unicorn_id, leg_position, color) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'BACK_LEFT', 'ORANGE');
insert into unicorn_leg (unicorn_id, leg_position, color) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'BACK_RIGHT', 'ORANGE');
