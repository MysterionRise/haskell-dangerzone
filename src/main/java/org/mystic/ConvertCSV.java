package org.mystic;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class ConvertCSV {

    private static void transformXLS(String absolutePath) throws IOException, BiffException {
        //Excel document to be imported
//        String filename = "input.xls";
        WorkbookSettings ws = new WorkbookSettings();
        ws.setLocale(new Locale("en", "EN"));
        Workbook w = Workbook.getWorkbook(new File(absolutePath), ws);

        Sheet s = w.getSheet(0);

        //File to store data in form of CSV
        File f = new File(transformToLatin(absolutePath.trim()) + ".csv");

        OutputStream os = new FileOutputStream(f);
        String encoding = "UTF8";
        OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
        BufferedWriter bw = new BufferedWriter(osw);

        Cell[] column;
        // Gets the cells from sheet
        for (int i = 1; i < s.getColumns(); i++) {
            column = s.getColumn(i);
            // limit stupid headers and bottoms
            final String name = column[7].getContents();
            if (i >= 3) {
                //TODO look up for place by name + absolute path
                bw.write(name + " " + absolutePath.substring(absolutePath.lastIndexOf("\\") + 2, absolutePath.indexOf(".xls")));
            } else {
                if (name.isEmpty()) {
                    bw.write("-");
                }
                bw.write(name);
            }
            bw.write('$');
            for (int j = 8; j < 55; j++) {
                final String contents = column[j].getContents();
                if (contents.isEmpty()) {
                    bw.write("-");
                } else {
                    bw.write(contents);
                }
                bw.write('$');
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static final Map<Character, String> map = new HashMap<Character, String>() {{
        put('я', "ya");
        put('ч', "ya");
        put('с', "ya");
        put('м', "ya");
        put('и', "ya");
        put('т', "ya");
        put('ь', "ya");
        put('б', "ya");
        put('ю', "ya");
        
        put('ф', "ya");
        put('я', "ya");
        put('я', "ya");
        put('я', "ya");
        put('я', "ya");
        put('я', "ya");

    }};

    private static String transformToLatin(String a) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); ++i) {
            sb.append(map.get(a.charAt(i)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            final Path path = Paths.get(".");
            Files.walkFileTree(path, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().contains(".xls")) {
                        System.out.println(file.toAbsolutePath().toString());
                        try {
                            transformXLS(file.toAbsolutePath().toString());
                        } catch (BiffException e) {
                            System.err.println(e.toString());
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}