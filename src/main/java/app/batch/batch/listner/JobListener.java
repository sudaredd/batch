package app.batch.batch.listner;

import app.batch.batch.model.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class JobListener extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("In Completion Listener ..");
            List<EmployeeDTO> results = jdbcTemplate.query("SELECT first_name,last_name,company_name,address,city,county,state,zip FROM employee",
                (rs,rowNum)->{
                    return new EmployeeDTO(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
                }
            );
            results.forEach(emp->log.info(emp.toString()));
        }
    }

    public void beforeJob(JobExecution jobExecution) {
        log.info("before Job run::"+jobExecution.getStatus().toString());
    }

}
