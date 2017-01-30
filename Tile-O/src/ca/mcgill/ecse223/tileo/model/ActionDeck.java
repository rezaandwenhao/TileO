/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

// line 24 "../../../../../GameEngine.ump"
public class ActionDeck
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionDeck Associations
  private List<ActionCard> actionCard;
  private Session session;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionDeck(Session aSession)
  {
    actionCard = new ArrayList<ActionCard>();
    boolean didAddSession = setSession(aSession);
    if (!didAddSession)
    {
      throw new RuntimeException("Unable to create actionDeck due to session");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public ActionCard getActionCard(int index)
  {
    ActionCard aActionCard = actionCard.get(index);
    return aActionCard;
  }

  public List<ActionCard> getActionCard()
  {
    List<ActionCard> newActionCard = Collections.unmodifiableList(actionCard);
    return newActionCard;
  }

  public int numberOfActionCard()
  {
    int number = actionCard.size();
    return number;
  }

  public boolean hasActionCard()
  {
    boolean has = actionCard.size() > 0;
    return has;
  }

  public int indexOfActionCard(ActionCard aActionCard)
  {
    int index = actionCard.indexOf(aActionCard);
    return index;
  }

  public Session getSession()
  {
    return session;
  }

  public boolean isNumberOfActionCardValid()
  {
    boolean isValid = numberOfActionCard() >= minimumNumberOfActionCard() && numberOfActionCard() <= maximumNumberOfActionCard();
    return isValid;
  }

  public static int requiredNumberOfActionCard()
  {
    return 32;
  }

  public static int minimumNumberOfActionCard()
  {
    return 32;
  }

  public static int maximumNumberOfActionCard()
  {
    return 32;
  }

  public ActionCard addActionCard()
  {
    if (numberOfActionCard() >= maximumNumberOfActionCard())
    {
      return null;
    }
    else
    {
      return new ActionCard(this);
    }
  }

  public boolean addActionCard(ActionCard aActionCard)
  {
    boolean wasAdded = false;
    if (actionCard.contains(aActionCard)) { return false; }
    if (numberOfActionCard() >= maximumNumberOfActionCard())
    {
      return wasAdded;
    }

    ActionDeck existingActionDeck = aActionCard.getActionDeck();
    boolean isNewActionDeck = existingActionDeck != null && !this.equals(existingActionDeck);

    if (isNewActionDeck && existingActionDeck.numberOfActionCard() <= minimumNumberOfActionCard())
    {
      return wasAdded;
    }

    if (isNewActionDeck)
    {
      aActionCard.setActionDeck(this);
    }
    else
    {
      actionCard.add(aActionCard);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeActionCard(ActionCard aActionCard)
  {
    boolean wasRemoved = false;
    //Unable to remove aActionCard, as it must always have a actionDeck
    if (this.equals(aActionCard.getActionDeck()))
    {
      return wasRemoved;
    }

    //actionDeck already at minimum (32)
    if (numberOfActionCard() <= minimumNumberOfActionCard())
    {
      return wasRemoved;
    }
    actionCard.remove(aActionCard);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean setSession(Session aSession)
  {
    boolean wasSet = false;
    if (aSession == null)
    {
      return wasSet;
    }

    Session existingSession = session;
    session = aSession;
    if (existingSession != null && !existingSession.equals(aSession))
    {
      existingSession.removeActionDeck(this);
    }
    session.addActionDeck(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=actionCard.size(); i > 0; i--)
    {
      ActionCard aActionCard = actionCard.get(i - 1);
      aActionCard.delete();
    }
    Session placeholderSession = session;
    this.session = null;
    placeholderSession.removeActionDeck(this);
  }

}