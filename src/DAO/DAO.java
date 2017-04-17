package DAO;
import Models.Eleve;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @param <T> type generique ; a remplacé par Eleve,Instituteur,Classe,etc...
 */
public interface DAO<T> {

    /**
     * Permet d'avoir tous les tuples
     * @return un ArrayList de T elements , null si aucun tuple trouvé
     */
    public ObservableList<T> getAll();

    /**
     * Permet de supprimer tous les tuples
     * @return true si l'operation de suppression est effectuée sans erreur , false sinon
     */
    public boolean      delAll();

    /**
     * Permet de crée une instance dans la base de donnée.
     * @param instance l'instance a insérée dans la base de donnée.
     * @return true si l'insertion est effectuée sans erreur , false sinon
     */
    public int      create(T instance);

    /**
     * Permet de rechercher un tuple de type T a partir de son ID
     * @param id l'identifiant a recherchée
     * @return une instance de type T qui a le id recherchée
     */
    public T            find(int id);

    /**
     * Permet de mettre a jour un tuple
     * @param instance l'instance du tuple a inserer
     * @return true si la mise a jour est effectuée sans erreur , false sinon
     */
    public boolean      update(T instance);

    /**
     * Permet de supprimer un tuple
     * @param id l'identifiant a supprimer
     * @return true si la suppression est effectuée sans erreur , false sinon
     */
    public boolean      delete(int id);

    
    
    


}
