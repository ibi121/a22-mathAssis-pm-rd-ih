package a22.climoilou.mono2.tp1.rd_pm_ih;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Component
public class Equations {
    @Id
    private Long id;



    public Equations() {
    }
}
