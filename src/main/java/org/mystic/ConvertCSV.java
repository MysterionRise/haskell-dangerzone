package org.mystic;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import java.io.*;
import java.util.*;

public class ConvertCSV {
    public static void main(String[] args) {
        try {
            //File to store data in form of CSV
            File f = new File("input.csv");

            OutputStream os = (OutputStream) new FileOutputStream(f);
            String encoding = "UTF8";
            OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
            BufferedWriter bw = new BufferedWriter(osw);

            //Excel document to be imported
            String filename = "input.xls";
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("en", "EN"));
            Workbook w = Workbook.getWorkbook(new File(filename), ws);

            // Gets the sheets from workbook
            for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++) {
                Sheet s = w.getSheet(sheet);

                bw.write(s.getName());
                bw.newLine();

                Cell[] row = null;

                // Gets the cells from sheet
                for (int i = 0; i < s.getRows(); i++) {
                    row = s.getRow(i);

                    if (row.length > 0) {
                        final String contents1 = row[0].getContents();
                        if (contents1.isEmpty()) {
                            bw.write("#");
                        } else {
                            bw.write(contents1);
                        }
                        for (int j = 1; j < row.length; j++) {
                            bw.write('$');
                            final String contents = row[j].getContents();
                            if (contents.isEmpty()) {
                                bw.write("#");
                            } else {
                                bw.write(contents);
                            }
                        }
                    }
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}