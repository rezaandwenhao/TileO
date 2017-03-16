/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 1 "../../../../../PlayControllerSM.ump"
public class PlayController
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayController State Machines
  public enum Mode { Ready, Roll, Move, ActionCard, Won }
  private Mode mode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayController()
  {
    setMode(Mode.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getModeFullName()
  {
    String answer = mode.toString();
    return answer;
  }

  public Mode getMode()
  {
    return mode;
  }

  public boolean startGame()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Ready:
        // line 4 "../../../../../PlayControllerSM.ump"
        doStartGame();
        setMode(Mode.Roll);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean load(Game selectedGame)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Ready:
        if (isInGameMode(selectedGame))
        {
        // line 7 "../../../../../PlayControllerSM.ump"
          doLoad(selectedGame);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        if (isInWonMode(selectedGame))
        {
        // line 10 "../../../../../PlayControllerSM.ump"
          doLoad(selectedGame);
          setMode(Mode.Won);
          wasEventProcessed = true;
          break;
        }
        if (isNotInGameOrWonMode(selectedGame))
        {
        // line 14 "../../../../../PlayControllerSM.ump"
          doLoad(selectedGame);
          setMode(Mode.ActionCard);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean rollDie()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Roll:
        // line 19 "../../../../../PlayControllerSM.ump"
        possibleMoves = doRollDie();
        setMode(Mode.Move);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean land(Tile tile)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case Move:
        if (isNormalTile(tile))
        {
        // line 24 "../../../../../PlayControllerSM.ump"
          doLandTile(tile);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        if (isWinTile(tile))
        {
        // line 27 "../../../../../PlayControllerSM.ump"
          doLandTile(tile);
          setMode(Mode.Won);
          wasEventProcessed = true;
          break;
        }
        if (isActionTile(tile))
        {
        // line 30 "../../../../../PlayControllerSM.ump"
          doLandTile(tile);
          setMode(Mode.ActionCard);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playRollDieActionCard()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isRollDieActionCard())
        {
        // line 37 "../../../../../PlayControllerSM.ump"
          possibleMoves = doPlayRollDieActionCard();
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playConnectTilesActionCard(Tile tile1,Tile tile2)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isConnectTilesActionCard())
        {
        // line 42 "../../../../../PlayControllerSM.ump"
          doPlayConnectTilesActionCard(tile1, tile2);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playRemoveConnectionActionCard(Connection c)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isRemoveConnectionActionCard())
        {
        // line 47 "../../../../../PlayControllerSM.ump"
          doPlayRemoveConnectionActionCard(c);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playTeleportActionCard(Tile tile)
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isTeleportAndNormalTile(tile))
        {
        // line 50 "../../../../../PlayControllerSM.ump"
          doPlayTeleportActionCard(tile);
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        if (isTeleportAndWinTile(tile))
        {
        // line 53 "../../../../../PlayControllerSM.ump"
          doPlayTeleportActionCard(tile);
          setMode(Mode.Won);
          wasEventProcessed = true;
          break;
        }
        if (isTeleportAndActionTile(tile))
        {
        // line 56 "../../../../../PlayControllerSM.ump"
          doPlayTeleportActionCard(tile);
          setMode(Mode.ActionCard);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean playLoseTurnActionCard()
  {
    boolean wasEventProcessed = false;
    
    Mode aMode = mode;
    switch (aMode)
    {
      case ActionCard:
        if (isLoseTurnActionCard())
        {
        // line 61 "../../../../../PlayControllerSM.ump"
          doPlayLoseTurnActionCard();
          setMode(Mode.Roll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setMode(Mode aMode)
  {
    mode = aMode;
  }

  public void delete()
  {}

}