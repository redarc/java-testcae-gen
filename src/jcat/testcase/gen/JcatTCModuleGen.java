package jcat.testcase.gen;

import org.testng.annotations.*;

public class JcatTCModuleGen
{
  protected static String nl;
  public static synchronized JcatTCModuleGen create(String lineSeparator)
  {
    nl = lineSeparator;
    JcatTCModuleGen result = new JcatTCModuleGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "package ";
  protected final String TEXT_3 = NL + NL + "public class ";
  protected final String TEXT_4 = " extends TestBase" + NL + "{" + NL + "\tpublic ";
  protected final String TEXT_5 = "(){" + NL + "\t\tsetTestInfo(\"Constructor of ";
  protected final String TEXT_6 = "\");" + NL + "\t}" + NL + "\t" + NL + "    /**" + NL + "     * Runs a test that uses the {@link CreateMoTestModule} test module. " + NL + "     * Same or different test modules can be used in various test methods." + NL + "     * " + NL + "     * @param  moLdn" + NL + "     *        " + NL + "     * @throws Exception" + NL + "     */" + NL + "\t@Test" + NL + "\t@Parameters({\"moldn\"})" + NL + "\tpublic void test1(String moldn){" + NL + "\t    setTestcase(\"createDeleteMoTestCase\", \"createDeleteMoTestCase\");" + NL + "        CreateDeleteMoTestModule testModule = CreateDeleteMoTestModule.newBuilder()" + NL + "                .setMoLdn(moLdn)" + NL + "                .build();" + NL + "        testModule.execute();" + NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_7 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	JcatTCGenArgs args = (JcatTCGenArgs)argument;
    String pkgName = args.getPkgName();
    String className = args.getClassName(); 

    stringBuffer.append(TEXT_2);
    stringBuffer.append(pkgName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
