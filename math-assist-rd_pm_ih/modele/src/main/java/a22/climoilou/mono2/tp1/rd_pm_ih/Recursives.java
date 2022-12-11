package a22.climoilou.mono2.tp1.rd_pm_ih;

import javafx.scene.control.TreeItem;

import java.util.List;

public class Recursives {

    public void remplirArbreSeries(TreeItem<TreeItemI> root, TreeItem item, Serie s) {
        remplirArbreSeriePureRec(root, item, s);
    }

    private void remplirArbreSeriePureRec(TreeItem<TreeItemI> actualNode, TreeItem item, Serie s) {

        String node = String.valueOf(actualNode.getValue().getNom());
        String cat = s.getCategorie().getNom();
        if (node.equals(cat)) {
            actualNode.getChildren().add(item);
        } else {

            if (actualNode.nextSibling() != null) {
                remplirArbreSeriePureRec(actualNode.nextSibling(), item, s);
            }

            if (!actualNode.isLeaf() && actualNode.getValue() instanceof Categorie) {
                remplirArbreSeriePureRec(actualNode.getChildren().get(0), item, s);
            }
        }

    }

    public void remplirArbreEquation(TreeItem<TreeItemI> root, TreeItem item, Equations e) {
        remplirArbreEquationPureRec(root, item, e);
    }

    private void remplirArbreEquationPureRec(TreeItem<TreeItemI> actualNode, TreeItem item, Equations e) {

        String node = String.valueOf(actualNode.getValue().getNom());
        String cat = e.getCategorie().getNom();
        if (node.equals(cat)) {
            actualNode.getChildren().add(item);
        } else {

            if (actualNode.nextSibling() != null) {
                remplirArbreEquationPureRec(actualNode.nextSibling(), item, e);
            }

            if (!actualNode.isLeaf() && actualNode.getValue() instanceof Categorie) {
                remplirArbreEquationPureRec(actualNode.getChildren().get(0), item, e);
            }
        }

    }

    public int calculNombreEquationTotal(List<Equations> listeEquationsEnBd) {
        return calculNombreEquationTotalPureRec(listeEquationsEnBd, 0);
    }

    private int calculNombreEquationTotalPureRec(List<Equations> listeEquationsEnBd, int position) {
        int retVal = 0;

        if (position != listeEquationsEnBd.size()) {
            retVal = 1 + calculNombreEquationTotalPureRec(listeEquationsEnBd, position + 1);
        }

        return retVal;
    }

    public int calculNombreEquationEtSerieMemeCategorie(List<Serie> listeSeriesEnBd, List<Equations> listeEquationsEnBd, Categorie c) {
        int retVal = 0;

        retVal += calculNombreEquationEtSerieMemeCategoriePureRec(listeSeriesEnBd, listeEquationsEnBd, c, 0, 0);

        return retVal;
    }

    private int calculNombreEquationEtSerieMemeCategoriePureRec(List<Serie> listeSeriesEnBd, List<Equations> listeEquationsEnBd, Categorie c, int positionSerie, int positionEquation) {
        int retVal = 0;

        if (positionSerie < listeSeriesEnBd.size()) {

            if (!listeSeriesEnBd.get(positionSerie).getCategorie().getNom().equals(c.getNom())) {
                retVal = calculNombreEquationEtSerieMemeCategoriePureRec(listeSeriesEnBd, listeEquationsEnBd, c, positionSerie + 1, positionEquation);
            }
            if (listeSeriesEnBd.get(positionSerie).getCategorie().getNom().equals(c.getNom())) {
                retVal = 1 + calculNombreEquationEtSerieMemeCategoriePureRec(listeSeriesEnBd, listeEquationsEnBd, c, positionSerie + 1, positionEquation);
            }
        }

        if (positionSerie == listeSeriesEnBd.size() && positionEquation < listeEquationsEnBd.size()) {
            if (!listeEquationsEnBd.get(positionEquation).getCategorie().getNom().equals(c.getNom())) {
                retVal = calculNombreEquationEtSerieMemeCategoriePureRec(listeSeriesEnBd, listeEquationsEnBd, c, positionSerie, positionEquation + 1);
            }
            if (listeEquationsEnBd.get(positionEquation).getCategorie().getNom().equals(c.getNom())) {
                retVal = 1 + calculNombreEquationEtSerieMemeCategoriePureRec(listeSeriesEnBd, listeEquationsEnBd, c, positionSerie, positionEquation + 1);
            }
        }


        return retVal;
    }

    private int CalculerNombreDeSousCategoriePureRec(Categorie categorie, int index) {
        int nmbr = 0;
        if (!(categorie.getSousCategorie().isEmpty())) {
            nmbr += 1 + CalculerNombreDeSousCategoriePureRec(categorie.getSousCategorie().get(index), 0);
            if (categorie.getSousCategorie().size() > 1) {
                nmbr += 1 + CalculerNombreDeSousCategoriePureRec(categorie.getSousCategorie().get(index), 0);
                index++;
            }
        } else {
            return nmbr;
        }
        return nmbr;
    }

    public int CalculerNombreDeSousCategorie(Categorie categorie, int i) {

        return CalculerNombreDeSousCategoriePureRec(categorie, i);
    }

    public int calculDataDansSerie(List<Serie> listeSeriesEnBd, int n) {
        return calculDataDansSerieRec(listeSeriesEnBd, n, 0);
    }

    private int calculDataDansSerieRec(List<Serie> listeSeriesEnBd, int n, int index) {
        int retVal = 0;
        if (listeSeriesEnBd.size() > index) {
            if (listeSeriesEnBd.get(index).getDonnees().size() >= n) {
                retVal += 1 + calculDataDansSerieRec(listeSeriesEnBd, n, ++index);
            } else {
                retVal += calculDataDansSerieRec(listeSeriesEnBd, n, ++index);
            }
        }
        return retVal;
    }
}
