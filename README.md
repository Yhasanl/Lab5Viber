# Lab5Viber - Android Notes App

This is a complete Android application developed for a laboratory assignment. It is a fully functional Notes app that demonstrates local data persistence using multiple storage methods and data synchronization.

## 🚀 Key Features

* **CRUD Operations:** Create, Read, Update, and Delete notes seamlessly through an intuitive interface.
* **Primary Storage (SQLite):** Fast, structured, and lifecycle-aware database storage for everyday note management.
* **Secondary Storage (Text File):** Notes are securely backed up to a local `.txt` file. All text data is Base64 encoded to prevent formatting errors and ensure data integrity.
* **Synchronization:** A one-tap sync feature to easily export all current notes from the primary SQLite database into the secondary Text File storage.

## 🛠️ Technology Stack

* **Language:** Java
* **Platform:** Android SDK
* **Architecture:** MVC / MVVM hybrid
* **UI Components:** XML, RecyclerView, FloatingActionButton
* **Database:** SQLite (`SQLiteOpenHelper`)
* **File I/O:** Android Internal Storage (`FileInputStream` / `FileOutputStream`)