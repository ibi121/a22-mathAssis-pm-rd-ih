package a22.climoilou.mono2.tp1.rd_pm_ih.repositories;

import a22.climoilou.mono2.tp1.rd_pm_ih.Equations;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquationService {
    private EquationRepository equationRepository;

    @Autowired
    public void setEquationRepository(EquationRepository equationRepository) {
        this.equationRepository = equationRepository;
    }

    /**
     * Gere les transaction des equations.
     *
     * @param equations
     */

    @Transactional
    public void SaveEquation(Equations equations) {
        this.equationRepository.save(equations);
    }

    @Transactional
    public Equations FindEquationsById(long id) {
        return this.equationRepository.findById(id).get();
    }

    @Transactional
    public void SupprimerEquations(Equations equations) {
        this.equationRepository.delete(equations);
    }

    @Transactional
    public List<Equations> GetAllEquations(){
        List<Equations> listeEquationsTemp = new ArrayList<>();
        equationRepository.findAll().forEach(equations -> listeEquationsTemp.add(equations));
        return listeEquationsTemp;
    }


    public void SupprimerToutesLesEquations(){
        this.equationRepository.deleteAll();
    }



}
