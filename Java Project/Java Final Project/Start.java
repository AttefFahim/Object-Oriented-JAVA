import  java.io.*;
import  java.util.Scanner;

import ControllerClasses.*;


public class Start{

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);

        File folderName =  new File("Files/");
        File files[]= new File[3];

        files[0] = new File("Files/books.txt");
        files[1] = new File("Files/grocery.txt");
        files[2] = new File("Files/medecine.txt");
        //File Check If Not Exist Create Files and Folder
        if(!folderName.exists())
        {
            folderName.mkdir();
        }

        if(!files[0].exists())
        {
            files[0].createNewFile();
        }
        if(!files[1].exists())
        {
            files[1].createNewFile();
        }
        if(!files[2].exists())
        {
            files[2].createNewFile();
        }

        //-----------------------

        //Giving User Choice

        boolean isRunning = true;
        short choice;

        while(isRunning)
        {
            System.out.println("-------------------------Inventory Management System-------------------------");
            System.out.println("#1. Insert Into Inventory");
            System.out.println("#2. View Current Inventory & Product Details");
            System.out.println("#3. Update Current Inventory");
            System.out.println("#4. Delete From Inventory");
            System.out.println("#5. Exit Program\n");
            System.out.print("Enter Your Choice: ");

            choice = scanner.nextShort();
            switch(choice)
            {
                case 1 : 
                {
                    InventoryHandler.InsertIntoInventory();
                    break;
                }
                case 2 :
                {
                    InventoryHandler.ViewFromInventory();
                    break;
                } 
                case 3 :
                {
                    InventoryHandler.UpdateCurrentInventory();
                    break;
                } 
                case 4 :
                {
                    InventoryHandler.DeleteFromInventory();
                    break;
                } 
                case 5 : isRunning=false; break;

                default : System.out.println("Wrong Choice Please Select Again");

            }

        }


        //-----------------
        scanner.close();

    }

}