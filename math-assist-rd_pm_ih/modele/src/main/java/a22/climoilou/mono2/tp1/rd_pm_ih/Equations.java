package a22.climoilou.mono2.tp1.rd_pm_ih;

import org.springframework.stereotype.Component;
import org.mariuszgromada.math.mxparser.*;

import javax.persistence.*;

@Entity
@Component
public class Equations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "variableA")
    private String a;

    @Column(name = "variableB")
    private String b;

    @Column(name = "variableX")
    private String x;

    @Column(name = "operationUn")
    private String operation1;

    @Column(name = "operationDeux")
    private String operation2;

    public Equations() {
    }

    public Equations(String a, String b, String x, String operation1, String operation2) {
        this.a = a;
        this.b = b;
        this.x = x;
        this.operation1 = operation1;
        this.operation2 = operation2;
    }

    public Equations(Long id, String a, String b, String x, String operation1, String operation2) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.x = x;
        this.operation1 = operation1;
        this.operation2 = operation2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getOperation1() {
        return operation1;
    }

    public void setOperation1(String operation1) {
        this.operation1 = operation1;
    }

    public String getOperation2() {
        return operation2;
    }

    public void setOperation2(String operation2) {
        this.operation2 = operation2;
    }
}
