# FileExplorerX
General Purpose File Explorer Swing App 


FileExplorerX is a user-friendly Java Swing-based application that allows you to browse the structure and contents of folders on the local file system, FTP-servers, and inside ZIP archives. With FileExplorerX, you can efficiently navigate through your files and directories, making it easier to manage your data.

Key Features:

Browse local file system: Explore your local folders and files effortlessly using the intuitive interface.

FTP-server support: Connect to FTP servers and  access files remotely, streamlining your workflow.

ZIP archive handling: Seamlessly navigate and view the contents of ZIP archives without extracting them.

File Preview: Preview text and image files directly within the application, enhancing productivity.

## Technologies Used

- Java 11
- Swing (Java GUI toolkit)
- Java ZIP archive API
- Apache Commons Net library for FTP support

## Getting Started

### Prerequisites

To run the application locally, you'll need the following:

- JDK 11 (Java Development Kit)
- NetBeans Apache Project (or any other Java IDE of your choice)

### How to Run

1. Clone the repository to your local machine.
2. Open the project in NetBeans (or your preferred Java IDE).
3. Build and run the project.

## Code Overview

### Lazy Loading with Background Threads

The application utilizes background threads to implement lazy loading of files in the tree. This approach ensures a smooth user experience even when dealing with large file systems or remote FTP servers.
