import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LibraryGUI extends JFrame {
    private Library library;

    public LibraryGUI() {
        library = new Library();
        setupFrame();
    }

    private void setupFrame() {
        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panels
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        // Create buttons
        JButton addBookButton = new JButton("Add Book");
        JButton addUserButton = new JButton("Add User");
        JButton borrowBookButton = new JButton("Borrow Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton viewBooksButton = new JButton("View Books");
        JButton viewUsersButton = new JButton("View Users");

        // Add action listeners
        addBookButton.addActionListener(e -> addBook());
        addUserButton.addActionListener(e -> addUser());
        borrowBookButton.addActionListener(e -> borrowBook());
        returnBookButton.addActionListener(e -> returnBook());
        viewBooksButton.addActionListener(e -> viewBooks());
        viewUsersButton.addActionListener(e -> viewUsers());

        // Add buttons to panel
        panel.add(addBookButton);
        panel.add(addUserButton);
        panel.add(borrowBookButton);
        panel.add(returnBookButton);
        panel.add(viewBooksButton);
        panel.add(viewUsersButton);

        // Add panel to frame
        add(panel);
    }

    private void addBook() {
        JTextField idField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();

        Object[] message = {
            "ID:", idField,
            "Title:", titleField,
            "Author:", authorField,
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String author = authorField.getText();
            Book book = new Book(id, title, author);
            library.addBook(book);
        }
    }

    private void addUser() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();

        Object[] message = {
            "ID:", idField,
            "Name:", nameField,
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            User user = new User(id, name);
            library.addUser(user);
        }
    }

    private void borrowBook() {
        JTextField userIdField = new JTextField();
        JTextField bookIdField = new JTextField();

        Object[] message = {
            "User ID:", userIdField,
            "Book ID:", bookIdField,
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Borrow Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int userId = Integer.parseInt(userIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());
            library.borrowBook(userId, bookId);
        }
    }

    private void returnBook() {
        JTextField userIdField = new JTextField();
        JTextField bookIdField = new JTextField();

        Object[] message = {
            "User ID:", userIdField,
            "Book ID:", bookIdField,
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Return Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int userId = Integer.parseInt(userIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());
            library.returnBook(userId, bookId);
        }
    }

    private void viewBooks() {
        StringBuilder booksList = new StringBuilder("Books in library:\n");
        List<Book> books = library.getBooks();
        for (Book book : books) {
            booksList.append("ID: ").append(book.getId()).append(", Title: ").append(book.getTitle()).append(", Author: ").append(book.getAuthor()).append("\n");
        }
        JOptionPane.showMessageDialog(this, booksList.toString(), "View Books", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewUsers() {
        StringBuilder usersList = new StringBuilder("Users in library:\n");
        List<User> users = library.getUsers();
        for (User user : users) {
            usersList.append("ID: ").append(user.getId()).append(", Name: ").append(user.getName()).append("\n");
        }
        JOptionPane.showMessageDialog(this, usersList.toString(), "View Users", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI gui = new LibraryGUI();
            gui.setVisible(true);
        });
    }
}
