/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 28 "../../../../../GameEngine.ump"
public class ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionCard State Machines
  public enum ActionType { extraTurn, addConnection, removeConnection, changeTile, loseTurn }
  private ActionType actionType;

  //ActionCard Associations
  private ActionDeck actionDeck;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionCard(ActionDeck aActionDeck)
  {
    boolean didAddActionDeck = setActionDeck(aActionDeck);
    if (!didAddActionDeck)
    {
      throw new RuntimeException("Unable to create actionCard due to actionDeck");
    }
    setActionType(ActionType.extraTurn);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getActionTypeFullName()
  {
    String answer = actionType.toString();
    return answer;
  }

  public ActionType getActionType()
  {
    return actionType;
  }

  public boolean setActionType(ActionType aActionType)
  {
    actionType = aActionType;
    return true;
  }

  public ActionDeck getActionDeck()
  {
    return actionDeck;
  }

  public boolean setActionDeck(ActionDeck aActionDeck)
  {
    boolean wasSet = false;
    //Must provide actionDeck to actionCard
    if (aActionDeck == null)
    {
      return wasSet;
    }

    //actionDeck already at maximum (32)
    if (aActionDeck.numberOfActionCard() >= ActionDeck.maximumNumberOfActionCard())
    {
      return wasSet;
    }
    
    ActionDeck existingActionDeck = actionDeck;
    actionDeck = aActionDeck;
    if (existingActionDeck != null && !existingActionDeck.equals(aActionDeck))
    {
      boolean didRemove = existingActionDeck.removeActionCard(this);
      if (!didRemove)
      {
        actionDeck = existingActionDeck;
        return wasSet;
      }
    }
    actionDeck.addActionCard(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ActionDeck placeholderActionDeck = actionDeck;
    this.actionDeck = null;
    placeholderActionDeck.removeActionCard(this);
  }

}