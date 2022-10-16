package a22.climoilou.mono2.tp1.rd_pm_ih.repositories;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
@Component
public class BD {

    private DataRepository dataRepository;

    @Autowired
    private SerieRepository serieRepository;


    public void SauvegarderData(Data data) {
        this.dataRepository.save(data);
    }

    public void FindDataById(Data data) {
        this.dataRepository.findById(data.getId());
    }

    public void SupprimeData(Data data) {
        this.dataRepository.delete(data);
    }


    /**
     * Gere les transaction des series.
     *
     * @param serie
     */
    public void SaveSerie(Serie serie) {
        this.serieRepository.save(serie);
    }

    public void FindSerieById(Serie serie) {
        this.serieRepository.findById(serie.getId());
    }

    public void SupprimerSerie(Serie serie) {
        this.serieRepository.delete(serie);
    }

    public void SupprimerToutesLesSeries(){
        this.serieRepository.deleteAll();
    }



}
