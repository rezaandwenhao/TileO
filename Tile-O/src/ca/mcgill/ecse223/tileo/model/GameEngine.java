/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 3 "../../../../../GameEngine.ump"
public class GameEngine
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameEngine Associations
  private Session session;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameEngine(Session aSession)
  {
    if (aSession == null || aSession.getGameEngine() != null)
    {
      throw new RuntimeException("Unable to create GameEngine due to aSession");
    }
    session = aSession;
  }

  public GameEngine(int aTotalTurnsForSession, Board aBoardForSession, Player... allPlayerForSession)
  {
    session = new Session(aTotalTurnsForSession, aBoardForSession, this, allPlayerForSession);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Session getSession()
  {
    return session;
  }

  public void delete()
  {
    Session existingSession = session;
    session = null;
    if (existingSession != null)
    {
      existingSession.delete();
    }
  }

}