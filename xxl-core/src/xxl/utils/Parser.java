package xxl.utils;

import xxl.Spreadsheet;
import xxl.content.Content;
import xxl.content.literals.*;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;

import java.util.Collection;
import java.util.ArrayList;

import xxl.exceptions.UnrecognizedEntryException;

public class Parser {
    private Spreadsheet _spreadsheet;

    Parser(Spreadsheet spreadsheet) {
        _spreadsheet = spreadsheet;
    }

    Parser() {
    }

    public Content<?> parseContent(String content) {
        if(content.charAt(0) == '=') {
            return parseContentExpression(content);
        } else {
            return parseContentLiteral(content);
        }
    }

    public Content<?> parseContentExpression(String content) {
        // TODO
        return new Str(content);
    }

    public Content<?> parseContentLiteral(String content) throws UnrecognizedEntryException {
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

}
