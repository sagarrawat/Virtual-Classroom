#branch_table
CREATE TABLE `branch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `course_id_idx` (`course_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


#course_table
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


#faculty_table
CREATE TABLE `faculty` (
  `subject_id` int(11) NOT NULL,
  `faculty_name` varchar(45) NOT NULL,
  UNIQUE KEY `subject_id_UNIQUE` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


#student_table
CREATE TABLE `student` (
  `username` varchar(45) DEFAULT NULL,
  `course` varchar(45) NOT NULL,
  `branch` varchar(45) NOT NULL,
  KEY `username_idx` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


#subject_table
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `branch_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`branch_id`),
  CONSTRAINT `id` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;


#user_table
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `id_no` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



#register_student Stored Procedure
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `register_student`(
  `username` varchar(45),
  `password` varchar(45),
  `type` int(11),
  `full_name` varchar(45),
  `phone` varchar(10),
  `id_no` varchar(20),
  `course` varchar(45),
  `branch` varchar(45)
)
BEGIN
	
    insert into user values (
		`username` , `password`, 
		`type`, `full_name`,
		`phone`, `id_no`
    );
    
    insert into student values (
		`username`, 
        `course`, 
        `branch`
	);
    
END$$
DELIMITER ;

#Session table
CREATE TABLE `Session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(100) DEFAULT NULL,
  `topic` varchar(100) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `media` longblob,
  `type` int(11) DEFAULT NULL,
  `hostid` varchar(45) DEFAULT NULL,
  `hostaddress` varchar(45) DEFAULT NULL,
  `ext` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Session_1_idx` (`subject`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;



