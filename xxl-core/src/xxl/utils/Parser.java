package xxl.utils;

import xxl.Spreadsheet;
import xxl.content.Content;
import xxl.content.literals.*;
import xxl.content.Reference;
import xxl.content.functions.binary.*;
import xxl.content.functions.interval.nospaces.*;
import xxl.content.functions.interval.spaces.*;
import xxl.exceptions.UnrecognizedEntryException;

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
        if(content.charAt(0) == '=') {
            return parseContentExpression(content);
        } else {
            return parseContentLiteral(content);
        }
    }

    public Content parseContentExpression(String content) throws UnrecognizedEntryException {
        if(content.contains("(")) {
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
        } catch(NumberFormatException e) {
            throw new NumberFormatException();
        }

    }

    public Content parseContentLiteral(String content) throws UnrecognizedEntryException {
        if(content.charAt(0) == '\'') {
            return new Str(content.substring(1));
        } else {
            try {
                return new Int(Integer.parseInt(content));
            } catch(NumberFormatException e) {
                throw new UnrecognizedEntryException(content);
            }
        }
    }

    /**
     * Parses the imported file
     * 
     * @param filename
     * @throws IOException
     * @throws UnrecognizedEntryException
     */
    public BufferedReader parseFile(String filename, Spreadsheet spreadsheet) throws IOException, UnrecognizedEntryException{

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            parseDimensions(reader, spreadsheet);
            return reader;
        } catch (IOException e) {
             throw new IOException();
        }
    }

    /**
     * Parses
     * 
     * @param reader
     * @throws IOException
     * @throws UnrecognizedEntryException
     */
    public void parseDimensions(BufferedReader reader, Spreadsheet spreadsheet) throws IOException, UnrecognizedEntryException{

        for (int i=0; i<2; i++) {
            String line = reader.readLine();
            String[] fields = line.split("=");
            int number = Integer.parseInt(fields[1]);

            if(number < 0) {
                throw new UnrecognizedEntryException("TODO"); // TODO
            }
            
            if(spreadsheet == null) {
                spreadsheet = new Spreadsheet();
            }

            switch(fields[0]) {
                case "linhas": 
                    spreadsheet.setRows(number);
                case "colunas":
                    spreadsheet.setColumns(number);
            }
        }
    }
}
