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