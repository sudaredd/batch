package app.batch.reader;

import app.batch.model.EmployeeNew;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MyCustomReader extends JdbcCursorItemReader<EmployeeNew> implements ItemReader<EmployeeNew> {
    public MyCustomReader(@Autowired DataSource primaryDataSource) {
        setDataSource(primaryDataSource);
        setSql("SELECT id, name, salary FROM employee_new");
        setFetchSize(100);
        setRowMapper(new EmployeeRowMapper());
    }

    public class EmployeeRowMapper implements RowMapper<EmployeeNew> {
        @Override
        public EmployeeNew mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeeNew employee  = new EmployeeNew();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setSalary(rs.getInt("salary"));
            return employee;
        }
    }
}
