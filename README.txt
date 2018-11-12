#How to play
	what about Eclipse
	Step by step navigtion
	commands available
		Messaging and request/trade
#File compilation. Error checking. 
		   Initialization.
#Added classes : Riddle, StringPairCompare.
		 Talk... etc.


#	Javier Mayol 	ACCC: cmayol
#  	Neel Patel 	NETID : npate315	UIN : 674004711
# 	Keval Patel

Project  Game version 4.0
——————————————————
CS-342 Software Design fall 2018 term project part 4. Implementation of the semester project.
Group project that involves having the files of every member work togetehr to run and play
this game.

________________________________________
### Prerequisites

Java Standard Edition (SE) 7, JDK 1.7 or later versions.

________________________________________
### Compiling and running

At least the following files are needed to create the program:

AI.java			Exit.java		Message.java		Printer.java		Use.java
Artifact.java		Game.java		Move.java		keyboardScanner.java
Character.java		GameTeaster.java	MysticCity4.txt		Request.java		makefile
CleanLineScanner.java	Get.java		Riddle.java		word_count.text
DecisionMaker.java	Go.java			NPC.java		StringPairCompare.java
Direction.java		Inventory.java		Place.java		Trade.java
Drop.java		Player.java		UI.java

The program takes a .gdf file specially formatted to initialize the different classes. In our implementation
we have changed the format from .gdf to .txt. The content of MysticCity4.txt follows the same GDF form.
Use makefile to link the files. Simply type “make” to the command line and run the program by typing 
“java GameTester” to the command line as well. If you’re using an IDE, refer to the instructions given by 
the developer to compile makefiles.

----------------------------------------
## Input File

A file .gdf is required to run the program. The GameTester class takes care of reading and formatting such file.
The Clean Line Method in CleanLineScanner class checks if a user has inputed a file that follows the GDF conventions.
The file name for the game is hardcoded in case the user do not want to input another file. To correctly initialize
a GDF file must be provided if MysticCity4.txt is not found, the user has one more opportunity to upload a file by 
inputing the file name when prompted.

________________________________________
##More than 2 players

The game supports up to 15 players. The command line takes three arguments in order to do this. First argument is the
the call to load the executable 'java GameTester' the second is the name of the file 'MysticCity4.txt' or any other 
GDF file. The third argument is the number of players. A call to createNewPlayer() in the Player class in made when 
minPlayer() method in Player class identify a request for more than 2 players.

----------------------------------------
## How to play

Ideal screen dimensions at least (90 X 40)
The game takes command line arguments to navigate through it. 
See ##GAME COMMANDS section below for game commands.

**************************** UP Date ^ up ***************************
----------------------------------------
##GAME COMMANDS:

  GO [DIRECTION]:
  	Direction is one of the cardinal positions that a player can move in a game, e.g. south.
  'GO south easth-south' or GO ses' are commands that are equivalent.

  USE [KEY ARTIFACT] [DIRECTION]:
	If a direction is locked in the current place and the player wants to try a key artifact, the
  'USE' command needs to follow the order 'USE[SPACE][KEY ARTIFACT][SPACE][DIRECTION]'. In other words,
  the command 'USE' needs two arguments to open a locked direction with a key: first argument 'key 
  artifact' second argument 'locked direction'. This will allowed the player to be immediatelly sent to
  the place where the now open direction leads to. SO, the next time the player has it's turn in the 
  game, the current place will have changed to the new current place formerly closed. 

  USE open [NAME OF ARTIFACT]:
    	In order to use an atrifact with a negative mobility the command USE open [NAME OF ARTIFACT] can be 
  used to retrieve the points in the value variable of the artifact and add those points to the player's
  points variable. First argument ignores cases the second argument is lower case open.

  TALK [NAME OF PLAYER]:
	If the player wants to send a message to another player in the current palce, the command 'Talk' 
  populates the string message variable in the Character class with a message that indicates from who 
  is the message coming. The message is a one liner that takes stdin strings using the keyboard.getInput()
  method. The player receives a notification with the message that gets printed in the terminal as soon as is 
  her/his turn. If the player wants to reply, the Talk command can be used again by the replier. The 
  notification() method is declared inside the players loop in the play() method in the Game class so 
  each player get the messages from other player. It supports multiple massages, they simply get added
  to the string message instance in Character class.  

  ASK [NAME OF REQUESTED PLAYER]:	
	Allows you to communicate with another player in the same place. Using method
  public Character listening(String name) from the Place class we can identify if there is another player
  in the current place. (Also, lets you know if you're in the current place). 'ASK' is the first step if 
  the player wants to trade an artifact with another player (or with itself). 'ASK' takes as argument a 
  string that indicates the name of the player you want to trade artifacts with. This command uses the 
  makeMove() method of the player to pass its arguments to the Request class object in UI class.
  The Request object in UI class execute a step by step application of the request. First the user 
  indicates the artifact offer and then indicates the artifact the user wants from the other player.
  Request class sends a message from the requester to target player indicating the interest of the requester
  in an artifact. Then to conclude the transaction the requested player enters TRADE "NAME OF REQUESTER"
  in the command line and answers if is or not interested in the offer.

  TRADE [NAME OF REQUESTER PLAYER]: 
   	Once a request has been made through the 'ASK' command, the next step in the trade process is the
  'TRADE' command that will allow the person being requested to answer whether the offer is accepted or not.
  If accepted, the Trade class instantiate Drop and Get objects from the Drop and Get classes accordingly
  to trade the artifacts. If the offer is not accepted by the player being requested, the requester receives
  a messag stating that the offer was not accepted. Impotant, both players need to be in the same place for the 
  transaction to be made and completed.

----------------------------------------
## Design Patterns:
	Singleton
	Command


