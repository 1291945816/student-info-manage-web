/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/4/18 21:25:52                           */
/*==============================================================*/


drop table if exists admin;

drop table if exists class;

drop table if exists course;

drop table if exists department;

drop table if exists sc;

drop table if exists student;

drop table if exists teacher;

drop table if exists user;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   admin_username       varchar(32) not null,
   admin_password       varchar(32) not null default '123456',
   primary key (admin_username)
);

/*==============================================================*/
/* Table: class                                                 */
/*==============================================================*/
create table class
(
   id                   varchar(32) not null,
   classteacher         varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course
(
   courseid             varchar(32) not null,
   name                 varchar(32) not null,
   startdate            varchar(32) not null,
   credit               int not null,
   teacher              varchar(32),
   primary key (courseid)
);

/*==============================================================*/
/* Table: department                                            */
/*==============================================================*/
create table department
(
   name                 varchar(32) not null,
   admin                varchar(32),
   primary key (name)
);

/*==============================================================*/
/* Table: sc                                                    */
/*==============================================================*/
create table sc
(
   studentid            varchar(32) not null,
   class                varchar(64) not null,
   courseid             varchar(32) not null,
   grade                float not null,
   primary key (studentid, class, courseid)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   id                   varchar(32) not null,
   name                 varchar(64) not null,
   sex                  varchar(32) not null,
   birthday             date,
   department           varchar(64) not null,
   class                varchar(64) not null,
   primary key (id, class)
);

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table teacher
(
   id                   varchar(32) not null,
   name                 varchar(32) not null,
   sex                  varchar(32) not null,
   age                  int,
   job                  varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   username             varchar(32) not null,
   password             varchar(32) not null default '123123',
   primary key (username)
);

alter table admin add constraint FK_Reference_6 foreign key (admin_username)
      references teacher (id) on delete restrict on update restrict;

alter table class add constraint FK_Reference_7 foreign key (classteacher)
      references teacher (id) on delete restrict on update restrict;

alter table course add constraint FK_Reference_8 foreign key (teacher)
      references teacher (id) on delete restrict on update restrict;

alter table  student add constraint FK_Reference_4 foreign key (department)
      references department (name) on delete restrict on update restrict;

alter table sc add constraint FK_Reference_1 foreign key (studentid, class)
      references student (id, class) on delete restrict on update restrict;

alter table sc add constraint FK_Reference_2 foreign key (courseid)
      references course (courseid) on delete restrict on update restrict;

alter table student add constraint FK_Reference_3 foreign key (class)
      references class (id) on delete restrict on update restrict;

alter table user add constraint FK_Reference_5 foreign key (username)
      references student (id) on delete restrict on update restrict;

