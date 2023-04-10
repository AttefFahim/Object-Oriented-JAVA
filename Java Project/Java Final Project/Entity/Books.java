package Entity;

import java.io.IOException;
import  java.util.Scanner;

import ControllerClasses.FileIO;
import ControllerClasses.InventoryHandler;


public class Books extends Product {
    
    private String authorName;
    private String publishingDate;
    private static final String bookFileName = "Files/books.txt";

    public Books(String productName,short quantity,int productPrice,String authorName,String publishingDate)
    {
        this.productName = productName;
        this.quantity=quantity;
        this.productPrice = productPrice;
        this.authorName = authorName;
        this.publishingDate = publishingDate;
    }

    public void getProductDetails()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------Book Details Of: "+this.productName+" ------------------------");
        System.out.println("Book Name: "+this.productName);
        System.out.println("Author Name: "+this.authorName);
        System.out.println("Book Price: "+this.productPrice);
        System.out.println("Quantity Available: "+this.quantity);
        System.out.println("Publishing Date: "+this.publishingDate);
        System.out.println("-------------------------End Of Book Details------------------------");
        System.out.print("Enter Any Key To Continue: ");
        String choice = scanner.next();
    }

    public void setProductDetails(int bookNumber)
    {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        short choice = 0;
        while(isRunning)
        {
            System.out.println("-------------------------Book Updating|Editing-------------------------");
            System.out.println("#1: Book Name");
            System.out.println("#2: Author Name");
            System.out.println("#3: Price");
            System.out.println("#4: Quantity");
            System.out.println("#5: Publishing Date");
            System.out.println("#6: Confirm and Go Back");
            System.out.print("Enter Your Choice: ");
            choice = scanner.nextShort();

            switch(choice)
            {
                case 1:
                {
                    String newName ="";
                    System.out.print("Enter New Name[NO SPACES ALLOWED USE UNDERSCORE INSTEAD]: ");
                    newName= scanner.next();
                    System.out.println("Book Name Updated to: "+this.productName+"---> "+newName);
                    this.productName = newName;
                    break;
                }
                case 2:
                {
                    String newName ="";
                    System.out.print("Enter New Author Name[NO SPACES ALLOWED USE UNDERSCORE INSTEAD]: ");
                    newName= scanner.next();
                    System.out.println("Author Name Updated to: "+this.authorName+"---> "+newName);
                    this.authorName = newName;
                    break;
                 
                }
                case 3:
                {
                    int newPrice =0;
                    System.out.print("Enter new Price: ");
                    newPrice= scanner.nextInt();
                    System.out.println("Price Updated to: "+this.productPrice+"---> "+newPrice);
                    this.productPrice = newPrice;
                    break;
                   
                }
                case 4:
                {
                    short newQuantity =0;
                    System.out.print("Enter new Quantity: ");
                    newQuantity= scanner.nextShort();
                    System.out.println("Quantity Updated to: "+this.quantity+"---> "+newQuantity);
                    this.quantity = newQuantity;
                    
                    break;
                }
                case 5:
                {
                    String newDate ="";
                    System.out.print("Enter Publishing Date(DD/MM/YYYY): ");
                    newDate= scanner.next();
                    System.out.println("Date Updated to: "+this.publishingDate+"---> "+newDate);
                    this.publishingDate = newDate;
                    break;
                }
                case 6:
                {
                    this.updateInventory(bookNumber);
                    isRunning = false;
					try {
                        InventoryHandler.clearScreen();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default:
                {
                    System.out.println("Invalid Choice Try Again");
                }
            }

        }
        
    }

    public void insert() 
    {
        
        String bookDetails = this.productName+" "+this.quantity+" "+this.productPrice+" "+this.authorName+" "+this.publishingDate+"\n";

        try {
            FileIO.insertProduct(bookDetails,Books.bookFileName);
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("Book Added To List Successfully\n\n");
    }

    public static void viewBookList()
    {
        System.out.println("-------------------------Book List-------------------------");
        try {
            FileIO.viewProductList(Books.bookFileName);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public static void insertBook()
    {
        Scanner scanner = new Scanner(System.in);
        String productName;
        short quantity;
        int productPrice;
        String authorName;
        short day=0;
        short month=0;
        int year=0;

        boolean isDay=true,isMonth=true,isYear=true;


        System.out.println("-------------------------Books Insertion-------------------------");
        System.out.print("Enter Book Name[No Spaces Allowed Use underscore '_' in place of Spaces]: ");
        productName = scanner.next();
        System.out.print("Enter Quantity: ");
        quantity = scanner.nextShort();
        System.out.print("Enter Price: ");
        productPrice = scanner.nextInt();
        System.out.print("Enter Author Name[No Spaces Allowed Use underscore '_' in place of Spaces]: ");
        authorName = scanner.next();
        
        while(isDay)
        {
            System.out.print("Enter Publishing Day(RANGE-->[1-31]): ");
            day = scanner.nextShort();
            if(day<1 || day>31 )
            {
                System.out.println("Invalid Date Please Enter a Valid Date");
            }
            else
            {
                isDay = false;
            }
        }
        while(isMonth)
        {
            System.out.print("Enter Publishing Month(RANGE-->[1-12]): ");
            month = scanner.nextShort();
            if(month<1 || month>12 )
            {
                System.out.println("Invalid Month Please Enter a Valid Month");
            }
            else
            {
                isMonth = false;
            }
        }
        while(isYear)
        {
            System.out.print("Enter Publishing Year(RANGE-->[0-2021]): ");
            year = scanner.nextShort();
            if(year<0 || year>2021 )
            {
                System.out.println("Invalid Date Please Enter a Valid Year");
            }
            else
            {
                isYear = false;
            }
        }

        String publishingDate = day+"/"+month+"/"+year;

        Books newBook = new Books(productName,quantity,productPrice,authorName,publishingDate);

        newBook.insert();




    }

    public static Books getBook(int bookNumber)
    {
        String[] bookDetails = new String[10];
        try {
            bookDetails = FileIO.getProductDetails(bookNumber, Books.bookFileName);
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        if(bookDetails[0]!=null)
        {
            Books book = new Books(bookDetails[0],Short.parseShort(bookDetails[1]),Integer.parseInt(bookDetails[2]),bookDetails[3],bookDetails[4]);
            return book;
        }
        else
        {
            return null;
        }
        
    }

    private int numberOfProducts()
    {
        int totalProducts=0;
        try {
            totalProducts = FileIO.countTotalProducts(Books.bookFileName);
        } catch (IOException e) {
           
            e.printStackTrace();
        }
        return totalProducts;
    }

    public void updateInventory(int bookNumber)
    {
        
        String wholeFile[] = new String[100];
        try {
            wholeFile = FileIO.getProductArray(Books.bookFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String bookDetails = this.productName+" "+this.quantity+" "+this.productPrice+" "+this.authorName+" "+this.publishingDate+"\n"; 
        try {
            FileIO.updateProduct(bookNumber, Books.bookFileName, bookDetails, this.numberOfProducts(), wholeFile);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public void delete(int bookNumber)
    {
        String wholeFile[] = new String[100];
        try {
            wholeFile = FileIO.getProductArray(Books.bookFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileIO.deleteProduct(bookNumber, Books.bookFileName, this.numberOfProducts(), wholeFile);
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        System.out.println("Book with Name: "+this.productName+" Deleted Successfully");
    }
}
