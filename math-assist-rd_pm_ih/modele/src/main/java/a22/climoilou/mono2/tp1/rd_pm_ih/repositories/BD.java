package a22.climoilou.mono2.tp1.rd_pm_ih.repositories;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BD {

//    private DataRepository dataRepository;

    private SerieRepository serieRepository;


    public BD(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }


    /**
     * Gere les transaction des series.
     *
     * @param serie
     */

    @Transactional
    public void SaveSerie(Serie serie) {
        this.serieRepository.save(serie);
    }

    @Transactional

    public Serie FindSerieById(long id) {
        return this.serieRepository.findById(id).get();
    }

    @Transactional

    public void SupprimerSerie(Serie serie) {
        this.serieRepository.delete(serie);
    }

    @Transactional

    public List<Serie> GetAllSerie(){
        List<Serie> listeDeSerieTemp = new ArrayList<>();
        serieRepository.findAll().forEach(serie -> listeDeSerieTemp.add(serie));
        return listeDeSerieTemp;
    }

    public void SupprimerToutesLesSeries(){
        this.serieRepository.deleteAll();
    }



}
