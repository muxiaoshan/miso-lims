package uk.ac.bbsrc.tgac.miso.core.data.spreadsheet;

import java.io.InputStream;
import java.util.stream.Stream;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;

public class OpenDocumentDataReader extends SpreadSheetReader<OdfTable, OdfTableRow> {

  /**
   * Open a file containing an ODT file previously generated by MISO (1-row header) and emit a stream of rows as strings.
   */
  public static Stream<String[]> open(InputStream stream) throws Exception {
    OdfSpreadsheetDocument workbook = OdfSpreadsheetDocument.loadDocument(stream);
    return SpreadSheetReader.createStream(workbook.getTableList().get(0), new OpenDocumentDataReader());
  }

  @Override
  protected String getColumn(OdfTable sheet, OdfTableRow row, int i) {
    return row.getCellByIndex(i).getStringValue();
  }

  @Override
  protected int getColumns(OdfTable sheet, OdfTableRow row) {
    return sheet.getColumnCount();
  }

  @Override
  protected OdfTableRow getRow(OdfTable sheet, int index) {
    return sheet.getRowByIndex(index);
  }

  @Override
  protected int getRows(OdfTable sheet) {
    return sheet.getRowCount();
  }

}
