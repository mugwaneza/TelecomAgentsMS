# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin (
  id                        bigint auto_increment not null,
  fullname                  varchar(255),
  gender                    varchar(255),
  company                   varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  address                   varchar(255),
  created_at                datetime,
  constraint pk_admin primary key (id))
;

create table applicant (
  id                        bigint auto_increment not null,
  agent_id                  bigint,
  district_id               bigint,
  sector_id                 bigint,
  cell_id                   bigint,
  agromeration              varchar(255),
  firstname                 varchar(255),
  lastname                  varchar(255),
  phonenumber               varchar(255),
  address                   varchar(255),
  nationality               varchar(255),
  nid                       varchar(255),
  company                   varchar(255),
  sitename                  varchar(255),
  reject_status             varchar(255),
  passportphoto             varchar(255),
  created_at                datetime,
  constraint pk_applicant primary key (id))
;

create table agents (
  id                        bigint auto_increment not null,
  fullname                  varchar(255),
  gender                    varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  created_at                datetime,
  constraint pk_agents primary key (id))
;

create table inquiry (
  id                        bigint auto_increment not null,
  agents_id                 bigint,
  admin_id                  bigint,
  message                   varchar(255),
  reply                     varchar(255),
  reply_status              tinyint(1) default 0,
  replied_at                varchar(255),
  created_at                datetime,
  constraint pk_inquiry primary key (id))
;

create table approved_agents (
  id                        bigint auto_increment not null,
  applicant_id              bigint,
  admin_id                  bigint,
  status                    tinyint(1) default 0,
  walletnumber              varchar(255),
  airtimenumber             varchar(255),
  approved_at               datetime,
  constraint pk_approved_agents primary key (id))
;

create table cell (
  id                        bigint auto_increment not null,
  sector_id                 bigint,
  cell                      varchar(255),
  description               varchar(255),
  created_at                datetime,
  constraint pk_cell primary key (id))
;

create table district (
  id                        bigint auto_increment not null,
  province_id               bigint,
  district                  varchar(255),
  description               varchar(255),
  created_at                datetime,
  constraint pk_district primary key (id))
;

create table province (
  id                        bigint auto_increment not null,
  province                  varchar(255),
  description               varchar(255),
  created_at                datetime,
  constraint pk_province primary key (id))
;

create table sector (
  id                        bigint auto_increment not null,
  district_id               bigint,
  sector                    varchar(255),
  description               varchar(255),
  created_at                datetime,
  constraint pk_sector primary key (id))
;

alter table applicant add constraint fk_applicant_agent_1 foreign key (agent_id) references agents (id) on delete restrict on update restrict;
create index ix_applicant_agent_1 on applicant (agent_id);
alter table applicant add constraint fk_applicant_district_2 foreign key (district_id) references district (id) on delete restrict on update restrict;
create index ix_applicant_district_2 on applicant (district_id);
alter table applicant add constraint fk_applicant_sector_3 foreign key (sector_id) references sector (id) on delete restrict on update restrict;
create index ix_applicant_sector_3 on applicant (sector_id);
alter table applicant add constraint fk_applicant_cell_4 foreign key (cell_id) references cell (id) on delete restrict on update restrict;
create index ix_applicant_cell_4 on applicant (cell_id);
alter table inquiry add constraint fk_inquiry_agents_5 foreign key (agents_id) references agents (id) on delete restrict on update restrict;
create index ix_inquiry_agents_5 on inquiry (agents_id);
alter table inquiry add constraint fk_inquiry_admin_6 foreign key (admin_id) references admin (id) on delete restrict on update restrict;
create index ix_inquiry_admin_6 on inquiry (admin_id);
alter table approved_agents add constraint fk_approved_agents_applicant_7 foreign key (applicant_id) references applicant (id) on delete restrict on update restrict;
create index ix_approved_agents_applicant_7 on approved_agents (applicant_id);
alter table approved_agents add constraint fk_approved_agents_admin_8 foreign key (admin_id) references admin (id) on delete restrict on update restrict;
create index ix_approved_agents_admin_8 on approved_agents (admin_id);
alter table cell add constraint fk_cell_sector_9 foreign key (sector_id) references sector (id) on delete restrict on update restrict;
create index ix_cell_sector_9 on cell (sector_id);
alter table district add constraint fk_district_province_10 foreign key (province_id) references province (id) on delete restrict on update restrict;
create index ix_district_province_10 on district (province_id);
alter table sector add constraint fk_sector_district_11 foreign key (district_id) references district (id) on delete restrict on update restrict;
create index ix_sector_district_11 on sector (district_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table admin;

drop table applicant;

drop table agents;

drop table inquiry;

drop table approved_agents;

drop table cell;

drop table district;

drop table province;

drop table sector;

SET FOREIGN_KEY_CHECKS=1;

