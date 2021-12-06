package app.batch.writer;

import app.batch.model.Manager;
import app.batch.repository.secondary.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MyCustomWriter implements ItemWriter<Manager> {


    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public void write(List<? extends Manager> list) throws Exception {
        log.info("saving %s manager records", list.size());
        managerRepository.saveAll(list);
    }
}
