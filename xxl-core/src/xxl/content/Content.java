package xxl.content;

import java.io.Serializable;

import xxl.content.functions.binary.Add;
import xxl.content.functions.binary.Div;
import xxl.content.functions.binary.Mul;
import xxl.content.functions.binary.Sub;
import xxl.content.functions.interval.nospaces.Avg;
import xxl.content.functions.interval.nospaces.Prod;
import xxl.content.functions.interval.spaces.Coal;
import xxl.content.functions.interval.spaces.Conc;
import xxl.content.literals.Literal;
import xxl.content.literals.Str;
import xxl.exceptions.UnrecognizedEntryException;

public abstract class Content implements Serializable {

    /**
     * 
     * @return the value of the content
     */
    public abstract Literal value();

    /**
     * 
     * @return the string content as a Content object
     */
    public Content parseContent(String content) {
        if (content.length() == 0) {
            return null;
        } else if (content.startsWith("=")) {
            return parseContentExpression(content);
        } else {
            return parseContentLiteral(content);
        }
    }

    /**
     * 
     * @return the string content as a Function or Reference object
     */
    public Content parseContentExpression(String content) {
        if (content.contains("(")) {
            return parseContentFunction(content);
        } else {
            return parseContentReference(content);
        }
    }

    /**
     * 
     * @return the string content as a Function object
     */
    public Content parseContentFunction(String content) throws UnrecognizedEntryException {
        // TODO
        String functionName = content.substring(1, content.indexOf("("));
        String[] args = content.substring(content.indexOf("(")+1, content.length()-1).split(",");
        //for (String s:args)
            //System.out.println(s);
        // TODO falta tratar os argumentos
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
}