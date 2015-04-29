INSERT INTO categories (id, name) VALUES ( 1, 'Technology' );
INSERT INTO categories (id, name) VALUES ( 2, 'IT' );
INSERT INTO categories (id, name) VALUES ( 3, 'Food' );

INSERT INTO projects (id, name,description, id_category) VALUES ( 1,'New OS', 'MorphOS is a lightweight,highly efficient and flexible media-centric operating system. If you are new to MorphOS and would like to learn more about it, please visit this page.' ,2);
INSERT INTO projects (id, name,description, id_category) VALUES ( 2,'Lowcountry Street Grocery"', 'Charleston''s first mission-driven mobile farmers'' market committed to improving healthy food access & bolstering our local food economy' ,3);
INSERT INTO projects (id, name,description, id_category) VALUES ( 3,'Breakfast in a Box', 'The minimal effort, optimised breakfast. Superfoods and mindfulness. A breakfast that nourishes your body & calms your mind.' ,3);
INSERT INTO projects (id, name,description, id_category) VALUES ( 4,'ENVELOP - 3D Sound', 'ENVELOP is a 3D sound platform that is creating a space in San Francisco for live electronic music, DJ sets and educational workshops.' ,1);
INSERT INTO projects (id, name,description, id_category) VALUES ( 5,'Close Network', 'Close Network is an alternate to Social networks where your parents and teachers could easily get hold of your profile, not anymore.' ,2);
INSERT INTO projects (id, name,description, id_category) VALUES ( 6,'Challenge (social network)', 'Virtual network for people, who are getting real life achievments.' ,2);

INSERT INTO users (id, login,password,firstName, lastName, email) VALUES ( 1, 'user1', '123123', 'user1_fname','user1_lname','user1@host.com' );
INSERT INTO users (id, login,password,firstName, lastName, email) VALUES ( 2, 'admin', '123123', 'admin_fname','admin_lname','admin@host.com' );

INSERT INTO active_sessions (id, id_user, token , expireDate) VALUES ( 1, 1, '', '2015-04-29 12:00:00');
INSERT INTO active_sessions (id, id_user, token , expireDate) VALUES ( 2, 2, '', '2015-04-29 2:00:00');