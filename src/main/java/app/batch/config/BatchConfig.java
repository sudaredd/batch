package app.batch.config;

import app.batch.model.EmployeeNew;
import app.batch.model.Manager;
import app.batch.processor.MyCustomProcessor;
import app.batch.reader.MyCustomReader;
import app.batch.writer.MyCustomWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EntityScan("app.batch.model.*")
@Configuration
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    MyCustomReader myCustomReader;

    @Autowired
    MyCustomWriter myCustomWriter;

    @Autowired
    MyCustomProcessor myCustomProcessor;

    @Bean(name = "createJob")
    public Job createJob() {
        return jobBuilderFactory.get("MyJob")
            .incrementer(new RunIdIncrementer())
            .flow(createStep()).end().build();
    }

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("MyStep")
            .<EmployeeNew, Manager> chunk(1)
            .reader(myCustomReader)
            .processor(myCustomProcessor)
            .writer(myCustomWriter)
            .build();
    }
}
