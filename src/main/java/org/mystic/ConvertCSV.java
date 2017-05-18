package org.mystic;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class ConvertCSV {

    private static File f = new File("all uiks.csv");
    private static OutputStream os;
    private static String encoding = "UTF8";
    private static OutputStreamWriter osw;
    private static BufferedWriter bw;

    public ConvertCSV() throws FileNotFoundException, UnsupportedEncodingException {
        f = new File("all uiks.csv");
        os = new FileOutputStream(f, true);
        encoding = "UTF8";
        osw = new OutputStreamWriter(os, encoding);
        bw = new BufferedWriter(osw);
    }

    private static void transformXLS(Path absolutePath) throws IOException, BiffException {
        WorkbookSettings ws = new WorkbookSettings();
        ws.setLocale(new Locale("en", "EN"));
        Workbook w = Workbook.getWorkbook(absolutePath.toFile(), ws);

        Sheet s = w.getSheet(0);

//        File f = new File(transformToLatin(absolutePath.trim()) + ".csv");

        Cell[] column;
        boolean stopProcessing = false;
        for (int i = 3; i < s.getColumns(); i++) {
            column = s.getColumn(i);
            // limit stupid headers and bottoms
            final String name = column[7].getContents();
            if (column.length >= 55) {
                if (i >= 3 && name.length() > 1) {
                    //TODO look up for place by name + absolute path
                    bw.write(name + " " + absolutePath.toString().substring(absolutePath.toString().lastIndexOf("/") + 1, absolutePath.toString().indexOf(".xls")));
                } else {
                    if (name.isEmpty()) {
                        if (i > 3) {
                            stopProcessing = true;
                        } else {
                            bw.write("-");
                        }
                    } else {
                        bw.write(name);
                    }
                }
                if (!stopProcessing) {
                    bw.write('$');
                    for (int j = 8; j < 55; j++) {
                        final String contents = column[j].getContents().replace("%", "");
                        if (contents.isEmpty()) {
                            bw.write("-");
                        } else {
                            bw.write(contents);
                        }
                        bw.write('$');
                    }
                    bw.newLine();
                }
            }
        }
        bw.flush();
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
        put('ч', "ch");
        put('с', "s");
        put('м', "m");
        put('и', "i");
        put('т', "t");
        put('ь', "");
        put('б', "b");
        put('ю', "yu");

        put('ф', "f");
        put('ы', "y");
        put('в', "v");
        put('а', "a");
        put('п', "p");
        put('р', "r");
        put('о', "o");
        put('л', "l");
        put('д', "d");
        put('ж', "zh");
        put('э', "e");

        put('й', "i");
        put('ц', "c");
        put('у', "u");
        put('к', "k");
        put('е', "e");
        put('н', "n");
        put('г', "g");
        put('ш', "sh");
        put('щ', "sh");
        put('з', "z");
        put('х', "x");
        put('ъ', "");
        put('ё', "yo");
    }};

    public static String transformToLatin(String a) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); ++i) {
            if (map.containsKey(Character.toLowerCase(a.charAt(i)))) {
                sb.append(map.get(Character.toLowerCase(a.charAt(i))).toUpperCase());
            } else {
                sb.append(a.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            new ConvertCSV();
            final Path path = Paths.get(".");
            Files.walkFileTree(path, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().contains(".xls")) {
                        try {
                            transformXLS(file.toAbsolutePath());
                            System.out.println(file.toAbsolutePath().toString());
                        } catch (BiffException e) {
                            e.printStackTrace(System.err);
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
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(System.err);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}