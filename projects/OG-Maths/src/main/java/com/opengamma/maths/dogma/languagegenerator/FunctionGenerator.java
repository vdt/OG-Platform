/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.dogma.engine.language.ArbitraryFunction;
import com.opengamma.maths.dogma.engine.language.InfixOperator;
import com.opengamma.maths.dogma.engine.language.UnaryFunction;
import com.opengamma.maths.dogma.engine.language.VoidUnaryFunction;
import com.opengamma.maths.dogma.languagegenerator.generators.ArbitraryFunctionGenerator;
import com.opengamma.maths.dogma.languagegenerator.generators.InfixOperatorGenerator;
import com.opengamma.maths.dogma.languagegenerator.generators.UnaryFunctionGenerator;
import com.opengamma.maths.dogma.languagegenerator.generators.VoidUnaryFunctionGenerator;

/**
 * 
 */
public class FunctionGenerator {
  private static Logger s_log = LoggerFactory.getLogger(FunctionGenerator.class);
  private static Map<Class<?>, DogmaLangTokenToCodeGenerator> s_generationMap = new HashMap<Class<?>, DogmaLangTokenToCodeGenerator>();

  static {
    s_generationMap.put(UnaryFunction.class, UnaryFunctionGenerator.getInstance());
    s_generationMap.put(InfixOperator.class, InfixOperatorGenerator.getInstance());
    s_generationMap.put(ArbitraryFunction.class, ArbitraryFunctionGenerator.getInstance());
    s_generationMap.put(VoidUnaryFunction.class, VoidUnaryFunctionGenerator.getInstance());
  }

  private FullToken _tok;
  private String _simpleClassName;
  private String _dogmaClassName;

  FunctionGenerator(FullToken tok) {
    _tok = tok;
    _simpleClassName = _tok.getSimpleName();
    _dogmaClassName = "DOGMA" + _tok.getSimpleName();
  }

  public String entryPointGenerator() {
    StringBuffer sbFunctionEntry = new StringBuffer();
    DogmaLangTokenToCodeGenerator g;
    g = s_generationMap.get(_tok.getInterfaceClassType());
    if (g == null) {
      s_log.warn("class " + _tok.getInterfaceClassType() + " has no code generator class available");
    } else {
      sbFunctionEntry.append(g.generateEntryPointsCode(_tok));
    }
    return sbFunctionEntry.toString();
  }

  public String classGenerator() {

    DogmaLangTokenToCodeGenerator g;
    StringBuffer sbprocedural = new StringBuffer();
    StringBuffer sbtablevars = new StringBuffer();
    StringBuffer sbjumptables = new StringBuffer();
    StringBuffer sbmethods = new StringBuffer();
    StringBuffer sbimports = new StringBuffer();

    g = s_generationMap.get(_tok.getInterfaceClassType());
    //    System.out.println("name = " + _tok.getSimpleName() + ". class id =" + _tok.getInterfaceClass().getSimpleName());
    if (g == null) {
      s_log.warn("class " + _tok.getInterfaceClassType() + " has no code generator class available");
    } else {
      sbtablevars.append(g.generateTableCodeVariables(_tok));
      sbjumptables.append(g.generateTableCode(_tok));
      sbmethods.append(g.generateMethodCode(_tok));
    }
    sbimports.append("import " + _tok.getCanonicalName() + ";\n");

    // procedural code gathering

    // add in header
    sbprocedural.append(header());

    // imports
    sbprocedural.append(imports());
    sbprocedural.append(sbimports);

    // add in classname
    sbprocedural.append(classname());

    // add singleton
    sbprocedural.append(singleton());

    // add logger
    sbprocedural.append(logger());

    // add code for verbose start up
    sbprocedural.append(verboseHandler());

    // add dispatch
    sbprocedural.append(chainRunners());

    // add variables that make up the jump tables
    sbprocedural.append(sbtablevars);

    // start static block
    sbprocedural.append(beginStaticBlock());

    // add evaluation default matrix cost
    sbprocedural.append(evalCostMatrix());

    // add operation dictionary
    sbprocedural.append(operationDict());

    // add jump table code
    sbprocedural.append(sbjumptables);

    // close static block
    sbprocedural.append(closeBrace());

    // add method code
    sbprocedural.append(sbmethods);

    // close the class
    sbprocedural.append(closeBrace());

    return sbprocedural.toString();
  }

  private static String header() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("// Autogenerated, do not edit!\n");
    tmp.append("/**\n");
    tmp.append(" * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies\n");
    tmp.append(" *\n");
    tmp.append(" * Please see distribution for license.\n");
    tmp.append(" */\n");
    tmp.append("package com.opengamma.maths.dogma.autogen;\n");
    return tmp.toString();
  }

  private String classname() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("/**\n");
    tmp.append(" * Provides the DOGMA Language\n");
    tmp.append(" */\n");
    tmp.append("public class " + _dogmaClassName + " {\n");
    return tmp.toString();
  }

  private String verboseHandler() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("  // switch for chatty start up\n");
    tmp.append("  private static boolean s_verbose;\n");
    tmp.append("  public " + _dogmaClassName + "(boolean verbose) {\n");
    tmp.append("    s_verbose = verbose;\n");
    tmp.append("  };\n");
    return tmp.toString();
  }

  private String singleton() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("  private static " + _dogmaClassName + " s_instance;\n");
    tmp.append("  " + _dogmaClassName + "() {\n");
    tmp.append("  }\n");
    tmp.append("  public static " + _dogmaClassName + " getInstance() {\n");
    tmp.append("    return s_instance;\n");
    tmp.append("  }\n");
    return tmp.toString();
  }

  public static String imports() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("import com.opengamma.maths.commonapi.numbers.ComplexType;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.language.InfixOperator;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.language.UnaryFunction;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.language.VoidUnaryFunction;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.language.Function;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.InfixOpChain;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.MethodScraperForInfixOperators;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.MethodScraperForUnaryFunctions;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.MethodScraperForVoidUnaryFunctions;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.OperatorDictionaryPopulator;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.OperatorDictionaryPopulatorLibrary;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.RunInfixOpChain;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.RunUnaryFunctionChain;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.RunVoidUnaryFunctionChain;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.VoidUnaryFunctionChain;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.operationstack.UnaryFunctionChain;\n");
    tmp.append("import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.matrixinfo.ConversionCostAdjacencyMatrixStore;\n");
    tmp.append("import com.opengamma.maths.dogma.engine.matrixinfo.MatrixTypeToIndexMap;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexScalar;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexDiagonalMatrix;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGRealScalar;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexMatrix;\n");
    tmp.append("import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;\n");
    tmp.append("import org.slf4j.Logger;\n");
    tmp.append("import org.slf4j.LoggerFactory;\n");
    return tmp.toString();
  }

  private static String chainRunners() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("  private static RunInfixOpChain s_infixOpChainRunner = new RunInfixOpChain();\n");
    tmp.append("  private static RunUnaryFunctionChain s_unaryFunctionChainRunner = new RunUnaryFunctionChain();\n");
    tmp.append("  private static RunVoidUnaryFunctionChain s_voidUnaryFunctionChainRunner = new RunVoidUnaryFunctionChain();\n");
    return tmp.toString();
  }

  private static String operationDict() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("// Build instructions sets\n ");
    //    tmp.append("OperatorDictionaryPopulator<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>> operatorDictInfix = new OperatorDictionaryPopulator");
    //    tmp.append("<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>>();\n");
    //    tmp.append("OperatorDictionaryPopulator<UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>>> operatorDictUnary");
    //    tmp.append(" = new OperatorDictionaryPopulator<UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>>>();\n");
    //    tmp.append("OperatorDictionaryPopulator<VoidUnaryFunction<OGArray<? extends Number>>> operatorDictVoidUnary");
    //    tmp.append(" = new OperatorDictionaryPopulator<VoidUnaryFunction<OGArray<? extends Number>>>();\n");
    tmp.append("OperatorDictionaryPopulator<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>> operatorDictInfix = OperatorDictionaryPopulatorLibrary.getInfixOperatorDictionary();\n"); //CSIGNORE
    tmp.append("OperatorDictionaryPopulator<UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>>> operatorDictUnary = OperatorDictionaryPopulatorLibrary.getUnaryOperatorDictionary();\n"); //CSIGNORE
    tmp.append("OperatorDictionaryPopulator<VoidUnaryFunction<OGArray<? extends Number>>> operatorDictVoidUnary = OperatorDictionaryPopulatorLibrary.getVoidUnaryOperatorDictionary();\n"); 
    return tmp.toString();
  }

  private String logger() {
    return "  private static Logger s_log = LoggerFactory.getLogger(" + _simpleClassName + ".class);\n";
  }

  private static String beginStaticBlock() {
    return "static {\n";
  }

  private static String closeBrace() {
    return "\n}\n";
  }

  private static String evalCostMatrix() {
    StringBuffer tmp = new StringBuffer();
    //    tmp.append("final double[][] DefaultInfixFunctionEvalCosts = new double[][] {//\n");
    //    tmp.append("{1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },//\n");
    //    tmp.append("{0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },//\n");
    //    tmp.append("{0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },//\n");
    //    tmp.append("{0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },//\n");
    //    tmp.append("{0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },//\n");
    //    tmp.append("{0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },//\n");
    //    tmp.append("{0, 0, 0, 0, 0, 0, 25, 0, 50, 0 },//\n");
    //    tmp.append("{0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },//\n");
    //    tmp.append("{0, 0, 0, 0, 0, 0, 50, 0, 100, 200 },//\n");
    //    tmp.append("{0, 0, 0, 0, 0, 0, 0, 0, 0, 200 } };\n");

    tmp.append("final double[][] DefaultInfixFunctionEvalCosts = new double[][] {\n");
    tmp.append("{1.00, 1.00, 1.00, 1.00, 0.00, 1.00, 1.00, 1.00, 1.00, 1.00 },//\n");
    tmp.append("{1.00, 1.00, 0.00, 1.00, 0.00, 0.00, 0.00, 1.00, 0.00, 1.00 },//\n");
    tmp.append("{1.00, 0.00, 1.00, 1.00, 0.00, 0.00, 1.00, 1.00, 1.00, 1.00 },//\n");
    tmp.append("{1.00, 1.00, 1.00, 1.00, 0.00, 0.00, 0.00, 1.00, 0.00, 1.00 },//\n");
    tmp.append("{0.00, 0.00, 0.00, 0.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00 },//\n");
    tmp.append("{1.00, 0.00, 0.00, 0.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00 },//\n");
    tmp.append("{1.00, 0.00, 1.00, 0.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00 },//\n");
    tmp.append("{1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 0.00, 1.00 },//\n");
    tmp.append("{1.00, 0.00, 1.00, 0.00, 1.00, 1.00, 1.00, 0.00, 1.00, 1.00 },//\n");
    tmp.append("{1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00 } };\n");

    tmp.append("OGMatrix defaultInfixFunctionEvalCostsMatrix = new OGMatrix(DefaultInfixFunctionEvalCosts);\n");

    tmp.append("final double[][] DefaultUnaryFunctionEvalCosts = new double[][] {//\n");
    tmp.append("{1 },//\n");
    tmp.append("{1 },//\n");
    tmp.append("{2 },//\n");
    tmp.append("{3 },//\n");
    tmp.append("{3 },//\n");
    tmp.append("{5 },//\n");
    tmp.append("{5 },//\n");
    tmp.append("{5 },//\n");
    tmp.append("{10 },//\n");
    tmp.append("{20 } };\n");
    tmp.append("OGMatrix defaultUnaryFunctionEvalCostsMatrix = new OGMatrix(DefaultUnaryFunctionEvalCosts);\n");
    return tmp.toString();
  }

}
