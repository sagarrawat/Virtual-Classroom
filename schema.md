#branch_table
CREATE TABLE `branch` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `course_id` int(11) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#course_table
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#faculty_table
CREATE TABLE `faculty` (
  `subject_id` int(11) NOT NULL,
  `faculty_name` varchar(45) NOT NULL,
  UNIQUE KEY `subject_id_UNIQUE` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#student_table
CREATE TABLE `faculty` (
  `subject_id` int(11) NOT NULL,
  `faculty_name` varchar(45) NOT NULL,
  UNIQUE KEY `subject_id_UNIQUE` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#subject_table
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `branch_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

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


