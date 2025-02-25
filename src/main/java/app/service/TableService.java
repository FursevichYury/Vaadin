package app.service;

import app.model.entity.Father;
import app.model.repository.FatherRepository;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@UIScope
public class TableService {

    private final FatherRepository fatherRepository;

    @Autowired
    public TableService(FatherRepository fatherRepository){
        this.fatherRepository = fatherRepository;
    }

    public List<Father> getTableItems(){
        return fatherRepository.findAll();
    }
}
