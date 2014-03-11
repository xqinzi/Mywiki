package com.mywiki.util.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;

public class ExcelCreate {

	public static void main(String[] args) {
		Workbook wb = createWorkbook();
		CellStyle style = wb.createCellStyle();
		List<String> sheetNames = new ArrayList<String>();
		sheetNames.add("mleo");
		sheetNames.add("chochy");
		List<Sheet> sheets = createSheet(wb, sheetNames);
		Row row = createRow(sheets.get(0), 6);
		Cell cell = createCell(row, 1);
		Cell cell1 = createCell(row, 2);
		cell = addValue(cell, "mleo");
		cell1 = addValue(cell1, "回忆中的明天");
	
		style = styleFont(wb, style, Font.BOLDWEIGHT_BOLD, IndexedColors.BLUE.getIndex(), (short)20, "微软雅黑");
		
		style = styleForegroundColor(style, IndexedColors.AQUA.getIndex());
		
		style = styleAlignment(style, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
		style = styleFrame(style);
		
		addStyle(cell, style);
		addStyle(cell1, style);
		
		mergeCells(sheets.get(0), 6, 6, 1, 6);
		
		columnWidth(sheets.get(0), 2, 50);
		
		writeWorkbook(wb, "workbook.xlsx");

		System.out.println("&&&&&&%%%&&&&&");
	}

	/**
	 * 写入工作簿
	 * 
	 * @param wb
	 * @param path
	 */
	public static void writeWorkbook(Workbook wb, String pathBookName) {
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(pathBookName);
			wb.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 创建工作簿
	 * 
	 * @return Workbook
	 */
	public static Workbook createWorkbook() {
		HSSFWorkbook wb = new HSSFWorkbook();
		return wb;
	}

	/**
	 * 创建工作表
	 * 
	 * @param sheetNames
	 * @param wb
	 */
	public static List<Sheet> createSheet(Workbook wb, List<String> sheetNames) {
		List<Sheet> sheets = new ArrayList<Sheet>();
		for (String sheetName : sheetNames) {
			// 一个安全的方式来创建有效的名称，这个工具替换无效字符用空格（''）
			String safeName = WorkbookUtil.createSafeSheetName(sheetName);
			Sheet sheet = wb.createSheet(safeName);
			sheets.add(sheet);
		}
		return sheets;
	}

	/**
	 * 创建行
	 * 
	 * @param sheet
	 * @param rowIndex
	 * @return
	 */
	public static Row createRow(Sheet sheet, int rowIndex) {
		Row row = sheet.createRow(rowIndex);
		return row;
	}

	/**
	 * 创建单元格
	 * 
	 * @param row
	 * @param cellIndex
	 * @return Cell
	 */
	public static Cell createCell(Row row, int cellIndex) {
		Cell cell = row.createCell(cellIndex);
		return cell;
	}

	/**
	 * 单元格赋值
	 * 
	 * @param cell
	 * @param cellValue
	 * @return Cell
	 */
	public static Cell addValue(Cell cell, String cellValue) {
		cell.setCellValue(cellValue);
		return cell;
	}

	/**
	 * 添加样试
	 * 
	 * @param cell
	 * @param style
	 * @return
	 */
	public static Cell addStyle(Cell cell, CellStyle style) {
		cell.setCellStyle(style);
		return cell;
	}

	/**
	 * 设置字体颜色
	 * 
	 * @param wb
	 * @param style
	 * @param fontBlod
	 *            字体粗细
	 * @param fontColor
	 *            字体颜色
	 * @param fontHeight
	 *            字体大小
	 * @param fontName
	 *            字体名字
	 * @return
	 */
	public static CellStyle styleFont(Workbook wb, CellStyle style,
			short fontBlod, short fontColor, short fontHeight, String fontName) {
		Font font = wb.createFont();
		font.setBoldweight(fontBlod);// 粗体
		font.setColor(fontColor);// 字体颜色
		font.setFontHeightInPoints(fontHeight);// 字体大小
		font.setFontName(fontName);// 字体名字
		style.setFont(font);
		return style;
	}

	/**
	 * 设置背景色
	 * 
	 * @param style
	 * @param foregroundCorlor
	 * @return CellStyle
	 */
	public static CellStyle styleForegroundColor(CellStyle style,
			short foregroundCorlor) {
		style.setFillForegroundColor(foregroundCorlor);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}

	/**
	 * 对齐方式
	 * 
	 * @param style
	 * @param halign
	 * @param valign
	 * @return CellStyle
	 */
	public static CellStyle styleAlignment(CellStyle style, short halign,
			short valign) {
		style.setAlignment(halign);
		style.setVerticalAlignment(valign);
		return style;
	}
	
	/**
	 * 单元格边框
	 * @param style
	 * @return CellStyle
	 */
	public static CellStyle styleFrame(CellStyle style) {
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}
	
	
	/**
	 * 合并单元格
	 * @param sheet
	 * @param firstRow
	 * @param lastRow
	 * @param firstColumn
	 * @param lastColumn
	 */
	public static void mergeCells(Sheet sheet,int firstRow,int lastRow,int firstColumn,int lastColumn) {
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstColumn, lastColumn));
	}

	/**
	 * 设置列宽度
	 * @param sheet
	 * @param columnIndex
	 * @param columnWidth
	 */
	public static void columnWidth(Sheet sheet,int columnIndex,int columnWidth) {
		sheet.setColumnWidth(columnIndex, 500*columnWidth);
	}
}