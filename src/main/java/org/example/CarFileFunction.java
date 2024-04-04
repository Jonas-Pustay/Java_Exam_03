package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CarFileFunction
{
    private static PrintWriter writer;

    static
    {
        try
        {
            writer = new PrintWriter("C:\\Users\\jonas\\IdeaProjects\\Exam_TEST_BBD_Client\\src\\main\\java\\org\\example\\log.txt");
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static List<Car> getFileCars()
    {
        List<Car> Cars = new ArrayList<Car>();
        
        try
        {
            Scanner scanner = new Scanner(new File("C:\\Users\\jonas\\IdeaProjects\\Exam_TEST_BBD_Client\\src\\main\\java\\org\\example\\data.txt"));
  
            while (scanner.hasNextLine())
            {
                List<String> infos = Arrays.asList(scanner.nextLine().split(",", -1));
                
                List<String> infosDate =  Arrays.asList(infos.get(3).split("/", -1));
                
                String FormattedDate = infosDate.get(2) + "-" + infosDate.get(1) + "-" + infosDate.get(0);

                Cars.add(new Car(infos.get(0), infos.get(1), infos.get(2), Date.valueOf(FormattedDate), Integer.valueOf(infos.get(4))));
            }

            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
        return Cars;
    }
    
    public static void printLog(String content, String status)
    {
        writer.println(LocalDate.now() + " - [" + status + "] : " + content);
        System.out.println(LocalDate.now() + " - [" + status + "] : " + content);
    }
}