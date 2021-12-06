package app.batch.processor;

import app.batch.model.EmployeeNew;
import app.batch.model.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCustomProcessor implements ItemProcessor<EmployeeNew, Manager> {

    @Override
    public Manager process(EmployeeNew emp) throws Exception {
        log.info("MyBatchProcessor : Processing data : "+emp);
        Manager manager = new Manager();
        manager.setName(emp.getName().toUpperCase());
        manager.setSalary(emp.getSalary());
        return manager;
    }
}
