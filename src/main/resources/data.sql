insert into unicorn (unicorn_uuid, name) values ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'Bubbles Starbreeze');

insert into unicorn_leg (unicorn_id, leg_position, color) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'FRONT_LEFT', 'ORANGE');
insert into unicorn_leg (unicorn_id, leg_position, color) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'FRONT_RIGHT', 'ORANGE');
insert into unicorn_leg (unicorn_id, leg_position, color) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'BACK_LEFT', 'ORANGE');
insert into unicorn_leg (unicorn_id, leg_position, color) values ((select id from unicorn where name = 'Bubbles Starbreeze'), 'BACK_RIGHT', 'ORANGE');

insert into magic_ability (magic_ability_uuid, unicorn_uuid, name, activation_color, description)
values
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'ffffffff-ffff-ffff-ffff-ffffffffffff', 'Fire Blast', 'RED', 'Unleashes a blast of fiery magic.'),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'ffffffff-ffff-ffff-ffff-ffffffffffff', 'Water Shield', 'BLUE', 'Creates a protective shield of water.'),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'ffffffff-ffff-ffff-ffff-ffffffffffff', 'Lightning Strike', 'YELLOW', 'Summons a powerful bolt of lightning.'),
    ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'ffffffff-ffff-ffff-ffff-ffffffffffff', 'Earth Quake', 'GREEN', 'Causes the ground to shake and crack beneath enemies.');