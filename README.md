# Contacts App

## Overview
The **Contacts App** is a simple Java application that allows users to manage a list of contacts. With this app, users can create, edit, and delete contacts. The contact information is stored in a CSV file.

The app features a simple GUI built using Java Swing.

Link to a youtube video of me explaining the app in finnish:
https://www.youtube.com/watch?v=d48KDCqVPiY

## Features
- Add new contacts.
- Edit existing contacts.
- Remove contacts.
- Save and load contacts from a CSV file.

### Contact Information
Each contact must have the following required fields:
- **Finnish ID**
- **First Name**
- **Last Name**
- **Phone Number**

Optional fields:
- **Address**
- **Email**

## Prerequisites
To run the app, ensure that you have the following installed on your system:
- **Java Development Kit (JDK)** (version 8 or later)
- **Gradle** (latest version recommended)

## Running the App
To run the app, execute the following command from the project directory:
```bash
gradle run
```

This command will compile the source code and launch the application.

## Usage
1. Start the app using the `gradle run` command.
2. Use the GUI to manage your contacts:
   - **Add Contact**: Fill in the required fields and click "Save to CSV".
   - **Edit Contact**: Select a contact from the list, make changes, and click "Save".
   - **Remove Contact**: Select a contact from the list and click "Remove contact".

