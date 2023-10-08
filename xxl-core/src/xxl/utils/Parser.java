package xxl.utils;

import xxl.Spreadsheet;

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


}
