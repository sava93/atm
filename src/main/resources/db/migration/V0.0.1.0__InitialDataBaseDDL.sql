CREATE TABLE `bank_transaction`

( `transaction_id` BIGINT NOT NULL AUTO_INCREMENT,
 `amount` DOUBLE,
 `device_information` VARCHAR(100),
 `created_at` TIMESTAMP,
 `bank_holder_id` BIGINT,
 PRIMARY KEY (`transaction_id`) );


 CREATE TABLE `bank_account_holder`
 ( `bk_account_id` BIGINT NOT NULL AUTO_INCREMENT,
 `first_name` VARCHAR(100),
 `last_name` VARCHAR(100),
 `account_number` VARCHAR(100),
 `amount` DOUBLE, PRIMARY KEY (`bk_account_id`) );

  CREATE TABLE `users`
 ( `user_id` BIGINT NOT NULL AUTO_INCREMENT,
 `enabled` bit(1),
 `password` VARCHAR(255),
 `username` VARCHAR(255),
 `role` VARCHAR(255), PRIMARY KEY (`user_id`) );

 ALTER TABLE `bank_transaction` ADD FOREIGN KEY (`bank_holder_id`) REFERENCES `bank_account_holder`(`bk_account_id`);