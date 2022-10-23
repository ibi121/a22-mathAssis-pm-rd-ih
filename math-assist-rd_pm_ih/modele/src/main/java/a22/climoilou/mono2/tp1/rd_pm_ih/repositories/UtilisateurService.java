package a22.climoilou.mono2.tp1.rd_pm_ih.repositories;

import a22.climoilou.mono2.tp1.rd_pm_ih.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService {

    private UserRepository utilisateurBD;

    @Autowired
    public void setUtilisateurBD(UserRepository utilisateurBD) {
        this.utilisateurBD = utilisateurBD;
    }

    @Transactional
    public void SupprimerUtilisateur(Utilisateur utilisateur){
        this.utilisateurBD.delete(utilisateur);
    }

    @Transactional
    public void SauvegarderUtilisateur(Utilisateur utilisateur){
        this.utilisateurBD.save(utilisateur);
    }

    @Transactional
    public Utilisateur FindUserById(Long id){
        return this.utilisateurBD.findById(id).get();
    }

    @Transactional
    public List<Utilisateur> GetAllUtilisateur(){
        List<Utilisateur> listeDeCommentaire = new ArrayList<>();
        utilisateurBD.findAll().forEach(Utilisateur -> listeDeCommentaire.add(Utilisateur));
        return listeDeCommentaire;
    }




}
