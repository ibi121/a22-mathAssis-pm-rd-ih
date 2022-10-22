package a22.climoilou.mono2.tp1.rd_pm_ih.repositories;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DataService{

    private DataRepository dataRepository;

    @Autowired
    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Transactional
    public void saveData(Data data) {
        this.dataRepository.save(data);
    }

    @Transactional
    public Data findDataById(long id) {
        return dataRepository.findById(id).get();
    }

    @Transactional
    public void suprimmerData(Data data) {
        dataRepository.delete(data);
    }
}
