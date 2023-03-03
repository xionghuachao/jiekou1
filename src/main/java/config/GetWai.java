package config;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.*;

public class GetWai {

public  Sheet getc(int num,String FilePath) {
    Sheet sheet=null;
    try {
    File file = new File(FilePath);
    if (!file.exists()) {

        throw new RuntimeException("文件不存在");
    }
    InputStream inputStream = null;

        inputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(inputStream);
       sheet=workbook.getSheetAt(num);

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

   return sheet;
}
        //获取第一工作簿
//        Sheet sheet = workbook.getSheetAt(0);
//        //获取第一行
//        Row row1 = sheet.getRow(0);
//        //第一行第一列
//        Cell cell0 = row1.getCell(0);
//        //第一行第二列
//        Cell cell1 = row1.getCell(1);
//        System.out.println(cell1.toString());
//      //获取第二行
//        Row row2= sheet.getRow(1);
//        //第二行第1列
//        Cell  cell2_1=row2.getCell(0);
//        System.out.println(cell2_1.toString());



}
