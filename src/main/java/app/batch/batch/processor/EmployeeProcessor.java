package app.batch.batch.processor;

import app.batch.batch.model.Employee;
import app.batch.batch.model.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeDTO> {

    @Override
    public EmployeeDTO process(Employee employee) throws Exception {
        log.info("Transforming Employee(s) to EmployeeDTO(s)..");
        final EmployeeDTO empployeeDto = new EmployeeDTO(employee.getFirstName(), employee.getLastName(),
            employee.getCompanyName(), employee.getAddress(),employee.getCity(),employee.getCounty(),employee.getState()
            ,employee.getZip());

//        sleep(1500);

        return empployeeDto;
    }
}
