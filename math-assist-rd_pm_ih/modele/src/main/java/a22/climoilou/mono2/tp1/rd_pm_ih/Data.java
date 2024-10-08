package a22.climoilou.mono2.tp1.rd_pm_ih;
/*
BD :
 Username :soleil1,
 mdp : abc123
 */


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
public class Data {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private double x;
    private double y;

    public Data(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Data() {
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public Long getId() {
        return id;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(id, data.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Data{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}

