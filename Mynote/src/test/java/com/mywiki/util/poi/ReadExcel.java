package com.mywiki.util.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class ReadExcel {
	
	/**
	 * 可以做成通用读取的形式
	 * @throws IOException
	 */
	@Test
	public void test01() throws IOException{
		File f=new File("gy.xls");
		String Updatesql;
    	int k;
    	StringBuffer sb;
		FileInputStream fis=new FileInputStream(f);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet=workbook.getSheetAt(0);
        for (int j = 1; j <=100; j++) {
        	sb= new StringBuffer();
            HSSFRow row=sheet.getRow(j);
            //开始的列(实际上是开始的单元格)
			int startCellNum = row.getFirstCellNum();
			//结束的列(实际上是结束的单元格)
			int endCellNum = row.getLastCellNum();
			int m=endCellNum-startCellNum;
			
			for( k=0;k<m;k++){
				if(k==m-1){
					if(row.getCell(k).getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
						sb.append(row.getCell(k).getNumericCellValue()+"'");
					else
						sb.append(row.getCell(k).getStringCellValue()+"'");
				}else if(k==0){
					if(row.getCell(k).getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
						sb.append("'"+row.getCell(k).getNumericCellValue()+"','");
					else
						sb.append("'"+row.getCell(k).getStringCellValue()+"','");
				}else{
					if(row.getCell(k).getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
						sb.append(row.getCell(k).getNumericCellValue()+"','");
					else
						sb.append(row.getCell(k).getStringCellValue()+"','");
				}
			}
			Updatesql = "insert into product values (" + sb+ ")" ;
            System.out.println(Updatesql);
        }
	}
	
	@Test
	public void test02() throws IOException{
		File f=new File("gy.xls");
		String Updatesql;
    	int k;
    	StringBuffer sb;
		FileInputStream fis=new FileInputStream(f);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet=workbook.getSheetAt(0);
        for (int j = 1; j <=100; j++) {
        	sb= new StringBuffer();
            HSSFRow row=sheet.getRow(j);
            //开始的列(实际上是开始的单元格)
			int startCellNum = row.getFirstCellNum();
			//结束的列(实际上是结束的单元格)
			int endCellNum = row.getLastCellNum();
			int m=endCellNum-startCellNum;
			
			for( k=0;k<m;k++){
				if(k==m-1){
					if(row.getCell(k).getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
						sb.append(row.getCell(k).getNumericCellValue()+"'");
					else
						sb.append(row.getCell(k).getStringCellValue()+"'");
				}else if(k==0){
					if(row.getCell(k).getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
						sb.append("'"+row.getCell(k).getNumericCellValue()+"','");
					else
						sb.append("'"+row.getCell(k).getStringCellValue()+"','");
				}else{
					if(row.getCell(k).getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
						sb.append(row.getCell(k).getNumericCellValue()+"','");
					else
						sb.append(row.getCell(k).getStringCellValue()+"','");
				}
			}
			Updatesql = "insert into product values (" + sb+ ")" ;
            System.out.println(Updatesql);
        }
	}
	
	@Test
	public void test03() throws IOException{
		Map<String,String> map1 = new HashMap<>();
		Map<String,String> map2 = new HashMap<>();
		File file = new File(
				"/EPAN/STS/git/Mynote/src/test/java/com/mywiki/util/poi/name.xml");
		try{ 
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder(); 
			Document doc = builder.parse(file); 
			NodeList nl = doc.getElementsByTagName("name"); 
			for (int i=0;i<nl.getLength();i++){
				NamedNodeMap  nnm = doc.getElementsByTagName("name").item(i).getAttributes();
				String nameType = nnm.getNamedItem("nameType").getNodeValue();
				String content = nnm.getNamedItem("content").getNodeValue();
				if(nameType.equals("1")){
					map1.put(content, "aa");
				}
				if(nameType.equals("2")){
					map2.put(content, "aa");
				}
			} 
		}catch(Exception e){ 
			e.printStackTrace(); 
		}
		
		
		File f=new File("/EPAN/STS/git/Mynote/src/test/java/com/mywiki/util/poi/name.xls");
		FileInputStream fis=new FileInputStream(f);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet=workbook.getSheetAt(0);
        String sb1 = "";
        String sb2 = "";
        for (int j = 0; j <=500; j++) {
            HSSFRow row=sheet.getRow(j);
            if(row==null)
            	break;
            Object val = row.getCell(0);
            Object val1 = row.getCell(1);
            if(val1 == null)
            	break;
            
            if(val!=null){
            	if(map1.get(val.toString())==null){
            		sb1=sb1+"\n<name nameType=\"1\" content=\""+val.toString()+"\"/>";
                }
            }
            if(val1!=null){
            	if(map2.get(val1.toString())==null){
            		sb2=sb2+"\n<name nameType=\"2\" content=\""+val1.toString()+"\"/>";
                }
            }
            
        }
        System.out.println(sb1);
        System.out.println();
        System.out.println(sb2);
	}
}
