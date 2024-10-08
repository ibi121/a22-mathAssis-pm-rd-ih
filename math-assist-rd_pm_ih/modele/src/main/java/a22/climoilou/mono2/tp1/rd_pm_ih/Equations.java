package a22.climoilou.mono2.tp1.rd_pm_ih;

import org.springframework.stereotype.Component;
import org.mariuszgromada.math.mxparser.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Component
public class Equations implements TreeItemI{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String equation;

    @OneToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Equations() {
    }

    public Equations(String equation) {
        this.equation = equation;
    }

    public Equations(String equation, Categorie categorie) {
        this.equation = equation;
        this.categorie = categorie;
    }

    public Equations(Long id, String equation) {
        this.id = id;
        this.equation = equation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equations equations = (Equations) o;
        return Objects.equals(id, equations.id);
    }

    public double calculerEquation(String expression, int x) {
        Function function = new Function(expression);
        Expression e1 = new Expression("f(" + x + ")", function);

        mXparser.consolePrint(e1.calculate());
        return e1.calculate();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String getNom() {
        return this.equation;
    }

    @Override
    public String toString() {
        return "Equation : " + this.equation;
    }
}
