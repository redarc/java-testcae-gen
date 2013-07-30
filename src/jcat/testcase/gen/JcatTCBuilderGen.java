package jcat.testcase.gen;

public class JcatTCBuilderGen
{
  protected static String nl;
  public static synchronized JcatTCBuilderGen create(String lineSeparator)
  {
    nl = lineSeparator;
    JcatTCBuilderGen result = new JcatTCBuilderGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "" + NL + "/**" + NL + "*   " + NL + "* @name ";
  protected final String TEXT_4 = "TestModuleBuilder" + NL + "*       " + NL + "* @author " + NL + "*       " + NL + "* @created " + NL + "*       " + NL + "* @description   A builder class for {@link ";
  protected final String TEXT_5 = "TestModule}. The setter methods can" + NL + "*               optionally be used to configure the test module. Call build to get an instance of" + NL + "*               {@link ";
  protected final String TEXT_6 = "TestModule}." + NL + "*       " + NL + "* @revision  first version " + NL + "*  " + NL + "*/" + NL + "" + NL + "public class ";
  protected final String TEXT_7 = NL + "{" + NL + "    protected String param;" + NL + "" + NL + "    /**" + NL + "     * The constructor for this builder, should only be used by {@link ";
  protected final String TEXT_8 = "TestModule} " + NL + "     * " + NL + "     */" + NL + "    protected ";
  protected final String TEXT_9 = "TestModuleBuilder()" + NL + "    {" + NL + "    }" + NL + "    " + NL + "    /**" + NL + "     * Sets the attribute moLdn (Mandatory attribute)" + NL + "     * " + NL + "     * @param param" + NL + "     * @return This builder" + NL + "     */" + NL + "    public ";
  protected final String TEXT_10 = "setParam(String param)" + NL + "    {" + NL + "        this.param = param;" + NL + "        return this;" + NL + "    }" + NL + "    " + NL + "    /**" + NL + "     * Verifies that the mandatory attributes are set." + NL + "     * Builds a ";
  protected final String TEXT_11 = "TestModule using the values set by the setter methods." + NL + "     *    " + NL + "     * @return a ";
  protected final String TEXT_12 = "TestModule" + NL + "     */" + NL + "    public ";
  protected final String TEXT_13 = "TestModule build() " + NL + "    {" + NL + "        // Verify that mandatory attributes has a value set." + NL + "" + NL + "        if(param == null)" + NL + "        {" + NL + "            String msg = \"You must set a value to the mandatory attribute \\\"param\\\".\"; " + NL + "            throw new NullPointerException(msg);" + NL + "        }" + NL + "        " + NL + "        return new ";
  protected final String TEXT_14 = "TestModule(this);" + NL + "    }" + NL + "}";
  protected final String TEXT_15 = NL;

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
    stringBuffer.append(className);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(TEXT_15);
    return stringBuffer.toString();
  }
}
