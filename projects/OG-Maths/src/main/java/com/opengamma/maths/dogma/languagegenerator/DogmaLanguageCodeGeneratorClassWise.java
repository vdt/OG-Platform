/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates the multi-class lazy initialisation representation of the DOGMA language code  
 */
public class DogmaLanguageCodeGeneratorClassWise {
  private static Logger s_log = LoggerFactory.getLogger(DogmaLanguageCodeGeneratorClassWise.class);

  private static DogmaLanguageMethodParser s_dogmaLanguageMethodParser = DogmaLanguageMethodParser.getInstance();

  private static String s_outpathAutogen;
  private static String s_outpath;

  DogmaLanguageCodeGeneratorClassWise(String outpath, String autogenPostfix) {
    s_outpathAutogen = new String(outpath + File.separatorChar + autogenPostfix);
    s_outpath = new String(outpath);
  }

  public static void generateCode() {
    List<FullToken> fullTokens = s_dogmaLanguageMethodParser.getTokens();
    String classCode = null;
    StringBuffer entryPointCode = new StringBuffer();

    File outfile = null;
    FileWriter writer;
    BufferedWriter bufferedWrite;
    FunctionGenerator f;
    // generate code from tokens
    for (FullToken tok : fullTokens) {
      s_log.debug("Creating code for: " + tok.getSimpleName());
      f = new FunctionGenerator(tok);
      classCode = f.classGenerator();
      entryPointCode.append(f.entryPointGenerator());
      outfile = new File(s_outpathAutogen + File.separatorChar + "DOGMA" + tok.getSimpleName() + ".java");
      // write to file
      try {
        outfile.createNewFile();
        writer = new FileWriter(outfile);
        bufferedWrite = new BufferedWriter(writer);
        bufferedWrite.write(classCode);
        bufferedWrite.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    s_log.debug("Function Count Total: " + fullTokens.size());

    StringBuffer entryPointFull = new StringBuffer();
    entryPointFull.append(entryPointHeader());
    entryPointFull.append(entryPointImports());
    entryPointFull.append(entryPointClassDecl());
    entryPointFull.append(entryPointCode);
    entryPointFull.append(entryPointFooter());
    // write out the language file with the entry points
    try {
      outfile.createNewFile();
      writer = new FileWriter(s_outpath + File.separatorChar + "DOGMA.java");
      bufferedWrite = new BufferedWriter(writer);
      bufferedWrite.write(entryPointFull.toString());
      bufferedWrite.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String entryPointHeader() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("// Autogenerated, do not edit! //CSIGNORE\n");
    tmp.append("/**\n");
    tmp.append(" * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies\n");
    tmp.append(" *\n");
    tmp.append(" * Please see distribution for license.\n");
    tmp.append(" */\n");
    tmp.append("package com.opengamma.maths.dogma;\n");
    return tmp.toString();
  }

  private static String entryPointImports() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("import com.opengamma.maths.commonapi.numbers.ComplexType;\n");
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
    tmp.append("import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;\n");
    tmp.append("import org.slf4j.Logger;\n");
    tmp.append("import org.slf4j.LoggerFactory;\n");
    return tmp.toString();
  }

  private static String entryPointClassDecl() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("/**\n");
    tmp.append(" * Suppression against unused code, typically imports, this is due to autogeneration and it being easier to include all at little extra cost.\n");
    tmp.append(" */\n");
    tmp.append("@SuppressWarnings(\"unused\")\n");
    tmp.append("/**\n");
    tmp.append(" * Provides the DOGMA Language\n");
    tmp.append(" */\n");
    tmp.append("public class DOGMA {\n");
    tmp.append("//CSOFF \n");    
    return tmp.toString();
  }

  private static String entryPointFooter() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("}\n\n");
    return tmp.toString();
  }

  @SuppressWarnings("unused")
  private static String dogmastart() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("if(s_verbose){\n");
    tmp.append("  s_log.info(\"Welcome to DOGMA\");");
    tmp.append("  s_log.info(\"Building instructions...\");");
    tmp.append("}\n");
    return tmp.toString();
  }

  @SuppressWarnings("unused")
  private static String dogmafinished() {
    StringBuffer tmp = new StringBuffer();
    tmp.append("if(s_verbose){\n");
    tmp.append("  s_log.info(\"DOGMA built.\");");
    tmp.append("}\n");
    return tmp.toString();
  }

}

