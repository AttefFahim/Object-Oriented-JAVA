package ControllerClasses;
import java.io.IOException;
import  java.util.Scanner;

import Entity.*;

public class InventoryHandler {
    
    public static void InsertIntoInventory()
    {
        Scanner scanner = new Scanner(System.in);
        short choice=0;
        boolean isRunning = true;
        while(isRunning)
        {
            System.out.println("-------------------------Product Insertion-------------------------");
            InventoryHandler.choiceFun();
            choice = scanner.nextShort();
    
            switch(choice)
            {
                case 1:
                {
                    Books.insertBook();
                    break;
                }
                case 2:
                {
                    Grocery.insertgrocery();
                    break;
                }
                case 3:
                {
                    Medicine.insertMedicine();
                    break;
                }
                case 4:
                {
                    isRunning=false;

                    try {
                        InventoryHandler.clearScreen();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }

                    break;

                    

                }
                default : System.out.println("Wrong Choice Try Again");
            }

        }

      
    }

    public static void ViewFromInventory()
    {
        Scanner scanner = new Scanner(System.in);
        short choice=0;
        boolean isRunning = true;
        while(isRunning)
        {
            System.out.println("-------------------------Inventory Viewing-------------------------");
            InventoryHandler.choiceFun();
            choice = scanner.nextShort();
    
            switch(choice)
            {
                case 1:
                {
                    Books.viewBookList();
                    int bookNumber;

                    System.out.print("Enter The Book Number You want to See Details of: ");
                    bookNumber = scanner.nextInt();

                    Books book = Books.getBook(bookNumber);

                    if(book==null)
                    {
                        System.out.println("\n!!Inserted Invalid Book Number!!\n");
                    }
                    else
                    {
                        book.getProductDetails();
                    }

                    

                    break;
                }
                case 2:
                {
                    Grocery.viewgroceryList();
                    int groceryNumber;

                    System.out.print("Enter The Grocery Number You want to See Details of: ");
                    groceryNumber = scanner.nextInt();

                    Grocery grocery = Grocery.getgrocery(groceryNumber);

                    if(grocery==null)
                    {
                        System.out.println("\n!!Inserted Invalid Grocery Number!!\n");
                    }
                    else
                    {
                        grocery.getProductDetails();
                    }


                    break;
                }
                case 3:
                {
                    Medicine.viewMedicineList();
                    int medicineNumber;

                    System.out.print("Enter The Medicine Number You want to See Details of: ");
                    medicineNumber = scanner.nextInt();

                    Medicine medicine = Medicine.getMedicine(medicineNumber);

                    if(medicine==null)
                    {
                        System.out.println("\n!!Inserted Invalid Medicine Number!!\n");
                    }
                    else
                    {
                        medicine.getProductDetails();
                    }
                    break;
                }
                case 4:
                {
                    isRunning=false;
                    
                    try {
                        InventoryHandler.clearScreen();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                default : System.out.println("Wrong Choice Try Again");
            }

        }

    }

    public static void UpdateCurrentInventory()
    {
        Scanner scanner = new Scanner(System.in);
        short choice=0;
        boolean isRunning = true;
        while(isRunning)
        {
            System.out.println("-------------------------Inventory Updating-------------------------");
            InventoryHandler.choiceFun();
            choice = scanner.nextShort();

            switch(choice)
            {
                case 1:
                {
                    Books.viewBookList();
                    int bookNumber;
                    System.out.print("Enter The Book Number You want to See Details of: ");
                    bookNumber = scanner.nextInt();
                    Books book = Books.getBook(bookNumber);
                    if(book!=null)
                    {
                        book.setProductDetails(bookNumber);

                    }
                    else
                    {
                        System.out.println("!!INVALID CHOICE TRY AGAIN");
                    }

                    break;
                }
                case 2:
                {
                    Grocery.viewgroceryList();
                    int groceryNumber;

                    System.out.print("Enter The Grocery Number You want to See Details of: ");
                    groceryNumber = scanner.nextInt();

                    Grocery grocery = Grocery.getgrocery(groceryNumber);

                    if(grocery==null)
                    {
                        System.out.println("\n!!Inserted Invalid Grocery Number!!\n");
                    }
                    else
                    {
                        grocery.setProductDetails(groceryNumber);
                    }


                    break;
                    
                }
                case 3:
                {
					Medicine.viewMedicineList();
                    int medicineNumber;

                    System.out.print("Enter The Medicine Number You want to See Details of: ");
                    medicineNumber = scanner.nextInt();

                    Medicine medicine = Medicine.getMedicine(medicineNumber);

                    if(medicine==null)
                    {
                        System.out.println("\n!!Inserted Invalid Medicine Number!!\n");
                    }
                    else
                    {
                        medicine.setProductDetails(medicineNumber);
                    }


                    break;
                }
                case 4:
                {
                    isRunning=false;

                    try {
                        InventoryHandler.clearScreen();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                default : System.out.println("Wrong Choice Try Again");
            
            }
        }

    }

    public static void DeleteFromInventory()
    {
        Scanner scanner = new Scanner(System.in);
        short choice=0;
        boolean isRunning = true;
        while(isRunning)
        {
            System.out.println("-------------------------Inventory Updating-------------------------");
            InventoryHandler.choiceFun();
            choice = scanner.nextShort();

            switch(choice)
            {
                case 1:
                {
                    Books.viewBookList();
                    int bookNumber;
                    System.out.print("Enter The Book Number You want to Delete: ");
                    bookNumber = scanner.nextInt();
                    Books book = Books.getBook(bookNumber);
                    if(book!=null)
                    {
                        book.delete(bookNumber);

                    }
                    else
                    {
                        System.out.println("!!INVALID CHOICE TRY AGAIN");
                    }

                    break;
                }
                case 2:
                {
                    Grocery.viewgroceryList();
                    int groceryNumber;
                    System.out.print("Enter The Book Number You want to Delete: ");
                    groceryNumber = scanner.nextInt();
                    Grocery grocery = Grocery.getgrocery(groceryNumber);
                    if(grocery!=null)
                    {
                        grocery.delete(groceryNumber);

                    }
                    else
                    {
                        System.out.println("!!INVALID CHOICE TRY AGAIN");
                    }

                    break;
                    
                }
                case 3:
                {
                    Medicine.viewMedicineList();
                    int medicineNumber;
                    System.out.print("Enter The Medicine Number You want to Delete: ");
                    medicineNumber = scanner.nextInt();
                    Medicine medicine = Medicine.getMedicine(medicineNumber);
                    if(medicine!=null)
                    {
                       medicine.delete(medicineNumber);

                    }
                    else
                    {
                        System.out.println("!!INVALID CHOICE TRY AGAIN");
                    }
					
					break;
                }
                case 4:
                {
                    isRunning=false;

                    try {
                        InventoryHandler.clearScreen();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                default : System.out.println("Wrong Choice Try Again");
            
            }
        }


    }

    private static void choiceFun()
    {
        System.out.println("Category:#1 Books ");
        System.out.println("Category:#2 Grocery ");
        System.out.println("Category:#3 Medicine ");
        System.out.println("To go Back Enter 4\n");
        System.out.print("Enter Category Number ");
    }
    
    public static void clearScreen() throws IOException, InterruptedException
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
    }
}
