Final Laboratory Report: Lab 5 - Android Notes App
1. Project Overview
   The Lab 5 Android Notes App is a fully functional, locally persistent note-taking application. Developed using Java and following an MVC/MVVM hybrid architecture, the application demonstrates advanced local data management, dual-storage synchronization, and secure file I/O operations within the Android ecosystem.

2. Core Features
   CRUD Operations: Users can seamlessly Create, Read, Update, and Delete notes through an intuitive UI utilizing a RecyclerView and a FloatingActionButton.

Primary Storage (SQLite): Serves as the main, fast-access database for everyday note management. It is structured, lifecycle-aware, and optimized for rapid querying.

Secondary Storage (Text File): Acts as a local backup system, saving notes to an internal .txt file (notes_secondary.txt).

One-Tap Synchronization: A dedicated sync button allows users to export all current notes from the SQLite database directly into the secondary text file storage.

3. Technical Implementations & Code Review Fixes
   During the development lifecycle, two critical optimizations were implemented to ensure data integrity and application stability:

Base64 Encoding for Dual Storage: Initially, multi-line notes or notes containing specific delimiter characters (like |||) caused parsing errors in the text file storage. To resolve this, FileStorageHelper.java was updated to wrap the title and content in Base64 encoding before saving, and decode them upon reading. This ensures no line breaks or special characters interfere with the file structure.

Memory Leak Prevention: To prevent SQLite database locks and memory leaks, the onDestroy() lifecycle method was overridden in both MainActivity.java and NoteEditorActivity.java to explicitly close the DatabaseHelper instances when the activities are destroyed.

4. Development Workflow (Session Summaries)
   The project was developed under strict version control and phased AI-assisted sessions:

Session 1: Initial Code Generation
Generated all foundational Java classes (Note, DatabaseHelper, FileStorageHelper, NoteAdapter, MainActivity, NoteEditorActivity).

Built the XML layouts for the main interface, the note editor, and the individual RecyclerView items.

Initialized the local Git repository.

Session 2: Code Review & Refactoring
Conducted a comprehensive code review to identify potential failure points.

Applied the highest-priority fixes: integrated Base64 encoding for file storage and added database closure protocols (onDestroy).

Verified application stability through emulator testing.

Session 3: Documentation
Drafted a comprehensive README.md file detailing the app's features, technology stack, and architecture.

Integrated the documentation into the root project directory to serve as the GitHub repository description.

Session 4: Version Control & GitHub Sync
Executed the final Git operations to synchronize local development with the remote repository.

Staged all files (git add .) and committed the finalized codebase (git commit -m "Add README and apply code review changes").

Pushed the complete, two-commit history to the remote main branch (git push -u origin main), ensuring a clean, auditable project timeline for evaluation.