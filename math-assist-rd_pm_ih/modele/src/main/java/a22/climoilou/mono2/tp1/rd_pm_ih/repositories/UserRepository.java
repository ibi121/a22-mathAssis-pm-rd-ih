package a22.climoilou.mono2.tp1.rd_pm_ih.repositories;

import a22.climoilou.mono2.tp1.rd_pm_ih.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Utilisateur,Long> {
}
