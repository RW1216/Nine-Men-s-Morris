package com.corgi.ninemensmorris.Game;

import com.corgi.ninemensmorris.Actions.ActionList;
import com.corgi.ninemensmorris.Players.Player;
import com.corgi.ninemensmorris.Actions.Action;

/**
 *
 * Token is the object that the Players can interact with on the board during the Game
 *
 */

public class Token {


    private Player owner;
    private ActionList allowableActions;
    private Position position;
    public boolean isPlaced;

    /**
     * Constructor to create a new token
     * @param owner The Player that is creating the token
     * @param position The selected position to perform an action
     */
    public Token(Player owner, Position position) {
        this.owner = owner;
        this.position = position;
        this.allowableActions = new ActionList();
    }

    /**
     * A getter method to retrive the list of allowable actions on the token
     * @return List of actions
     */
    public ActionList getAllowableActions() {
        return this.getAllowableActions();
    }

    /**
     * Add an action into the ActionList of the token
     * @param action The Action to be added to the token
     */
    public void addAction(Action action) {
        if(action == null){
            throw new NullPointerException("Unable to add a null action!");
        }
        this.allowableActions.add(action);
    }

    /**
     * Remove an action in the ActionList of the token
     * @param action The Action to be removed
     */
    public void removeAction(Action action){
        if(action == null){
            throw new NullPointerException("Unable to add a null action!");
        }
        this.allowableActions.remove(action);
    }

    /**
     * Method to clear the ActionList of the token by creating a new ActionList
     */
    public void clearActions(){
        this.allowableActions = new ActionList();
    }

    /**
     * Get the owner of the token
     * @return a Player object
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Method to retrive the current position of the selected token
     * @return A Position object of the selected token
     */
    public Position getPosition() { return position; }

    /**
     * A setter method to set the position of the selected token
     * @param position The selected Position on the board
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
