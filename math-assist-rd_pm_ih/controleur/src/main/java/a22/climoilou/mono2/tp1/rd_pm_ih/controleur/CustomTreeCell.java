package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;
import a22.climoilou.mono2.tp1.rd_pm_ih.Categorie;
import a22.climoilou.mono2.tp1.rd_pm_ih.Equations;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.TreeItemI;
import javafx.scene.control.*;


public class CustomTreeCell extends TreeCell<TreeItemI> {
    private Label leaf;
    public CustomTreeCell() {
        leaf = new Label();
    }

    /**
     * Gère l'association d'une donnée à une cellule. Les cellules sont réutilisées
     * et elles ne gardent pas les mêmes données tout le temps.
     */
    @Override
    public void updateItem(TreeItemI item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setItem(null);
            setGraphic(null);
        } else {
            if (getTreeItem().getValue() instanceof Categorie) {
                leaf.setText(item.getNom());
                leaf.setStyle("-fx-text-fill: green");
            }else if(getTreeItem().getValue() instanceof Serie){
                leaf.setText(item.getNom());
                leaf.setStyle("-fx-text-fill: red");
            } else if (getTreeItem().getValue() instanceof Equations) {
                leaf.setText(item.getNom());
                leaf.setStyle("-fx-text-fill: orange");
            }
            setGraphic(leaf);
        }


    }


}