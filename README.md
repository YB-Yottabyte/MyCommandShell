# Command Shell

Welcome to my Command Shell project! This is a simple command-line tool where you can run shell commands and track how often you use them. Plus, it has some features for generating text using a Markov chain model.

## Features
- Run your favorite shell commands
- Change directories with `cd`
- Keep track of how often you use commands
- Get suggestions for frequently used commands
- Generate text based on a Markov chain model (Under Testing)

## Technologies Used
- Java: The primary programming language used to build the command shell and related functionalities.
- Git: For version control and managing the project's source code.
- Shell: To run commands and interact with the command shell.
- Terminal: To execute the Java program and interact with the command line.

## Getting Started
Here’s how to get this project up and running on your own machine.

### Prerequisites
Make sure you have these installed:
- Java Development Kit (JDK)
- A terminal or command line interface

## Usage
- To exit the command shell, just type exit.
- Feel free to use any standard shell commands and change directories with cd.
- This project tracks how often you use each command and will even suggest commands based on your usage.

## Steps
```bash
Step 1: Clone the Repository
First, make a copy of the project by cloning it from GitHub. Open your terminal and run:

git clone https://github.com/YB-Yottabyte/MAT266.git

Then, Add this Files Back to src: (## Working on Adding this Files back to src in github)
- CommmandShell.class
- CommmandShell.java
- CommmandUsuageStore.class
- CommmandUsuageStore.java
- MarkovChain.class
- MarkovChain.java
- TextGenerator.class
- TextGenerator.java
- pom.xml


Step 2: Go to the src Directory
Next, navigate to the src folder where all the Java files are stored. You can do this by typing:

cd path/to/your/repository/src

Step 3: Compile the Java Files
Now, let’s compile the Java files so we can run them. Just type this in terminal:

javac CommandShell.java MarkovChain.java CommandUsageStore.java TextGenerator.java

This command compiles all the Java files and creates the necessary .class files to run the application.

Step 4: Go Back to the Project Root
Once everything is compiled, head back to the main project directory. You can do that by running:

cd ..

This takes you back up one level, back to the main folder of the project.

Step 5: Make the Command Shell Executable
Now, we need to make the command shell script executable. Run this command:

chmod +x mycommandshell

Step 6: Run the Command Shell
Finally, let’s run the command shell! Just type:

./mycommandshell

You’ll see a prompt where you can start typing your commands.
