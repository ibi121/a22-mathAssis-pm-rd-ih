package a22.climoilou.mono2.tp1.rd_pm_ih.repositories;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;

public class BD {
     DataRepository dataRepository;

     public void SauvegarderData(Data data) {
          dataRepository.save(data);
     }

     public void FindDataById(Data data){
          dataRepository.findById(data.getId());
     }







}
