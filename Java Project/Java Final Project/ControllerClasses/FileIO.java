package ControllerClasses;
import  java.io.*;
import  java.util.Scanner;

public  class FileIO {
    private static Scanner sc = new Scanner(System.in);
    private static InputStreamReader isr = new InputStreamReader(System.in);
    private static BufferedReader bfr = new BufferedReader(isr);
    
    public static void insertProduct(String productDetails,String fileName) throws IOException
    {
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file,true);
        writer.write(productDetails);
        writer.flush();
        writer.close();

    }

    public static void viewProductList(String fileName) throws IOException
    {
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bfl = new BufferedReader(reader);

        String temp;
        int id=1;
        while((temp=bfl.readLine())!=null)
        {
            System.out.print("Number:#"+id+" ");
            for(int i=0;temp.charAt(i)!=' ';i++)
            {
                System.out.print(temp.charAt(i));
            }
            id++;
            System.out.println();
        }

        bfl.close();
    }

    public static String[] getProductDetails(int fileNumber,String fileName) throws IOException
    {
        String[] productDetails = new String[10];

        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bfl = new BufferedReader(reader);

        String temp;
        int id=1;
        while((temp=bfl.readLine())!=null)
        {
           if(id==fileNumber)
           {
               int index=0;
               for(int i=0;i<temp.length();i++)
               {
                   if(temp.charAt(i)==' ')
                   {
                       index++;
                   }
                   else
                   {
                       if(productDetails[index]==null)
                       {

                           productDetails[index] = String.valueOf(temp.charAt(i));
                       }
                       else
                       {
                            productDetails[index] += temp.charAt(i);
                       }
                   }
               }

               break;
           }
           else
           {
               id++;
           }
        }

        bfl.close();

        return productDetails;
    }

    public static String[] getProductArray(String fileName) throws IOException
    {
        String[] wholeFile = new String[100];

        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bfl = new BufferedReader(reader);

        String temp;
        int index =0;
        while((temp=bfl.readLine())!=null)
        {
            wholeFile[index] = temp+"\n";
            index++;
        }
        bfl.close();

        if(wholeFile[0]!=null)
        {
            return wholeFile;
        }
        else
        {
            return null;

        }
      
    }

    public static int countTotalProducts(String fileName) throws IOException
    {
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bfl = new BufferedReader(reader);

        String temp;
        int count =0;
        while((temp=bfl.readLine())!=null)
        {
            count++;
        }
        bfl.close();

        return count;
    }

    public static void updateProduct(int fileNumber,String fileName,String updateProduct,int totalProducts,String[] wholeArray) throws IOException
    {
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);

        String fileText ="";

        for(int i=0;i<totalProducts;i++)
        {
            if(i==(fileNumber-1))
            {
                fileText += updateProduct;
            }
            else
            {
                fileText += wholeArray[i];
            }
        }
        
        writer.write(fileText);
        writer.flush();
        writer.close();
    }

    public static void deleteProduct(int fileNumber,String fileName, int totalProducts,String[] wholeArray) throws IOException
    {
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);

        String fileText ="";

        for(int i=0;i<totalProducts;i++)
        {
            if(i==(fileNumber-1))
            {
               
            }
            else
            {
                fileText += wholeArray[i];
            }
        }
        
        writer.write(fileText);
        writer.flush();
        writer.close();
    }
}
