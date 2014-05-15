package httpTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelGen {
	
	public static void main(String[] args) {
		List<String[]> list=new ArrayList<String[]>();
		String[] aa={"A","B","C"};
		list.add(aa);
		String[] bb={"A","B","C","B","C","B","C","B","C"};
		list.add(bb);
		export(list);
	}
	
	
	public static void export(List<String[]> list) {
	        HSSFWorkbook wb = new HSSFWorkbook();
	        HSSFSheet sheet = wb.createSheet();
	        initSheet(sheet);//初始化sheet，设置列数和每列宽度
	        
	        HSSFCellStyle centerStyle = wb.createCellStyle();//设置为水平居中
	        centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        centerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFCellStyle rightStyle = wb.createCellStyle();//水平靠右
	        rightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        rightStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        initHeader(sheet, centerStyle);//初始化头部为水平居中

	        int rowNum=1;
	        if(list==null){
	        	outPutExcel(wb);
	        	return;
	        }
	        for(String[] arr:list){
	        	 HSSFRow row = sheet.createRow(rowNum++);
	        	 int colNum=0;
	        	 if(arr==null){
	        		 continue;
	        	 }
	        	 for(String str:arr){
	        		 createCell(row, colNum++, str, centerStyle);
	        	 }
	        }

	        outPutExcel(wb);
	        
	    }
	
	
	
	private static void outPutExcel(HSSFWorkbook bookWorkbook){
		try {
			   FileOutputStream outputStream;
			   
			   try {
			    outputStream = new FileOutputStream("D://order.xls");
			    bookWorkbook.write(outputStream);
			    outputStream.flush();
			    outputStream.close();
			   } catch (FileNotFoundException e) {
			    System.err.println("获取不到位置");
			    e.printStackTrace();
			   } catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			   }
			  } finally {
				  System.out.println("Gen Excel successfuly!");
			  }
			 }


	// 初始化sheet，设置列数和每列宽度
	private static void initSheet(HSSFSheet sheet) {
		sheet.setColumnWidth((short) 0, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 1, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 2, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 3, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 4, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 5, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 6, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 7, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 8, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 9, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 10, (short) (35.7 * 150));
		sheet.setColumnWidth((short) 11, (short) (35.7 * 150));
	}

	/**
	 * 初始化sheet样式
	 * 
	 * @param sheet
	 * @param style
	 */
	private static void initHeader(HSSFSheet sheet, HSSFCellStyle style) {
		HSSFRow row1 = sheet.createRow((short) 0);
		createCell(row1, 0, "订单号", style);
		createCell(row1, 1, "收件人", style);
		createCell(row1, 2, "Step 1 Date", style);
		createCell(row1, 3, "Step 1 Content", style);
		createCell(row1, 4, "Step 2 Date", style);
		createCell(row1, 5, "Step 2 Content", style);
		createCell(row1, 6, "Step 3 Date", style);
		createCell(row1, 7, "Step 3 Content", style);
		createCell(row1, 8, "Step 4 Date", style);
		createCell(row1, 9, "Step 4 Content", style);
		createCell(row1, 10, "Step 5 Date", style);
		createCell(row1, 11, "Step 5 Content", style);
	}

	/**
	 * 创建单元格
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列位置
	 * @param value
	 *            值
	 * @param style
	 *            样式
	 */
	private static void createCell(HSSFRow row, int column, Object value,
			HSSFCellStyle style) {
		HSSFCell cell = row.createCell((short) column);
		// cell.setEncoding((short) 1);
		cell.setCellValue(String.valueOf(value));
		cell.setCellStyle(style);
	}
}
