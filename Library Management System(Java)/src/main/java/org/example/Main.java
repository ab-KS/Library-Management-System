package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//library management system; to be made here


class Librarian{
    void addBook(String bookName, String authorName, Library Library){
        Book newBook = new Book(bookName,authorName, Library);
    }
    void addMember(String memberName, String phoneNumber, int age ,Library Library){
        Member newMember = new Member(memberName, phoneNumber, age, Library);
    }
    //    void removeMember(){}
//
//;
//    };
    void viewAllBooks(Library Library){
        for(int i=0; i< Library.getNumBooks();i++){
            System.out.println("----------------------");
            System.out.printf("BOOK ID- %s\n", Library.books.get(i).ID);
            System.out.printf("Name: %s\n", Library.books.get(i).Name);
            System.out.printf("Author: %s\n", Library.books.get(i).Author);
        }
        System.out.println("----------------------");
    }
    void viewAllMembers(Library Library){
        for(int i=0; i< Library.getNumMembers();i++){
            System.out.println("----------------------");
            System.out.printf("MEMBER ID- %s\n", Library.members.get(i).MemberID);
            System.out.printf("Name: %s\n", Library.members.get(i).Name);
            System.out.printf("Fine Amount: %d\n",Library.members.get(i).fine);
        }
        System.out.println("----------------------");
    }
    void removeBook(int ID, Library Library){
//        List<Book> newList = new ArrayList<>();
        int original_number_books  = Library.getNumBooks();
        int i=0;
        while(i<original_number_books){

            if(Library.books.get(i).ID==ID){
                if(Library.books.get(i).borrowStatus ==true){
                    System.out.println("Book could not be removed as it is borrowed.");
                    break;
                }
                else{
                    Library.decreaseBooks();
                    Library.books.remove(i);
                    System.out.println("Book is removed. ");
                    break;
                }


            }
            i++;
        }
        System.out.println("----------------------");
    }
    void removeMember(int ID, Library Library){
//        List<Member> newList = new ArrayList<>();
        int original_number_members  = Library.getNumMembers();
        int i=0;
        while(i<original_number_members){

            if(Library.members.get(i).MemberID==ID ){
                System.out.printf("Member with Mem ID: %d deleted successfully!\n", Library.members.get(i).MemberID);
                Library.decreaseMembers();
                Library.members.remove(i);
                break;

            }
            i++;

        }

        System.out.println("----------------------");
    }

}

class Member{
    protected String Name;
    protected String phoneNumber;
    protected int age;
    protected int MemberID;
    Book borrowed[] = new Book[2];
    protected int fine=0;
    Member(String name, String phoneNumber, int age,Library Library){
        this.Name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.MemberID = Library.getNumMembersID();
        Library.members.add(this);
        Library.increaseMembers();
        Library.increaseMembersID();
        System.out.println("----------------------");
        System.out.printf("Member Successfully registered with Member ID: %d\n",this.MemberID);

    }
    void listAllAvailableBooks(Library Library){
        for(int i=0; i< Library.getNumBooks();i++){

            if(!Library.books.get(i).borrowStatus){
                System.out.println("----------------------");
                System.out.printf("BOOK ID- %s\n", Library.books.get(i).ID);
                System.out.printf("Name: %s\n", Library.books.get(i).Name);
                System.out.printf("Author: %s\n", Library.books.get(i).Author);
            }
        }
        System.out.println("----------------------");
    }
    void listBooks(){
        System.out.println("----------------------");
        if(this.borrowed[0]==null && this.borrowed[1]==null){
            System.out.println("No Books borrowed currently.");
        }else if(this.borrowed[0]!=null && this.borrowed[1]==null){
            System.out.printf("Book ID: %d\n",this.borrowed[0].ID);
            System.out.printf("Name: %s\n",this.borrowed[0].Name);
            System.out.printf("Author: %s\n",this.borrowed[0].Author);

        }else if(this.borrowed[0]==null && this.borrowed[1]!=null){
            System.out.printf("Book ID: %d\n",this.borrowed[1].ID);
            System.out.printf("Name: %s\n",this.borrowed[1].Name);
            System.out.printf("Author: %s\n",this.borrowed[1].Author);

        }else{
            System.out.printf("Book ID: %d\n",this.borrowed[0].ID);
            System.out.printf("Name: %s\n",this.borrowed[0].Name);
            System.out.printf("Author: %s\n",this.borrowed[0].Author);

            System.out.println("----------------------");

            System.out.printf("Book ID: %d\n",this.borrowed[1].ID);
            System.out.printf("Name: %s\n",this.borrowed[1].Name);
            System.out.printf("Author: %s\n",this.borrowed[1].Author);

        }

        System.out.println("----------------------");
    }
    void payFine(Library Library){
        System.out.println("------------------------");
        if(this.fine==0){
            System.out.println("You have no fine.");
        }
        else{
            System.out.printf("You had a total fine of Rs. %d. It has been paid successfully!\n",this.fine);
            this.fine = 0;

        }
        System.out.println("------------------------");
    }

    void Issue(int bookID, Library Library) {
        System.out.println("---------------------------------");
        if (this.borrowed[0] != null && this.borrowed[1] != null) {
            System.out.println("First return the borrowed books.");
        }else if(this.fine!=0){
            System.out.printf("Pay your fine of Rs. %d first.\n",this.fine);
        }
        else if (this.borrowed[0] == null && this.fine==0) {
            Book issue_book = Library.searchBook(bookID);
            if (issue_book!=null){
                if(issue_book.borrowStatus==false){
                    borrowed[0]= issue_book;
                    issue_book.borrowStatus=true;
                    issue_book.borrowTime = System.currentTimeMillis()/1000;
                    System.out.println("Borrow Successful!");

                }else{
                    System.out.println("That book is already borrowed. Check again later!!");
                }}

        } else if (this.borrowed[1] == null && this.fine==0) {
            Book issue_book = Library.searchBook(bookID);
            if(issue_book!=null){
                if(issue_book.borrowStatus==false){
                    borrowed[1]= issue_book;
                    issue_book.borrowStatus=true;
                    issue_book.borrowTime = System.currentTimeMillis()/1000;
                    System.out.println("Borrow Successful!");

                }else{
                    System.out.println("That book is already borrowed. Check again later!!");
                }
            }
        }
        System.out.println("---------------------------------");


    }
    void Return(  int ID,Library Library){
        System.out.println("---------------------------------");
        if(this.borrowed[0]==null && this.borrowed[1]==null){
            System.out.println("You have nothing to return");
        }
        else if(this.borrowed[0]!= null && this.borrowed[0].ID==ID){
            this.borrowed[0].borrowStatus = false;
            this.fine += Library.calculateFine(this.borrowed[0]);
            this.borrowed[0].borrowTime = null;
            this.borrowed[0]= null;
            System.out.printf("Fine you have to pay: %d\n",this.fine);
            System.out.println("Return Successful");
        }
        else if(this.borrowed[1]!= null && this.borrowed[1].ID==ID){
            this.borrowed[1].borrowStatus = false;
            this.fine += Library.calculateFine(this.borrowed[1]);
            this.borrowed[1].borrowTime = null;
            this.borrowed[1]= null;
            System.out.printf("Fine you have to pay: %d\n",this.fine);
            System.out.println("Return Successful");
        }else{
            System.out.println("You have different books");
            this.listBooks();
        }
        System.out.println("---------------------------------");
    }

}
class Book{
    public String Name;
    public String Author;
    protected int ID;
    protected boolean borrowStatus;

    protected Long borrowTime;

    Book(String name, String author, Library Library){
        this.Name = name;
        this.Author = author;
        this.borrowStatus = false;
        this.ID = Library.getBooksID();
        Library.books.add(this);
        Library.increaseBooks();
        Library.increaseBooksID();

    }

}
class Library{
    protected List<Book> books = new ArrayList<>();
    protected List<Member> members = new ArrayList<>();
    //    public Member[] getMember(){
//
//    }
    private int bookID=0;
    private int numBooks=0;

    protected void increaseBooks() {
        this.numBooks = this.numBooks+1;
    }
    protected void decreaseBooks() {
        this.numBooks = this.numBooks-1;
    }

    public int getNumBooks() {
        return (this.numBooks);
    }
    public void increaseBooksID() {
        this.bookID = this.bookID+1;
    }
    public int getBooksID() {
        return this.bookID;
    }
    private int numMembers=0;
    private int memID=0;

    public void increaseMembers() {
        this.numMembers = this.numMembers+1;
    }
    public void decreaseMembers() {
        this.numMembers = this.numMembers-1;
    }
    public int getNumMembers() {
        return this.numMembers;
    }
    public void increaseMembersID() {
        this.memID = this.memID+1;
    }
    public int getNumMembersID() {
        return this.memID;
    }
    //    public search_books(){}
    public Member searchMember(String name, String phoneNumber) {
        for (int i = 0; i < this.numMembers; i++) {
            if (this.members.get(i).Name.equals(name) && this.members.get(i).phoneNumber.equals(phoneNumber)) {
                return this.members.get(i);
            }
        }

        return null;
    }
    protected int calculateFine(Book book){
        Long time_now = System.currentTimeMillis()/1000;
        if(time_now-book.borrowTime<=10){
            return 0;
        }
        else{
            return (int)((time_now-(book.borrowTime))*3);
        }
    }

    protected Book searchBook(int ID){
        for(int i=0; i<this.getNumBooks();i++){
            if(this.books.get(i).ID==ID){
                return this.books.get(i);
            }
        };
        return null;
    }
    protected void setBooks(List<Book> booksList){
        for(int i=0;i<this.getNumBooks();i++){
            this.books.set(i,booksList.get(i));}
    }
    protected void setMembers(List<Member> memsList){
        for(int i=0;i<this.getNumMembers();i++){
            this.members.set(i,memsList.get(i));}
    }



}



public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("***************************");
        System.out.println("***************************");
        System.out.println("Library Portal Initialised....");
        Library IIITD_Library = new Library();
        Librarian deskSitting = new Librarian();

        //Menu Driven For Library Management System
        while(true){

            System.out.println("------------------------");
            System.out.println("1. Enter as a Librarian");
            System.out.println("2. Enter as a Member");
            System.out.println("3. Exit");

            int a = scanner.nextInt();
            scanner.nextLine();
            if (a==1){
                while(true){
                    System.out.println("------------------------");
                    System.out.println("1. Register a member");
                    System.out.println("2. Remove a member");
                    System.out.println("3. Add a book");
                    System.out.println("4. Remove a book") ;
                    System.out.println("5. View all members along with their books and fines to be paid");
                    System.out.println("6. View all books");
                    System.out.println("7. Back");
                    System.out.println("------------------------");

                    int b = scanner.nextInt();

                    scanner.nextLine();
                    System.out.println("------------------------");
                    if(b==1){
                        System.out.print("Name: ");
                        String memberName = scanner.nextLine();
                        System.out.print("Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Phone Number: ");
                        String phoneNumber = scanner.nextLine();
                        deskSitting.addMember(memberName,phoneNumber, age, IIITD_Library);

                    }
                    else if(b==2){
                        System.out.print("Member ID: ");
                        int Mem_ID= scanner.nextInt();
                        scanner.nextLine();
                        deskSitting.removeMember(Mem_ID,IIITD_Library);


                    }
                    else if(b==3){
                        System.out.print("Book Title: ");
                        String bookName = scanner.nextLine();
                        System.out.print("Author: ");
                        String authorName = scanner.nextLine();
//                        scanner.nextLine();
                        System.out.print("Copies: ");
                        int copies = scanner.nextInt();
                        scanner.nextLine();
                        for(int i=0; i<copies;i++){deskSitting.addBook(bookName, authorName, IIITD_Library);};
                        if(copies==1){
                            System.out.println("Book added successfully!");
                        }else if(copies>1){
                            System.out.println("Books added successfully!");
                        }



                    }
                    else if(b==4){
                        System.out.print("Book ID: ");
                        int id = scanner.nextInt();
                        deskSitting.removeBook(id,IIITD_Library);
                    }
                    else if (b==5){
                        deskSitting.viewAllMembers(IIITD_Library);
                    }
                    else if (b==6){
                        deskSitting.viewAllBooks(IIITD_Library);
                    }
                    else if (b==7){break; }
                    else{
                        System.out.println("Please Enter a Valid Input!!!");
                    }



                }
            }
            else if(a==2){
                System.out.println("------------------------");
                System.out.print("Name: ");
                String memberName = scanner.nextLine();
                System.out.print("Phone Number: ");
                String phoneNumber = scanner.nextLine();

                Member memberUsing = IIITD_Library.searchMember(memberName, phoneNumber);
                if(memberUsing!=null){
                    System.out.printf("Welcome %s. Member ID: %d\n",memberUsing.Name,memberUsing.MemberID);
                    System.out.println("------------------------");
                    while(true){

                        System.out.println("1. List Available Books");
                        System.out.println("2. List My Books");
                        System.out.println("3. Issue book");
                        System.out.println("4. Return book");
                        System.out.println("5. Pay Fine");
                        System.out.println("6. Back");
                        int b = scanner.nextInt();
                        if(b==1){
                            memberUsing.listAllAvailableBooks(IIITD_Library);
                        }
                        else if(b==2){
                            memberUsing.listBooks();
                        }
                        else if(b==3){
                            System.out.print("Book ID: ");
                            int bookID = scanner.nextInt();
                            scanner.nextLine();

                            memberUsing.Issue(bookID, IIITD_Library);

                        }
                        else if(b==4){
                            System.out.print("Book ID: ");
                            int bookID = scanner.nextInt();
                            scanner.nextLine();

                            memberUsing.Return(bookID, IIITD_Library);
                        }
                        else if (b==5){
                            memberUsing.payFine(IIITD_Library);
                        }
                        else if (b==6){ break;}
                        else{
                            System.out.println("Please Enter a Valid Input!!!");
                        }}


                }else{
                    System.out.printf("Member with Name: %s and Phone No: %s doesn't exist.\n",memberName, phoneNumber);
                }
            }
            else if (a==3){
                System.out.println("Thank You for Using our Library!!!");
                break;}
            else{
                System.out.println("Please Enter a Valid Value!!!!");
            }
        }
    }

}