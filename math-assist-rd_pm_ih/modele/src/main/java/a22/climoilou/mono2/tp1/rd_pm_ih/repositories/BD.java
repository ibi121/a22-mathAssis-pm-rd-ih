package a22.climoilou.mono2.tp1.rd_pm_ih.repositories;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BD {
    private DataRepository dataRepository;
    private SerieRepository serieRepository;


     public void SauvegarderData(Data data) {
          this.dataRepository.save(data);
     }

     public void FindDataById(Data data){
          this.dataRepository.findById(data.getId());
     }

     public void SaveSerie(Serie serie){
          this.serieRepository.save(serie);
     }

     public void FindSerieById(Serie serie){
          this.serieRepository.findById(serie.getId());
     }







}
