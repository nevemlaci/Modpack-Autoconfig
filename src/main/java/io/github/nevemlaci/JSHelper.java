package io.github.nevemlaci;

import java.util.List;

public class JSHelper {
    public static String JSString(String string){
        return "'" + string + "'";
    }

    public static String JSFunction(String functionName, List<String> args) {
        if(args.size() == 1){
            return JSFunction(functionName, args.get(0));
        }
        String output = new String();
        output += functionName + "([";
        for (String arg : args) {
            output += arg + ", ";
        }
        output = output.substring(0, output.length() - 2);
        output += "])";
        return output;
    }

    public static String JSFunction(String functionName, String arg){
        String output = new String();
        output += functionName + "(" + arg + ")";
        return output;
    }

    public static String NullJSFunction(String functionName, List<String> args){
        if(args.isEmpty()){
            return "";
        }
        return JSFunction(functionName, args);
    }

    public static String JSMember(String functionName, List<String> args){
        String memberCallForm = ".";
        memberCallForm += functionName;

        return JSFunction(memberCallForm, args);
    }

    public static String JSMember(String functionName, String arg){
        String memberCallForm = ".";
        memberCallForm += functionName;

        return JSFunction(memberCallForm, arg);
    }

    public static String NullJSMember(String functionName, List<String> args){
        if(args.isEmpty()){
            return "";
        }
        return JSMember(functionName, args);
    }

    public static String JSLambda(List<String> args, String body){
        String output = new String();
        output += "(";
        for (String arg : args) {
            output += arg + ", ";
        }
        output = output.substring(0, output.length() - 2);
        output += ") => {\n\t\t";
        output += body + "\n\t\t";
        output += "}";
        return output;
    }
}
