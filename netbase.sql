create database netbase;

use netbase;

create table campaign (
	campaign_id INT(10) AUTO_INCREMENT PRIMARY KEY,
    campaign_name VARCHAR(50) NOT NULL,
	club_id int(4) NOT NULL,
	effective_date TIMESTAMP
);


use netbase;

select * from campaign;

delete from campaign where campaign_id = 15;

CREATE TABLE club (
	club_id INT(5) UNIQUE AUTO_INCREMENT PRIMARY KEY,
	club_name VARCHAR(30) NOT NULL
);

CREATE TABLE club_campaigns (
	club_campaigns_id INT(5) UNIQUE AUTO_INCREMENT PRIMARY KEY,
	club_club_id INT(5) NOT NULL,
	campaign_campaign_id INT(10) NOT NULL,
    CONSTRAINT `club_club_fk` FOREIGN KEY (`club_club_id`) REFERENCES `club` (`club_id`),
    CONSTRAINT `campaign_campaign_fk` FOREIGN KEY (`campaign_campaign_id`) REFERENCES `campaign` (`campaign_id`)
);


drop table club_campaigns;
CREATE TABLE `campaign` (
  `campaign_id` int(10) NOT NULL AUTO_INCREMENT,
  `campaign_name` varchar(50) NOT NULL,
  `campaign_start_date` date DEFAULT NULL,
  `campaign_end_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`campaign_id`)
);

CREATE TABLE customer (
	customer_id INT(10) AUTO_INCREMENT PRIMARY KEY,
	full_name VARCHAR(50) NOT NULL,
    email VARCHAR(30) NOT NULL,
    born_date DATE NOT NULL,
    club_club_id INT(5) NOT NULL,
    CONSTRAINT `club_club_id_fk` FOREIGN KEY (`club_club_id`) REFERENCES `club` (`club_id`)
);

alter table campaign
drop FOREIGN KEY campaign_ibfk_1;

alter table club
add FOREIGN KEY (campaign_id_fk) REFERENCES campaign(campaign_id);

alter table club
add column campaign_id_fk INT(10) NOT NULL,
add FOREIGN KEY (campaign_id_fk) REFERENCES campaign(campaign_id);

CREATE TABLE `campaign` (
  `campaign_id` int(10) NOT NULL AUTO_INCREMENT,
  `campaign_name` varchar(50) NOT NULL,
  `club_id_fk` int(5) NOT NULL,
  `campaign_start_date` date DEFAULT NULL,
  `campaign_end_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`campaign_id`),
  FOREIGN KEY (club_id_fk) REFERENCES club(club_id)
);
