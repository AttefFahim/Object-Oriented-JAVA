package Entity;

import java.io.IOException;
import  java.util.Scanner;

import ControllerClasses.FileIO;
import ControllerClasses.InventoryHandler;

public class Medicine extends Product {
    
    private String companyName;
    private String expirationDate;
    private static final String medicineFileName = "Files/medecine.txt";

    public Medicine(String productName,short quantity,int productPrice,String companyName,String expirationDate)
    {
        this.productName = productName;
        this.quantity=quantity;
        this.productPrice = productPrice;
        this.companyName = companyName;
        this.expirationDate = expirationDate;
    }

    public void getProductDetails()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------Medicine Details Of: "+this.productName+" ------------------------");
        System.out.println("Medicine Name: "+this.productName);
        System.out.println("Company Name: "+this.companyName);
        System.out.println("Medicine Price: "+this.productPrice);
        System.out.println("Quantity Available: "+this.quantity);
        System.out.println("Expiration Date: "+this.expirationDate);
        System.out.println("-------------------------End Of Medicine Details------------------------");
        System.out.print("Enter Any Key To Continue: ");
        String choice = scanner.next();
    }

    public void setProductDetails(int medicineNumber)
    {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        short choice = 0;
        while(isRunning)
        {
            System.out.println("-------------------------Medicine Updating|Editing-------------------------");
            System.out.println("#1: Medicine Name");
            System.out.println("#2: Company Name");
            System.out.println("#3: Price");
            System.out.println("#4: Quantity");
            System.out.println("#5: Expiration Date");
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
                    System.out.println("Medicine Name Updated to: "+this.productName+"---> "+newName);
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
                    System.out.print("Enter Expiration Date(DD/MM/YYYY): ");
                    newDate= scanner.next();
                    System.out.println("Date Updated to: "+this.expirationDate+"---> "+newDate);
                    this.expirationDate = newDate;
                    break;
                }
                case 6:
                {
                    this.updateInventory(medicineNumber);
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
        
        String medicineDetails = this.productName+" "+this.quantity+" "+this.productPrice+" "+this.companyName+" "+this.expirationDate+"\n";

        try {
            FileIO.insertProduct(medicineDetails,Medicine.medicineFileName);
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("Medicine Added To List Successfully\n\n");
    }

    public static void viewMedicineList()
    {
        System.out.println("-------------------------Medicine List-------------------------");
        try {
            FileIO.viewProductList(Medicine.medicineFileName);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public static void insertMedicine()
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


        System.out.println("-------------------------Medicine Insertion-------------------------");
        System.out.print("Enter Medicine Name[No Spaces Allowed Use underscore '_' in place of Spaces]: ");
        productName = scanner.next();
        System.out.print("Enter Quantity: ");
        quantity = scanner.nextShort();
        System.out.print("Enter Price: ");
        productPrice = scanner.nextInt();
        System.out.print("Enter Company Name[No Spaces Allowed Use underscore '_' in place of Spaces]: ");
        companyName = scanner.next();
        
        while(isDay)
        {
            System.out.print("Enter Expiration Day(RANGE-->[1-31]): ");
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
            System.out.print("Enter Expiration Month(RANGE-->[1-12]): ");
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
            System.out.print("Enter Expiration Year(RANGE-->[2020-2030]): ");
            year = scanner.nextShort();
            if(year<2020 || year>2030 )
            {
                System.out.println("Invalid Date Please Enter a Valid Year");
            }
            else
            {
                isYear = false;
            }
        }

        String expirationDate = day+"/"+month+"/"+year;

        Medicine newMedicine = new Medicine(productName,quantity,productPrice,companyName,expirationDate);

        newMedicine.insert();




    }

    public static Medicine getMedicine(int medicineNumber)
    {
        String[] medicineDetails = new String[10];
        try {
            medicineDetails = FileIO.getProductDetails(medicineNumber, Medicine.medicineFileName);
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        if(medicineDetails[0]!=null)
        {
            Medicine medicine = new Medicine(medicineDetails[0],Short.parseShort(medicineDetails[1]),Integer.parseInt(medicineDetails[2]),medicineDetails[3],medicineDetails[4]);
            return medicine;
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
            totalProducts = FileIO.countTotalProducts(Medicine.medicineFileName);
        } catch (IOException e) {
           
            e.printStackTrace();
        }
        return totalProducts;
    }

    public void updateInventory(int medicineNumber)
    {
        
        String wholeFile[] = new String[100];
        try {
            wholeFile = FileIO.getProductArray(Medicine.medicineFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String medicineDetails = this.productName+" "+this.quantity+" "+this.productPrice+" "+this.companyName+" "+this.expirationDate+"\n"; 
        try {
            FileIO.updateProduct(medicineNumber, Medicine.medicineFileName, medicineDetails, this.numberOfProducts(), wholeFile);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public void delete(int medicineNumber)
    {
        String wholeFile[] = new String[100];
        try {
            wholeFile = FileIO.getProductArray(Medicine.medicineFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileIO.deleteProduct(medicineNumber, Medicine.medicineFileName, this.numberOfProducts(), wholeFile);
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        System.out.println("Medicine with Name: "+this.productName+" Deleted Successfully");
    }
}
