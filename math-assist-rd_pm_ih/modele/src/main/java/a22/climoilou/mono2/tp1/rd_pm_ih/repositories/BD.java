package a22.climoilou.mono2.tp1.rd_pm_ih.repositories;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BD {

//    private DataRepository dataRepository;

    @Autowired
    private SerieRepository serieRepository;



//    @Autowired
//    public void setDataRepository(DataRepository dataRepository) {
//        this.dataRepository = dataRepository;
//    }


//    @Autowired
//    public void setSerieRepository(SerieRepository serieRepository) {
//        this.serieRepository = serieRepository;
//    }


//    public void SauvegarderData(Data data) {
//        this.dataRepository.save(data);
//    }
//
//    public void FindDataById(Data data) {
//        this.dataRepository.findById(data.getId());
//    }
//
//    public void SupprimeData(Data data) {
//        this.dataRepository.delete(data);
//    }
//
//
    /**
     * Gere les transaction des series.
     *
     * @param serie
     */
    public void SaveSerie(Serie serie) {
        this.serieRepository.save(serie);
    }

    public Serie FindSerieById(long id) {
        return this.serieRepository.findById(id).get();
    }

    public void SupprimerSerie(Serie serie) {
        this.serieRepository.delete(serie);
    }

    public List<Serie> GetAllSerie(){
        List<Serie> listeDeSerieTemp = new ArrayList<>();
        serieRepository.findAll().forEach(serie -> listeDeSerieTemp.add(serie));
        return listeDeSerieTemp;
    }

    public void SupprimerToutesLesSeries(){
        this.serieRepository.deleteAll();
    }



}
