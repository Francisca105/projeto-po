package xxl.utils;

import xxl.Spreadsheet;
import xxl.content.Content;
import xxl.content.literals.*;
import xxl.content.Reference;
import xxl.content.functions.binary.*;
import xxl.content.functions.interval.nospaces.*;
import xxl.content.functions.interval.spaces.*;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.utils.Position;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;
import java.nio.Buffer;
import java.util.Collection;
import java.util.ArrayList;

import xxl.exceptions.UnrecognizedEntryException;

public class Parser {

    public Content parseContent(String content) throws UnrecognizedEntryException {
        if (content.length() == 0) {
            return null;
        } else if (content.charAt(0) == '=') {
            return parseContentExpression(content);
        } else {
            return parseContentLiteral(content);
        }
    }

    public Content parseContentExpression(String content) throws UnrecognizedEntryException {
        if (content.contains("(")) {
            return parseContentFunction(content);
        } else {
            return parseContentReference(content);
        }
    }

    public Content parseContentFunction(String content) throws UnrecognizedEntryException {
        // TODO
        String functionName = content.substring(1, content.indexOf("("));
        String[] args = content.split(",");
        Content arg1 = parseContent(args[0]);
        Content arg2 = parseContent(args[1]);

        switch (functionName) {
            case "ADD":
                return new Add(arg1, arg2);
            case "DIV":
                return new Div(arg1, arg2);
            case "MUL":
                return new Mul(arg1, arg2);
            case "SUB":
                return new Sub(arg1, arg2);
            case "AVERAGE ":
                return new Avg(arg1, arg2);
            case "PRODUCT":
                return new Prod(arg1, arg2);
            case "CONCAT":
                return new Conc(arg1, arg2);
            case "COALESCE":
                return new Coal(arg1, arg2);
        }
        return new Str(content);
    }

    public Content parseContentReference(String content) throws NumberFormatException {
        String position = content.substring(1);
        String[] split = position.split(";");
        try {
            int row = Integer.parseInt(split[0]);
            int col = Integer.parseInt(split[1]);

            return new Reference(row, col);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

    }

    public Content parseContentLiteral(String content) throws UnrecognizedEntryException {
        if (content.charAt(0) == '\'') {
            return new Str(content.substring(1));
        } else {
            try {
                return new Int(Integer.parseInt(content));
            } catch (NumberFormatException e) {
                throw new UnrecognizedEntryException(content);
            }
        }
    }
    // bora mudar temporariaremente só para receber uma celula em vez de uma gama
    public Position parseRange(String rangeSpecification) throws UnrecognizedEntryException {
        Position pos = new Position(rangeSpecification);
        return pos; //?_?
        
        
        /*String[] coords = rangeSpecification.split(":");
        Position[] range;

        if (coords.length == 1) {
            range = new Position[1];
            range[0] = new Position(coords[0]);
            return range;
        }

        String[] coord_1 = coords[0].split(";");
        String[] coord_2 = coords[1].split(";");

        if (coord_1[0].equals(coord_2[0])) {
            int nColumns = Integer.parseInt(coord_2[1]) - Integer.parseInt(coord_1[1]);
            range = new Position[nColumns];

            for (int i = 0; i < nColumns; i++)
                range[i] = new Position(Integer.parseInt(coord_1[0]), Integer.parseInt(coord_1[0]) + i);

            return range;

        } else if (coord_1[1].equals(coord_2[1])) {
            int nRows = Integer.parseInt(coord_2[0]) - Integer.parseInt(coord_1[0]);
            range = new Position[nRows];

            for (int i = 0; i < nRows; i++)
                range[i] = new Position(Integer.parseInt(coord_1[1]) + i, Integer.parseInt(coord_1[1]));

            return range;
        } else {
            throw new UnrecognizedEntryException(rangeSpecification);
        }*/
    }

    /**
     * Starts to parse a import file.
     * 
     * @param filename
     * @throws IOException
     * @throws UnrecognizedEntryException
     * @return a buffered reader
     */
    public BufferedReader parseFile(String filename, Spreadsheet spreadsheet)
            throws IOException, UnrecognizedEntryException {

        /*try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            parseDimensions(reader, spreadsheet);
            return reader;
        } catch (IOException e) {
            throw new IOException();
        }*/
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            parseDimensions(reader, spreadsheet);
            return reader;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Parses the dimensions of a spreadsheet from a import file.
     * 
     * @param reader
     * @throws IOException
     * @throws UnrecognizedEntryException
     */
    public void parseDimensions(BufferedReader reader, Spreadsheet spreadsheet) throws IOException, UnrecognizedEntryException {

        for (int i = 0; i < 2; i++) {
            String line = reader.readLine();
            String[] fields = line.split("=");
            int number = Integer.parseInt(fields[1]);

            if (number <= 0) {
                throw new UnrecognizedEntryException("Rows or Columns non positive numbers."); // TODO: não sei se é para fazer isto
            }

            /*if (spreadsheet == null) {
                spreadsheet = new Spreadsheet();
            }*/

            switch (fields[0]) {
                case "linhas":
                    spreadsheet.setRows(number);
                    break;
                case "colunas":
                    spreadsheet.setColumns(number);
                    break;
                default:
                    throw new UnrecognizedEntryException("Invalid format for dimensions entries."); // TODO: não sei se é para fazer isto
            }
        }
    }
}