package Entity;

import java.io.IOException;
import  java.util.Scanner;

import ControllerClasses.FileIO;
import ControllerClasses.InventoryHandler;


public class Grocery extends Product {
    
    private String companyName;
    private String addingDate;
    private static final String groceryFileName = "Files/Grocery.txt";

    public Grocery(String productName,short quantity,int productPrice,String companyName,String addingDate)
    {
        this.productName = productName;
        this.quantity=quantity;
        this.productPrice = productPrice;
        this.companyName = companyName;
        this.addingDate = addingDate;
    }

    public void getProductDetails()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------grocery Details Of: "+this.productName+" ------------------------");
        System.out.println("Grocery Name: "+this.productName);
        System.out.println("Company Name: "+this.companyName);
        System.out.println("Grocery Price: "+this.productPrice);
        System.out.println("Quantity Available: "+this.quantity);
        System.out.println("Adding Date: "+this.addingDate);
        System.out.println("-------------------------End Of grocery Details------------------------");
        System.out.print("Enter Any Key To Continue: ");
        String choice = scanner.next();
    }

    public void setProductDetails(int groceryNumber)
    {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        short choice = 0;
        while(isRunning)
        {
            System.out.println("-------------------------Grocery Updating|Editing-------------------------");
            System.out.println("#1: Grocery Name");
            System.out.println("#2: Company Name");
            System.out.println("#3: Price");
            System.out.println("#4: Quantity");
            System.out.println("#5: Adding Date");
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
                    System.out.println("Grocery Name Updated to: "+this.productName+"---> "+newName);
                    this.productName = newName;
                    break;
                }
                case 2:
                {
                    String newName ="";
                    System.out.print("Enter New Company Name[NO SPACES ALLOWED USE UNDERSCORE INSTEAD]: ");
                    newName= scanner.next();
                    System.out.println("Company Name Updated to: "+this.companyName+"---> "+newName);
                    this.companyName = newName;
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
                    System.out.print("Enter Adding Date(DD/MM/YYYY): ");
                    newDate= scanner.next();
                    System.out.println("Date Updated to: "+this.addingDate+"---> "+newDate);
                    this.addingDate = newDate;
                    break;
                }
                case 6:
                {
                    this.updateInventory(groceryNumber);
                    isRunning = false;
					
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
        
        String groceryDetails = this.productName+" "+this.quantity+" "+this.productPrice+" "+this.companyName+" "+this.addingDate+"\n";

        try {
            FileIO.insertProduct(groceryDetails,Grocery.groceryFileName);
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("Grocery Added To List Successfully\n\n");
    }

    public static void viewgroceryList()
    {
        System.out.println("-------------------------Grocery List-------------------------");
        try {
            FileIO.viewProductList(Grocery.groceryFileName);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public static void insertgrocery()
    {
        Scanner scanner = new Scanner(System.in);
        String productName;
        short quantity;
        int productPrice;
        String companyName;
        short day=0;
        short month=0;
        int year=0;

        boolean isDay=true,isMonth=true,isYear=true;


        System.out.println("-------------------------Grocery Insertion-------------------------");
        System.out.print("Enter Grocery Name[No Spaces Allowed Use underscore '_' in place of Spaces]: ");
        productName = scanner.next();
        System.out.print("Enter Quantity: ");
        quantity = scanner.nextShort();
        System.out.print("Enter Price: ");
        productPrice = scanner.nextInt();
        System.out.print("Enter Company Name[No Spaces Allowed Use underscore '_' in place of Spaces]: ");
        companyName = scanner.next();
        
        while(isDay)
        {
            System.out.print("Enter Adding Day(RANGE-->[1-31]): ");
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
            System.out.print("Enter Adding Month(RANGE-->[1-12]): ");
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
            System.out.print("Enter Adding Year(RANGE-->[2020-2021]): ");
            year = scanner.nextShort();
            if(year<2020 || year>2021 )
            {
                System.out.println("Invalid Date Please Enter a Valid Year");
            }
            else
            {
                isYear = false;
            }
        }

        String addingDate = day+"/"+month+"/"+year;

        Grocery newgrocery = new Grocery(productName,quantity,productPrice,companyName,addingDate);

        newgrocery.insert();




    }

    public static Grocery getgrocery(int groceryNumber)
    {
        String[] groceryDetails = new String[10];
        try {
            groceryDetails = FileIO.getProductDetails(groceryNumber, Grocery.groceryFileName);
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        if(groceryDetails[0]!=null)
        {
            Grocery grocery = new Grocery(groceryDetails[0],Short.parseShort(groceryDetails[1]),Integer.parseInt(groceryDetails[2]),groceryDetails[3],groceryDetails[4]);
            return grocery;
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
            totalProducts = FileIO.countTotalProducts(Grocery.groceryFileName);
        } catch (IOException e) {
           
            e.printStackTrace();
        }
        return totalProducts;
    }

    public void updateInventory(int groceryNumber)
    {
        
        String wholeFile[] = new String[100];
        try {
            wholeFile = FileIO.getProductArray(Grocery.groceryFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String groceryDetails = this.productName+" "+this.quantity+" "+this.productPrice+" "+this.companyName+" "+this.addingDate+"\n"; 
        try {
            FileIO.updateProduct(groceryNumber, Grocery.groceryFileName, groceryDetails, this.numberOfProducts(), wholeFile);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public void delete(int groceryNumber)
    {
        String wholeFile[] = new String[100];
        try {
            wholeFile = FileIO.getProductArray(Grocery.groceryFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileIO.deleteProduct(groceryNumber, Grocery.groceryFileName, this.numberOfProducts(), wholeFile);
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        System.out.println("Grocery with Name: "+this.productName+" Deleted Successfully");
    }
}
