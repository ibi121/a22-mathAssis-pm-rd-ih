package a22.climoilou.mono2.tp1.rd_pm_ih.services;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

import java.util.Objects;

public class WindowAnimationService extends ScheduledService<WindowAnimationService.Location> {

    private Location endroitSouhaite;
    private Location endroitActuel;

    private double locationIncrement = 5;


    public void setSouhaitee(Location souhaitee) {
        this.endroitSouhaite = souhaitee;
    }

    public void setActuelle(Location actuelle) {
        this.endroitActuel = actuelle;
    }

    @Override
    protected Task<Location> createTask() {
        return new Task<Location>() {
            @Override
            protected Location call() throws Exception {

                endroitActuel.x = intrapole(endroitSouhaite.x, endroitActuel.x);
                endroitActuel.y = intrapole(endroitSouhaite.y, endroitActuel.y);

                if(endroitActuel.equals(endroitSouhaite)){
                    this.cancel();
                }

                return endroitActuel.clone();
            }

            private double intrapole(double cible, double valeur) {
                if (Math.abs(cible - valeur) < locationIncrement) {
                    valeur = cible;
                } else {
                    if (valeur > cible) {
                        valeur -= locationIncrement;
                    } else {
                        valeur += locationIncrement;
                    }
                }
                return valeur;
            }
        };
    }

    public static class  Location implements Cloneable{
        private double x;
        private double y;

        public Location(double x, double y) {
            this.x = x;
            this.y = y;
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
        public Location clone() throws CloneNotSupportedException {
            return (Location) super.clone();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Location that)) return false;
            return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
