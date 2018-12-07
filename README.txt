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
we have changed the format from .gdf to .txt. The content of MysticCity5.txt follows the same GDF form.
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
                     
                         java GameTester filename numPlayer
		ex:
			java GameTester MysticCity5.txt 5
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

----------------------------------------
##GAME COMMANDS:
  
  GUI 1, GUI 2, GUI 3 :
	Javier Mayol- GUI 1 to work on graphical userinterface 1
	keval Patel-  GUI 2 to work on graphical userinterface 2
	Neel Patel -  GUI 3 to work on graphical userinterface 3 
		on GUI 3 there are we created labels of GO, USE, DROP, GET and textfield to enter the instruction 
			i.e 
				if you enter N next to GO lable GO NORTH command will execute
		there are button fot LOOK, EXIT, INVE 
		Informtion about player and inventory will display on textArea

  GO [DIRECTION]:
  	Direction is one of the cardinal positions that a player can move to in a game, e.g. south.
  "GO south-easth south" or "GO ses" are commands that are equivalent.

  GET [NAME OF ARTIFACT]
    This command puts the artifacts in the current place into the player's inventory.

  INVENTORY
    By typing INVENTORY or INVE or simply I, the player can see what artifacts are in the inventory.

  LOOK
    Display information about the current place.

  USE [NAME OF ARTIFACT]
    Allow the user to use an artifact.

  USE [KEY ARTIFACT] [DIRECTION]:
	If a direction is locked in the current place and the player wants to try a key artifact, the
  'USE' command needs to follow the order 'USE[SPACE][KEY ARTIFACT][SPACE][DIRECTION]'. In other words,
  the command 'USE' needs two arguments to open a locked direction with a key: first argument 'key 
  artifact' second argument 'locked direction'. This will allow the player to be immediately sent to
  the place where the now open direction leads to. So, the next time the player has the turn in the 
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
	Singleton :
	Command : Move
	Prototype : ArtifactUse.java
	Bridge : For GUI implemetation.
----------------------------------------
## Riddle class

The Ogre can now challenge a player with a "riddle". If a player finds the Ogre, he is needs to answer a 
question in order to continue. If the player does not has anything it is possession the Ogre will leave
her/him alone. If the player fails the answer, the Ogre takes everything from the players inventory. If the
player wins and the Ogre has somthing in the inventry, then the player gets to choose one artifact from the 
Ogre.

----------------------------------------
## HollyWand.java

Moves the character to a randomly picked place.

_________________________________________
##Contribution

Keval Patel 

Neel Patel
	Creation and implementation  of IO.java and UserIntefcae.java
	Following methods in Place Class:
  	  public boolean checkPlayerInstance(Character obj)
   	  public Place goToRoom(Character ponpc, int ID)

Javier Mayol
	Implementation of TextInterface.
	GUI_1


Appended below is the log from the repository with
all commits done from the time created through
December 6.
***********************************************
commit a5e4a56985d1abc2c2d0febcf20bcd76922225e9
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Thu Dec 6 16:11:01 2018 -0600

    Added inventory artifacts buttons.

commit 5516871c3ff53cee8ddda30de188225895b07e51
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Thu Dec 6 12:43:23 2018 -0600

    Adding enter and other buttons

commit 91f15395a6158c84bd358e1af43eee7a04d4929e
Merge: d5ebe04 73bac00
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Thu Dec 6 12:19:57 2018 -0600

    Merge branch 'master' of https://github.com/npate315/cs342-hw4

commit d5ebe04e79a4416e1530dd439ded7309d9d078ee
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Thu Dec 6 12:17:43 2018 -0600

    Adding events to send commands to stdin.

commit 73bac0001114f97e3cec731baacf232ff46922d5
Author: kevP00 <35612324+kevP00@users.noreply.github.com>
Date:   Thu Dec 6 12:04:46 2018 -0600

    Add files via upload

commit 26bc9f6623d0a1de04f5b39cb67168d08d0e8693
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Thu Dec 6 11:31:03 2018 -0600

    Adding events to buttons.

commit b20ae717aed1acc6ce71d55c4de3b0bde099334a
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Thu Dec 6 10:12:26 2018 -0600

    Attempt changing the layout.

commit 2b9038e3cb0d2ca459bdb256c012b870c3623f58
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Dec 5 20:51:37 2018 -0600

    Adding a text filed for input.

commit 03ffe30a9219477f5bc95c6ec5deadc9dd56bdba
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Dec 5 20:24:23 2018 -0600

    Restored Riddle class to its former state.

commit a76e11ffe7750aea7d4f4657af8419d1ed28b00b
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Dec 5 15:10:01 2018 -0600

    Changing All the functionality of Riddle :(

commit 5c16c329e37fd976fb982fe7fa092e89493ef2f8
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Dec 5 12:57:36 2018 -0600

    Fixes gui command argument to add GUI 1, GUI 2, GUI 3

commit dd717c7b7804a838f15642805b7881c685e07bea
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Dec 5 12:51:51 2018 -0600

    working GUI funstionalities

commit 1e70a0971cfbcc772f2ae1313fd13e0f01b817ee
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Dec 5 09:58:48 2018 -0600

    Removed externalDisplay() method

commit 99d1240a63124f0b2a65171b57aacc4a51133f37
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Dec 5 03:42:29 2018 -0600

    Trying to add action to buttons.

commit 2d08d26033b97473f8c2e1551bc4977cbd4be656
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Dec 5 00:07:54 2018 -0600

    New grid display of frame. 2 rows 2 col

commit 9e1b7420d949757b41bf5cf230510bd02a4ea697
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Tue Dec 4 23:17:27 2018 -0600

    Changing GUI style

commit d1ccfa34c09da2c1539b0cdd42df686035fae78f
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Tue Dec 4 20:31:59 2018 -0600

    fixing display of players

commit 69abf8d8f7db8daf047dfe69af3b4b2d2839e45a
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Tue Dec 4 00:57:22 2018 -0600

    Changing GUI_1 appearance.

commit 9834fe8cfecfd6d2a9e72998956bf7cf100db627
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Mon Dec 3 18:39:52 2018 -0600

    GUI stage 1: just a frame with buttons.

commit 76f04c787d07e4d15f58c962e7d36190aa666028
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Mon Dec 3 18:21:12 2018 -0600

    Working on GUI

commit 65e7707ad3afc103018e12b5f13f25821293ad26
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Sun Dec 2 13:57:26 2018 -0600

    Included a displayPrompt() method in UserInterface

commit 4a782e69940f1b9c6fa7a8b34c030e72bd224836
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Sat Dec 1 14:12:54 2018 -0600

    Implemented IO in all classe that needed it.

commit 058ad67010c1ae157b51ea40441e8e71253a6534
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Sat Dec 1 00:59:40 2018 -0600

    Added a constructor for IO to initialize implementor

commit a32fdfcfcfd2fa04808fa89c8fe3beb58ebc14ba
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Sat Dec 1 00:27:56 2018 -0600

    Implement IO, UserInterface, and TextInterface

commit ed33d4d6ed6e0bcb123e6a9421f22fed0495cc45
Author: Neel Patel <npate315@uic.edu>
Date:   Sat Nov 24 15:10:33 2018 -0600

    add new IO class and UserInterface

commit ed9839e89a31cab105415346597a62ced8c5d77e
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Nov 14 05:49:31 2018 -0600

    Adding all files

commit d3231b2b41c42ba0f2b4f45041c870633c5f4c18
Merge: dcf7151 9148794
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Nov 14 05:05:29 2018 -0600

    Adding files local

commit dcf71516a2490bba47e5fb5afd0f6de19b631dcc
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Nov 14 04:50:25 2018 -0600

    Added ArtifactUse class etc

commit 9148794f1d448b9537219bf7f37d838b7f372d3c
Author:  <Neel@Neel-PC.localdomain>
Date:   Tue Nov 13 21:32:36 2018 -0600

    print group info before game information prints

commit 115ac275a753190415433314c64be879f816819b
Author: npate315 <32661442+npate315@users.noreply.github.com>
Date:   Tue Nov 13 21:01:40 2018 -0600

    Update README.txt

commit c989e29752f9314566b96819e980d7c21515e20c
Author:  <Neel@Neel-PC.localdomain>
Date:   Tue Nov 13 20:29:16 2018 -0600

    create new Pet class which inherits NPC class

commit 963db064fd29ca6ef2c41906998e91db5b3ad29d
Author: kevP00 <35612324+kevP00@users.noreply.github.com>
Date:   Tue Nov 13 18:31:30 2018 -0600

    Add files via upload

commit bfdb72e68385780aecd2794b51b0a8b3a23f59e6
Author: kevP00 <35612324+kevP00@users.noreply.github.com>
Date:   Tue Nov 13 18:11:27 2018 -0600

    Update Place.java

commit aaad872493be0eda24e8297581ede83acdbe76ed
Author:  <Neel@Neel-PC.localdomain>
Date:   Tue Nov 13 15:10:55 2018 -0600

    compitable with version 5.0

commit 63f1bcc4cbf460d6e6583064538d7dcce9f532b2
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Mon Nov 12 09:50:48 2018 -0600

    Added information to README.txt : GAME COMMANDS.

commit 244639a2cff7fc66498d5e621bf2e6b45bfe3743
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Sun Nov 11 13:16:16 2018 -0600

    Fixed createNewPlayer name .

commit 4aba8db14fa35008bfc59cd63f226b31a8df3b52
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Sun Nov 11 12:53:05 2018 -0600

    Included age in printAll method.

commit d83ad7ebac3bf8cbced23075c5430a2c760e87a3
Merge: 59fd7b0 5aeb415
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Sat Nov 10 20:16:47 2018 -0600

    doing merging.

commit 59fd7b0c2c834203290f3db9886485fade6689b1
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Sat Nov 10 20:03:16 2018 -0600

    Changed README file

commit 5aeb415ea0e68133df0a0d195575f0c1dc7096bd
Author:  <Neel@Neel-PC.localdomain>
Date:   Sat Nov 10 18:19:17 2018 -0600

    fix some bugs

commit c1ffa2604157643e5bc6441371de4d07e6bd3ade
Merge: 6d7047b bf1f5c6
Author:  <Neel@Neel-PC.localdomain>
Date:   Sat Nov 10 16:31:44 2018 -0600

    updated

commit 6d7047be54731d612063d3a8d14bcb7e057775b6
Author:  <Neel@Neel-PC.localdomain>
Date:   Sat Nov 10 16:20:29 2018 -0600

    print available direction for LOOK command

commit 093f4c5e5a5c4ae002fc22bfb5df9e176918243a
Author:  <Neel@Neel-PC.localdomain>
Date:   Sat Nov 10 15:03:39 2018 -0600

    update the age for playing character and data file

commit bf1f5c68d2f87f09434f46680a81e17056b9009d
Merge: d3c920d 7af2ab7
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Thu Nov 8 17:44:46 2018 -0600

    trying one more time. Requst and trade fixed.

commit d3c920df9e489ab5810d4b4e09deba625413fcb5
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Thu Nov 8 17:42:48 2018 -0600

    Fix bug with request trade classes.

commit 7af2ab72ea8d54f65413469a556e752227a32e5e
Author:  <Neel@Neel-PC.localdomain>
Date:   Wed Nov 7 22:12:14 2018 -0600

    create a new data file with version 5.0

commit afb133b2cc4b552bb2b67f86090a1ca6375a578f
Author:  <Neel@Neel-PC.localdomain>
Date:   Wed Nov 7 21:49:26 2018 -0600

    add players age

commit 68b08ed48086c8b93e0bd963e1b7591e4e409235
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Wed Nov 7 11:37:31 2018 -0600

    implemented a factory method for character using iterator.

commit a74d4d3956ef652d31aa6280de0c5b1245f56583
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Tue Nov 6 12:14:58 2018 -0600

    Changed Clean line scanner to add a get clean line with string argument.

commit 56e366e614ef1d4ebedb03d9ec0f89fde9faa093
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Tue Nov 6 11:02:05 2018 -0600

    changes to Riddle class added. Prize method.

commit a8503bc0db4b28ed14a23debfbac19cee440f33f
Author: javierMayol <carlosjmayol@gmail.com>
Date:   Tue Nov 6 09:50:48 2018 -0600

    Files put together and running.

commit 81cd0e7fb1d5dbc2a4ac1c73ab29863c6aa4327f
Author: npate315 <32661442+npate315@users.noreply.github.com>
Date:   Sat Nov 3 16:58:12 2018 -0500

    Add files via upload

commit c64efdad6db7cc3de287b0f8720a2a04530dd2bc
Author: javierMayol <43070162+javierMayol@users.noreply.github.com>
Date:   Fri Nov 2 18:43:53 2018 -0500

    HW4_cmayol2
    
    files for HW4

commit 53cc765948590411e09e5a75238e52f0a9b00261
Author: kevP00 <35612324+kevP00@users.noreply.github.com>
Date:   Fri Nov 2 10:37:19 2018 -0500

    Add files via upload

commit fc4682e25976b395708037aed1ba82bf684d1582
Author: npate315 <32661442+npate315@users.noreply.github.com>
Date:   Fri Nov 2 10:25:02 2018 -0500

    Version proj-3
