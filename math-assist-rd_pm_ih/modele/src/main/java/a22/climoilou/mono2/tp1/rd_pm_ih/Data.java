package a22.climoilou.mono2.tp1.rd_pm_ih;

import a22.climoilou.mono2.tp1.rd_pm_ih.origine.Serie;

import java.util.Objects;

public class Data {
    double x;
    double y;

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

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Serie.Data data)) return false;
        return Double.compare(data.getX(), x) == 0 && Double.compare(data.getY(), y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Data{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
