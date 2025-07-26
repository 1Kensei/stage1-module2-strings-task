package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        String pre = signatureString.substring(0, signatureString.indexOf('('));
        StringTokenizer preT = new StringTokenizer(pre, " ");
        String args = signatureString.substring(signatureString.indexOf('(')+1, signatureString.indexOf(')'));
        StringTokenizer argsT = new StringTokenizer(args, ", ");

        while (argsT.hasMoreElements()) {
            arguments.add(new MethodSignature.Argument(argsT.nextToken(), argsT.nextToken()));
        }

        MethodSignature signature = new MethodSignature("", arguments);

        if(preT.countTokens() >2 ) {
            signature.setAccessModifier(preT.nextToken());
        }
        signature.setReturnType(preT.nextToken());
        signature.setMethodName(preT.nextToken());

        return signature;
    }
}
