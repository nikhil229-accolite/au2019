create database AuDatabase;
use AuDatabase;

DROP TABLE IF EXISTS game;

create table game(
m_id int,
dom Date,
won varchar(256),
max_run_scored varchar(256),
max_runs int,
max_wicket_taker varchar(256),
max_wickets int,
primary key(m_id)
);


insert into game values(1,"2019-01-01","india","dhoni",123,"bumrah",3);
insert into game  values(2,"2019-01-02","Australia","ponting",32,"lee",2);
insert into game  values(3,"2019-01-03","WI","gyle",12,"taylor",4);
insert into game  values(4,"2019-01-04","Pakistan","iqbal",101,"akram",1);
insert into game  values(5,"2019-01-05","india","ponting",32,"lee",2);
insert into game  values(6,"2019-01-06","bangladesh","hasan",45,"mhmd",2);
insert into game  values(7,"2019-01-07","england","kevin",100,"jason",4);
insert into game  values(8,"2019-01-08","NZ","mcculum",76,"boult",4);
insert into game  values(9,"2019-01-09","south africa","abd",92,"polluck",3);
insert into game  values(10,"2019-01-10","india","yuvraj",90,"bhuvi",3);

DROP TABLE IF EXISTS team_stats;
create table team_stats(
m_id int ,
t_name varchar(256),
p_id int,
runs int,
wicket int,
sixes int,
fours int,
primary key(m_id,t_name,p_id)
);

insert into team_stats values(4,"India",2,3,2,0,0);
insert into team_stats values(4,"India",1,54,0,2,3);
insert into team_stats values(4,"Pakistan",2,22,5,2,1);
insert into team_stats values(4,"Pakistan",1,101,0,5,6);
insert into team_stats values(3,"Australia",2,0,2,0,0);
insert into team_stats values(3,"Australia",1,50,0,1,5);
insert into team_stats values(3,"WI",2,10,4,0,1);
insert into team_stats values(3,"WI",1,150,0,6,10);
insert into team_stats values(2,"NewZeland",2,0,1,0,0);
insert into team_stats values(2,"NewZeland",1,10,0,1,1);
insert into team_stats values(2,"Australia",2,5,2,0,0);
insert into team_stats values(2,"Australia",1,32,0,2,3);
insert into team_stats values(1,"Pakistan",2,0,1,0,0);
insert into team_stats values(1,"Pakistan",1,30,0,2,1);
insert into team_stats values(1,"India",2,10,3,0,0);
insert into team_stats values(1,"India",1,123,0,6,10);




DROP TABLE IF EXISTS Scores;

create table Scores
(
t_name varchar(256),
p_id int,
p_fname char(255),
p_Mname char(255),
p_lname char(255),
runs int,
wicket int,
primary key(t_name,p_id)
);

insert into Scores values("NewZeland",2,"G","","Boult",0,0);
insert into Scores values("NewZeland",1,"Ls","jr","Balton",0,0);
insert into Scores values("Pakistan",2,"Mahammad","ul","hassan",0,0);
insert into Scores values("Pakistan",1,"Mohammad","","Iqbal",0,0);
insert into Scores values("WI",2,"Jeoram","P","Taylor",0,0);
insert into Scores values("WI",1,"Chris","","Gayle",0,0);
insert into Scores values("Australia",2,"Bret","","lee",0,0);
insert into Scores values("Australia",1,"Ricky","","Ponting",0,0);
insert into Scores values("India",2,"Jaspreet","Singh","Bumrah",0,0);
insert into Scores values("India",1,"Mahendra","Singh","Dhoni",0,0);



UPDATE Scores a,
 (select t_name,p_id,sum(wicket) as wick
 from team_stats
 group by t_name,p_id) b
 SET a.wicket = b.wick
 where a.t_name = b.t_name and a.p_id = b.p_id;
 

DROP TABLE IF EXISTS Extras;
create table Extras
(
m_id int,
t_name char(255),
wide int,
lb int,
nb int,
fh int
);

insert into extras values(1,"India",1,1,1,0);
insert into extras values(1,"Pakistan",1,1,1,0);

DROP TABLE IF EXISTS MatchPlayed;
create table MatchPlayed(
t_name varchar(256),
played int,
primary key(t_name)
);
insert into MatchPlayed values("WI",1);
insert into MatchPlayed values("NewZeland",1);
insert into MatchPlayed values("Australia",2);
insert into MatchPlayed values("Pakistan",2);
insert into MatchPlayed values("India",2);
alter table MatchPlayed add points int default 0;
alter table MatchPlayed add lost int default 0;
alter table MatchPlayed add won int default 0;


drop function if exists cal;
DELIMITER $$
 
CREATE FUNCTION cal(test char(255)) RETURNS int (10)
deterministic
BEGIN
    DECLARE x  int;
    Declare var char(255);
    set var = test;
    
    select played into x
    from MatchPlayed a
    where var = a.t_name;
 RETURN x;
END$$
DELIMITER //





-- Contraints Adding 
ALTER TABLE team_stats DROP PRIMARY KEY, ADD PRIMARY KEY(m_id,t_name,p_id);  
ALTER TABLE Scores DROP PRIMARY KEY, ADD PRIMARY KEY(t_name,p_id);  
ALTER TABLE Extras DROP PRIMARY KEY, ADD PRIMARY KEY(m_id,t_name); 
ALTER TABLE MatchPlayed DROP PRIMARY KEY, ADD PRIMARY KEY(t_name);  

ALTER TABLE team_stats ADD FOREIGN KEY (m_id) REFERENCES game(m_id);
ALTER TABLE team_stats ADD FOREIGN KEY (t_name) REFERENCES MatchPlayed(t_name);
ALTER TABLE Scores ADD FOREIGN KEY (t_name) REFERENCES MatchPlayed(t_name);    
ALTER TABLE Extras ADD FOREIGN KEY (m_id) REFERENCES game(m_id); 
ALTER TABLE Extras ADD FOREIGN KEY (t_name) REFERENCES MatchPlayed(t_name); 









