package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan("uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.mapper")
public class Team11RecruitmentAndAdmissionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team11RecruitmentAndAdmissionsApplication.class, args);
	}

}
