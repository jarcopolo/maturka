package maturka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Zadanie58 {

    static List<Integer> time1,temp1,time2,temp2,time3,temp3;
    static int LIST_SIZE;
    static StringBuilder results = new StringBuilder("");

    public static void main(String[] args) throws IOException {

        readFromfiles();
        zad1();
        zad2();
        zad3();
        zad4();

        writeResults();
    }

    static void readFromfiles() throws FileNotFoundException {
        File f1 = new File(Zadanie58.class.getResource("dane/dane_systemy1.txt").getFile());
        File f2 = new File(Zadanie58.class.getResource("dane/dane_systemy2.txt").getFile());
        File f3 = new File(Zadanie58.class.getResource("dane/dane_systemy3.txt").getFile());

        System.out.println(f1.getAbsolutePath());
        time1 = new ArrayList<Integer>();
        temp1 = new ArrayList<Integer>();
        time2 = new ArrayList<Integer>();
        temp2 = new ArrayList<Integer>();
        time3 = new ArrayList<Integer>();
        temp3 = new ArrayList<Integer>();

        Scanner sc = new Scanner(f1);
        while (sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] parts = line.split(" ",2);
            time1.add(Integer.parseInt(parts[0],2));
            temp1.add(Integer.parseInt(parts[1],2));
        }

        sc = new Scanner(f2);
        while (sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] parts = line.split(" ",2);
            time2.add(Integer.parseInt(parts[0],4));
            temp2.add(Integer.parseInt(parts[1],4));
        }

        sc = new Scanner(f3);
        while (sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] parts = line.split(" ",2);
            time3.add(Integer.parseInt(parts[0],8));
            temp3.add(Integer.parseInt(parts[1],8));
        }

            LIST_SIZE = time1.size();
    }

    static void zad1()
    {
        String s1min = Integer.toString(Collections.min(temp1),2);
        String s2min = Integer.toString(Collections.min(temp2),2);
        String s3min = Integer.toString(Collections.min(temp3),2);
        results.append("58.1\nStacja 1: ").append(s1min).append(" Stacja 2: ").append(s2min).append(" Stacja 3: ").append(s3min).append("\n\n");
    }

    static void zad2()
    {
        int count=0;
        for(int i=0;i<LIST_SIZE;i++)
        {
            if(timeIsInvalid(time1.get(i)))
            {
                if(timeIsInvalid(time2.get(i)) && timeIsInvalid(time3.get(i)))
                {
                    count++;
                }
            }
        }
        results.append("58.2\nDni z niepoprawnym stanem zegara: ").append(count).append("\n\n");
    }

    static boolean timeIsInvalid(int time)
    {
        if((time-12)%24==0)
        {
            return false;
        }
        else return true;
    }

    static void zad3()
    {
        int count=1,max1=temp1.get(0),max2=temp2.get(0),max3=temp3.get(0);
        for(int i=1;i<LIST_SIZE;i++)
        {
            boolean hasRecord=false;
            if(max1<temp1.get(i))
            {
                hasRecord=true; max1=temp1.get(i);
            }
            if(max2<temp2.get(i))
            {
                hasRecord=true; max2=temp2.get(i);
            }
            if(max3<temp3.get(i))
            {
                hasRecord=true; max3=temp3.get(i);
            }
            if (hasRecord) count++;
        }
        results.append("58.3\nDni z rekordem: ").append(count).append("\n\n");
    }

    static void zad4()
    {
        int maxZ=0;
        for(int i=0;i<LIST_SIZE-1;i++)
        {
            for(int j=i+1;j<LIST_SIZE;j++)
            {
                int t_i=temp1.get(i);
                int t_j=temp1.get(j);
                int r_ij= (t_i-t_j)*(t_i-t_j);
                int z= (r_ij - 1) / Math.abs(i-j) + 1;
                if (maxZ<z) maxZ=z;
            }

        }
        results.append("58.4\nNajwiekszy skok temperatury: ").append(maxZ);
    }



    static void writeResults() throws IOException {
        File f = new File("wyniki_systemy.txt");
        f.createNewFile();
        PrintWriter pw = new PrintWriter(f);
        pw.write(results.toString());
        pw.close();

    }


}
