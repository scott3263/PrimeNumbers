drop table  `prime_numbers`;

CREATE TABLE `prime_numbers` (
  `seq` double NOT NULL AUTO_INCREMENT COMMENT '순번',
  `prime_num` double NOT NULL COMMENT '소수',
  `int_num` int NOT NULL COMMENT '간격',
  `hash_value` varchar(200) default NULL COMMENT '해시값',
  `calc_duration` int default NULL COMMENT '연산시간(millisec)',
  `reg_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
  `reg_id` varchar(45)  DEFAULT NULL COMMENT '등록자',
  `last_date` datetime DEFAULT  CURRENT_TIMESTAMP COMMENT '최종수정일시',
  `last_id` varchar(45) DEFAULT NULL COMMENT '최종수정자',
  PRIMARY KEY (`seq`),
  UNIQUE KEY `prime_num_UNIQUE` (`prime_num`)
  -- UNIQUE KEY `hash_value_UNIQUE` (`hash_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='소수';

insert into prime_numbers(prime_num, int_num, hash_value) values (2, 0, 'asd');
insert into prime_numbers(prime_num, int_num, hash_value) values (3, 1, '1da');
insert into prime_numbers(prime_num, int_num, hash_value) values (5, 2, 'bgf');

select * from applestar.prime_numbers order by reg_date desc;

select  format(max(seq), "#,##0") 갯수, 
        format(max(prime_num), "#,##0") 최대값,  
        format(max(int_num), "#,##0") 최대간격,  
        format(max(calc_duration), "#,##0") 최대소요시간, 
		format((max(seq) / max(prime_num)) * 100,2) 분포,
        max(reg_date) 최종일시
from applestar.prime_numbers;

select * from applestar.prime_numbers where calc_duration = 5985;
select * from applestar.prime_numbers where int_num = 74;

select date_format(reg_date, '%Y%m%d %H%i'), count(*) from applestar.prime_numbers
group by date_format(reg_date, '%Y%m%d %H%i');

