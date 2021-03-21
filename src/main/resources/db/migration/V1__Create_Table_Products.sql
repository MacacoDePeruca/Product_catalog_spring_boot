CREATE TABLE `products` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`description` VARCHAR(255) NOT NULL COLLATE 'latin1_swedish_ci',
	`name` VARCHAR(255) NOT NULL COLLATE 'latin1_swedish_ci',
	`price` DECIMAL(19,2) NOT NULL,
	PRIMARY KEY (`id`) USING BTREE
)
