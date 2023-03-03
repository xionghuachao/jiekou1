package post;

import java.io.*;
import java.util.Scanner;

public class Test {
    //23
    public void test1(String num1,String num2){
        int number=Integer.parseInt(num1+num2);
        if(number>-321&&number<=321){
            System.out.println("符合的数字为"+Integer.parseInt(num2+num1));
        }else{
            System.out.println("0");
        }
    }
    public void test2(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入一个整数：");
        int num = scanner.nextInt();
        if(num>-321&&num<=321){
            String x= String.valueOf(num);
            StringBuffer stringBuffer = new StringBuffer(x);//构造一个StringBuffer对象。
            String result = stringBuffer.reverse().toString();
            System.out.println("反转后的数为："+result);



        }else{
            System.out.println("0");
        }

    }
    public void test3(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName)));
        String line = reader.readLine();
        int lineNumber=getTotalLines(fileName);
        if ( lineNumber< 0 || lineNumber > getTotalLines(fileName)) {
            System.out.println("不在文件的行数范围之内。");
        }
        int num = 0;
        while (line != null) {
            if (lineNumber == ++num) {
                System.out.println("line     " + lineNumber + ":     " + line);
            }
            line = reader.readLine();
        }
        reader.close();
    }

    // 文件内容的总行数。
    static int getTotalLines(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName)));
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        in.close();
        return lines;
    }


    public static void main(String[] args) {
        Test testC=new Test();
       testC.test2();
    }
}
