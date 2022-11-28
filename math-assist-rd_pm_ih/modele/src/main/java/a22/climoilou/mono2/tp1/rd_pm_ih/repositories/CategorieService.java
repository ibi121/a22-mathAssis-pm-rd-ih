package a22.climoilou.mono2.tp1.rd_pm_ih.repositories;

import a22.climoilou.mono2.tp1.rd_pm_ih.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieService {

    private CatgeorieRepository catgeorieRepository;

    @Autowired
    public void setCatgeorieRepository(CatgeorieRepository catgeorieRepository) {
        this.catgeorieRepository = catgeorieRepository;
    }

    @Transactional
    public void saveCategorie(Categorie categorie){
        this.catgeorieRepository.save(categorie);
    }

    @Transactional
    public void SupprimerCategorie(Categorie categorie){
        this.catgeorieRepository.delete(categorie);
    }

    @Transactional
    public List<Categorie> GetAllSousCatgeorie(){
        List<Categorie> listeDesSousCategories = new ArrayList<>();
        this.catgeorieRepository.findAll().forEach(sousCategorie -> listeDesSousCategories.add(sousCategorie));
        return listeDesSousCategories;
    }



}
