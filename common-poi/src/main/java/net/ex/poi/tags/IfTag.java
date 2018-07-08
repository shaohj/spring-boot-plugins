//package net.ex.poi.tags;
//
//import net.ex.poi.poiutils.WorkbookUtils;
//import net.ex.poi.ExcelParser;
//
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//
////import org.apache.poi.hssf.util.Region;
//import org.apache.poi.ss.util.CellRangeAddress;
//
//import bsh.Interpreter;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//
///**
// * poi shiftRows方法移动时
// *  合并单元格格式丢失,添加处理.注意:if标签不要添加为合并单元格,以免标签删除后合并单元格对数据产生影响
// * 编  号：
// * 名  称：IfTag
// * 描  述：
// * 完成日期：2018/7/7 17:45
// * 编码作者：SHJ
// */
//public class IfTag implements ITag {
//
//	public static final String KEY_IF = "#if";
//
//	public static final String KEY_END = "#end";
//
//	public int[] parseTag(Object context, SXSSFWorkbook swb, Sheet sheet, Sheet newSheet, Row curRow, Cell curCell) {
//		int ifstart = curRow.getRowNum();
//		int ifend = -1;
//		int ifCount = 0;
//		String ifstr = "";
//		boolean bFind = false;
//		for (int rownum = ifstart; rownum <= sheet.getLastRowNum(); rownum++) {
//			Row row = sheet.getRow(rownum);
//			if (null == row)
//				continue;
//			for (short colnum = row.getFirstCellNum(); colnum <= row.getLastCellNum(); colnum++) {
//				Cell cell = row.getCell(colnum);
//				if (null == cell)
//					continue;
//				if (cell.getCellTypeEnum() == CellType.STRING) {
//					String cellstr = cell.getStringCellValue();
//
//					// get the tag instance for the cellstr
//					ITag tag = ExcelParser.getTagClass(cellstr);
//
//					if (null != tag) {
//						if (tag.hasEndTag()) {
//							if (0 == ifCount) {
//								ifstart = rownum;
//								ifstr = cellstr;
//							}
//							ifCount++;
//							break;
//						}
//					}
//					if (cellstr.startsWith(KEY_END)) {
//						ifend = rownum;
//						ifCount--;
//						if (ifstart >= 0 && ifend >= 0 && ifend > ifstart && ifCount == 0) {
//							bFind = true;
//						}
//						break;
//					}
//				}
//			}
//			if (bFind)
//				break;
//		}
//
//		if (!bFind)
//			return new int[] { 0, 0, 1 };
//
//		// test if condition
//		boolean bResult = false;
//		// remove #if tag and get condition expression
//		String expr = ifstr.trim().substring(KEY_IF.length()).trim();
//
//		// parse the condition expression
//		expr = (String) ExcelParser.parseStr(context, expr, true);
//
//		// use beanshell to eval expression value
//		Interpreter in = new Interpreter();
//		try {
//			if (expr.indexOf("?notEmpty") > 2) { // ""?notEmpty
//				bResult = true;
//			} else {
//				Object v = in.eval(expr);
//				bResult = ((Boolean) v).booleanValue();
//			}
//		} catch (Exception e) {
//			bResult = false;
//		}
//
//		/**
//		 * 当含有多个tag时,如多个#foreach标签,shift移动的标签会丢失,特殊进行处理 ,2个步骤.步骤1
//		 */
//		List<CellRangeAddress> originMerged = sheet.getMergedRegions();
//
//		if (bResult) { // if condition is true
//			// remove #if tag and #end tag only
//			sheet.removeRow(WorkbookUtils.getRow(ifstart, sheet));
//			sheet.removeRow(WorkbookUtils.getRow(ifend, sheet));
//			// remove merged region in ifstart & ifend
//			for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
//				// Region r = sheet.getMergedRegionAt(i);
//				// if (r.getRowFrom()==ifstart && r.getRowTo()==ifstart ||
//				// r.getRowFrom()==ifend && r.getRowTo()==ifend) {
//				CellRangeAddress r = sheet.getMergedRegion(i);
//				if (r.getFirstRow() == ifstart && r.getLastRow() == ifstart
//						|| r.getFirstRow() == ifend && r.getLastRow() == ifend) {
//					sheet.removeMergedRegion(i);
//					// we have to back up now since we removed one
//					i = i - 1;
//				}
//			}
//			sheet.shiftRows(ifend + 1, sheet.getLastRowNum(), -1, true, true);
//			sheet.shiftRows(ifstart + 1, sheet.getLastRowNum(), -1, true, true);
//
//			/**
//			 * shift移动的标签会丢失,特殊进行处理.2个步骤.步骤2
//			 */
//
//			for (CellRangeAddress cellRangeAddress : originMerged) {
//				int allShiftNum = (cellRangeAddress.getFirstRow() <= ifend) ? -1 : -2;
//
//				// 这里的8是插入行的index，表示这行之后才重新合并
//				if (cellRangeAddress.getFirstRow() >= ifstart - 1) {
//					// 你插入了几行就加几，我这里插入了一行,加1
//					int firstRow = cellRangeAddress.getFirstRow() + allShiftNum;
//					CellRangeAddress newCellRangeAddress = new CellRangeAddress(firstRow,
//							(firstRow + (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())),
//							cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
//					try {
//						sheet.addMergedRegion(newCellRangeAddress);
//					} catch (Exception e) {
//
//					}
//				}
//			}
//			return new int[] { 1, -2, 1 };
//		} else { // if condition is false
//			// remove #if #end block
//			for (int rownum = ifstart; rownum <= ifend; rownum++) {
//				sheet.removeRow(WorkbookUtils.getRow(rownum, sheet));
//			}
//			// remove merged region in ifstart & ifend
//			for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
//				// Region r = sheet.getMergedRegionAt(i);
//				// if (r.getRowFrom()>=ifstart && r.getRowTo()<=ifend) {
//				CellRangeAddress r = sheet.getMergedRegion(i);
//				if (r.getFirstRow() >= ifstart && r.getLastRow() <= ifend) {
//					sheet.removeMergedRegion(i);
//					// we have to back up now since we removed one
//					i = i - 1;
//				}
//			}
//			sheet.shiftRows(ifend + 1, sheet.getLastRowNum(), -(ifend - ifstart + 1), true, true);
//
//			/**
//			 * shift移动的标签会丢失,特殊进行处理.2个步骤.步骤2
//			 */
//			for (CellRangeAddress cellRangeAddress : originMerged) {
//				int allShiftNum = -(ifend - ifstart + 1);
//
//				// 这里的8是插入行的index，表示这行之后才重新合并
//				if (cellRangeAddress.getFirstRow() >= ifend) {
//					// 你插入了几行就加几，我这里插入了一行,加1
//					int firstRow = cellRangeAddress.getFirstRow() + allShiftNum;
//					CellRangeAddress newCellRangeAddress = new CellRangeAddress(firstRow,
//							(firstRow + (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())),
//							cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
//					try {
//						sheet.addMergedRegion(newCellRangeAddress);
//					} catch (Exception e) {
//
//					}
//				}
//			}
//
//			return new int[] { ExcelParser.getSkipNum(ifstart, ifend), ExcelParser.getShiftNum(ifend, ifstart), 1 };
//		}
//	}
//
//	public String getTagName() {
//		return KEY_IF;
//	}
//
//	public boolean hasEndTag() {
//		return true;
//	}
//
//}
