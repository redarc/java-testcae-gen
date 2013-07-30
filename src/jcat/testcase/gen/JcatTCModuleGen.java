package jcat.testcase.gen;

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
  protected final String TEXT_3 = NL + NL + "/**" + NL + " *   " + NL + " * @name ";
  protected final String TEXT_4 = NL + " *       " + NL + " * @author " + NL + " *       " + NL + " * @created " + NL + " *       " + NL + " * @description" + NL + " *       " + NL + " * @revision " + NL + " *  " + NL + " */" + NL + "" + NL + "public class ";
  protected final String TEXT_5 = " extends GratHelper" + NL + "{" + NL + "    // builder variables" + NL + "    private String param;" + NL + "" + NL + "" + NL + "" + NL + "    /**" + NL + "     * The constructor. It is used by the build method in the builder class. To get" + NL + "     * an instance of this class, use the builder." + NL + "     * " + NL + "     * @param builder An instance of the builder of this class" + NL + "     */" + NL + "    protected ";
  protected final String TEXT_6 = "(";
  protected final String TEXT_7 = "Builder builder) " + NL + "    {       " + NL + "        param=builder.param;        " + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * Gets a new instance of the builder of this class {@link ";
  protected final String TEXT_8 = "Builder}." + NL + "     * " + NL + "     * @return The builder." + NL + "     */" + NL + "    public static ";
  protected final String TEXT_9 = "Builder newBuilder() " + NL + "    {" + NL + "        return new ";
  protected final String TEXT_10 = "Builder();" + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * This method is the test method. It creates the given MO (Managed Object)." + NL + "     *   " + NL + "     */" + NL + "    public void execute() throws ConnectionException" + NL + "    {" + NL + "" + NL + "    }" + NL + "}" + NL + NL + NL;
  protected final String TEXT_11 = NL;

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
    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
