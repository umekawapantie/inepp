package com.inepp.service.admin.util;

import com.inepp.domain.entity.GrantPrivs;
import com.inepp.domain.entity.Privs;
import com.inepp.domain.entity.Role;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper<T> {

    private XSSFWorkbook excel;

    public ExcelHelper(InputStream inputStream) {
        try {
            this.excel = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public List<T> parse(String sheetName,Class<?> clazz) {

        List<T> list = new ArrayList<>();
        XSSFSheet sheet = excel.getSheet(sheetName);

        List<String> names =new ArrayList<>(); //存储标题

        XSSFRow firstRow = sheet.getRow(0);
        short lastCellNum = firstRow.getLastCellNum();

        for (int i = 0; i < lastCellNum; i++) {
            XSSFCell cell = firstRow.getCell(i);
            names.add(cell.getStringCellValue());
        }


        for(int tr =1;tr<=sheet.getLastRowNum();tr++){

            XSSFRow row = sheet.getRow(tr);
            T obj = (T) clazz.newInstance();

            for(int td = 0; td < lastCellNum; td++){

                String fieldName = names.get(td);
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                XSSFCell cell = row.getCell(td);

                switch (cell.getCellType()){
                    case STRING:  //字符串
                        String str = cell.getStringCellValue();
                        field.set(obj,str);
                        break;
                    case NUMERIC:
                        if(DateUtil.isCellDateFormatted(cell)){ //日期
                            field.set(obj,cell.getDateCellValue());
                        }else{//数字
                                field.set(obj,(int)cell.getNumericCellValue());
                        }
                        break;
                }


                list.add(obj);
            }


        }





        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        final InputStream in = new FileInputStream("D:\\admin.xlsx");


      //  List<Role> list = new ExcelHelper<Role>(in).parse("role",Role.class);
        List<Privs> lists= new ExcelHelper<Privs>(in).parse("privs", Privs.class);


        List<GrantPrivs> gps= new ExcelHelper<GrantPrivs>(in).parse("gp", GrantPrivs.class);
        System.out.println(lists);
        System.out.println(gps);
    }

}
