# Week 3 Project — Sudoku Web App

### Context
I lost a bet with a friend and he challenged me that for the next 52 weeks, during weekends I need to create from scratch a new project.

The rules are the following:
*   Each Friday night, me and him will talk about what project I need to do.
*   Mostly he will decide for me, but I’m allowed to suggest and do my own ideas if he agrees on.
*   Once the project is decided, he will tell me if I’m allowed to work Saturday and Sunday, or only Sunday.
(Surely this won’t backfire at some point by underestimating a project, right? 😅)

For this week we decided on a classic Sudoku game, being allowed to work on it both Saturday and Sunday because of the frontend part.

### About the project
This project is a classic Sudoku game built as a web application with **Java Spring Boot** backend and **JavaScript** frontend.

The backend is responsible for the core game logic, exposing a simple REST API for generating new puzzles, validating solutions, and providing the full solution on demand. Puzzle generation is achieved using a backtracking algorithm to create a complete, valid Sudoku board, from which a number of cells are removed based on the selected difficulty.

The frontend is a single HTML page that dynamically renders the Sudoku grid and handles all user interactivity. It communicates with the Spring Boot server using the standard `fetch` API to get game data and verify results, providing a seamless single-page application experience without any page reloads.

### Features

*   **Unique Puzzle Generation:** Start a new game at any time with three difficulty levels (Easy, Medium, Hard).

*   **Interactive Grid:** Click tiles to select them and input numbers. Original puzzle numbers are black and cannot be changed, while user-added numbers are red and can be edited or deleted.

*   **Dual Input Modes:**
    *   **Add Mode:** Enter a single, definitive number in a square. This clears any notes.
    *   **Edit Mode:** Let's the user add multiple small numbers as notes into any empty square to help solve the puzzle.

*   **Solution Reveal:** The 'Show Solution' button fills in only the incorrect or empty cells with a blue number, leaving the player's correct answers highlighted in red for easy review.

*   **Instant On-Page Feedback:** Once the grid is full, the application automatically verifies the solution and sends a message: "Congratulations!" or "Something is not quite right..."(and yes, this is a world of warcraft reference). 
