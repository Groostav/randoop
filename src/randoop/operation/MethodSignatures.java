package randoop.operation;

import java.lang.reflect.Method;

import randoop.types.TypeNames;

/**
 * MethodSignatures provides static methods to write as well as recognize and extract methods 
 * from string representations of their signature.
 */
public class MethodSignatures {

  /**
   * getMethodForSignature parses a method signature as produced by getSignature
   * and returns the corresponding reflective {@link Method} object. 
   * 
   * @param signature a string representing a method signature.
   * @return reflective {@link Method} object corresponding to signature.
   * @throws OperationParseException
   */
  public static Method getMethodForSignature(String signature) throws OperationParseException {
    if (signature == null) {
      throw new IllegalArgumentException("signature may not be null");
    }
    
    int openPar = signature.indexOf('(');
    int closePar = signature.indexOf(')');
    // Verify only one open/close paren, and close paren is last char.
    assert openPar == signature.lastIndexOf('(') : signature;
    assert closePar == signature.lastIndexOf(')') : signature;
    assert closePar == signature.length() - 1 : signature;
    String clsAndMethod = signature.substring(0, openPar);
    int lastDot = clsAndMethod.lastIndexOf('.');
    // There should be at least one dot, separating class/method name.
    assert lastDot >= 0;
    String clsName = clsAndMethod.substring(0, lastDot);
    String methodName = clsAndMethod.substring(lastDot + 1);
    String argsOneStr = signature.substring(openPar + 1, closePar);
    
    // Extract parameter types.
    Class<?>[] argTypes = ArgumentParser.recognizeArguments(argsOneStr);
    
    Class<?> cls;
    try {
      cls = TypeNames.recognizeType(clsName);
      return cls.getDeclaredMethod(methodName, argTypes);
    } catch (ClassNotFoundException e1) {
      throw new Error(e1);
    } catch (NoSuchMethodException e) {
      throw new Error(e);
    } catch (SecurityException e) {
      throw new Error(e);
    }
  }

  /**
   * getSignature generates a string representation of the signature of the method.
   * 
   * @return string representing the method signature.
   */
  public static String getSignature(Method method) {
    StringBuilder sb = new StringBuilder();
    sb.append(method.getDeclaringClass().getName() + ".");
    sb.append(method.getName() + "(");
    Class<?>[] params = method.getParameterTypes();
    for (int j = 0; j < params.length; j++) {
      sb.append(params[j].getName());
      if (j < (params.length - 1))
        sb.append(",");
    }
    sb.append(")");
    return sb.toString();
  }
}
