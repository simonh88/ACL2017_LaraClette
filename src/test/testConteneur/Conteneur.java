/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.udl.acl.conteneur;

/**
 * Contient un ensemble d'objects
 * @author urso
 */
public interface Conteneur {
    /**
     * Ajoute l'objet O dans le conteneur.
     * @param O objet à ajouter
     * @throws ErreurConteneur si l'object est déjà présent,
     * si l'objet est null, ou si le conteneur est plein.
     */
    public void ajouter(Object O) throws ErreurConteneur;

    /**
     * Retire un object égal à O dans le conteneur.
     * @param O objet à retirer
     * @throws ErreurConteneur si l'object n'est pas présent.
     */
    public void retirer(Object C) throws ErreurConteneur;

    /**
     * Redimensionne la capacité du conteneur en gardant tous les objets.
     * @param nouv nouvelle capacité
     * @throws ErreurConteneur si nov est inférieure au nombre d'objets présents
     */
    public void redimensionner(int nouv) throws ErreurConteneur;

    /**
     * Indique si le conteneur contient un object égal à O.
     * @param O l'objet
     * @return true si O est dans le conteneur
     */
    public boolean contient(Object O);

    /**
     * Indique si le conteneur est vide
     * @return true si aucun objet présent
     */
    public boolean estVide();

    /**
     * Indique le nombre d'objets présents
     * @return la taille actuelle du conteneur
     */
    public int taille();

    /**
     * Indique la capacité du conteneur
     * @return la taille maximale du conteneur
     */
    public int capacite();
}
