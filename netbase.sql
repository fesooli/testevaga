create database netbase;

use netbase;

create table campaign (
	campaign_id INT(10) AUTO_INCREMENT PRIMARY KEY,
    campaign_name VARCHAR(50) NOT NULL,
	club_id int(4) NOT NULL,
	effective_date TIMESTAMP
);
