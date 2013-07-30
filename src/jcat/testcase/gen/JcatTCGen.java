package jcat.testcase.gen;

public class JcatTCGen
{
  protected static String nl;
  public static synchronized JcatTCGen create(String lineSeparator)
  {
    nl = lineSeparator;
    JcatTCGen result = new JcatTCGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "package ";
  protected final String TEXT_3 = NL + NL + "import org.testng.annotations.*;" + NL + "import se.ericsson.jcat.fw.annotations.*;" + NL + "import com.ericsson.tac.jcat.TestBase;" + NL + "" + NL + "/**" + NL + " * @id  TC_";
  protected final String TEXT_4 = NL + " *       <p>" + NL + " *       The @id tag is used to give your script a machine readable unique identity. " + NL + " *       To allow the database-tracking of the script it is important that the id is unique!" + NL + " *   " + NL + " * @name An example test showing how to use test modules and builders." + NL + " *       <p>" + NL + " *       The @name tag is used to give a more user friendly name to the script. " + NL + " *       Use a short sentence or a set of keywords to name the script." + NL + " *       " + NL + " * @author " + NL + " *       <p>" + NL + " *       Your signum " + NL + " *       " + NL + " * @created " + NL + " *       <p>" + NL + " *       The year-month-day " + NL + " *       " + NL + " * @description  This test uses the {@link ";
  protected final String TEXT_5 = "TestModuleBuilder} to configure and build a" + NL + " *       {@link ";
  protected final String TEXT_6 = "TestModule}. Then the test modules execute(test) method is executed." + NL + " *        If more information needed of passing parameters to before/afterSuite, before/afterTest" + NL + " *       from XML file please refer to {@link com.ericsson.lte.templates.helloworld} " + NL + " *       <p>" + NL + " *       A description of what the script does. More detailed than the @name tag." + NL + " *       " + NL + " * @revision " + NL + " *       <p>" + NL + " *       The revision tag should include your signum, the date and a description of the revision" + NL + " *  " + NL + " * Optional tags:      " + NL + " * @deviation " + NL + " *       <p>" + NL + " *       If the ID of your script is shared by an existing test case and your test script does not follow the existing test case exactly, note all deviations with deviation tags. One tag per deviation." + NL + " *       Example: @deviation The synchronization time limit will be set to 15 minutes due to lack of framework support for checking the synchronization constraints" + NL + " * " + NL + " * @precondition " + NL + " *       <p>" + NL + " *       The @precondition tags will be used to allow both humans and computer systems to decide whether a script can be run on a certain test configuration or not." + NL + " * " + NL + " * @tags Template,Example" + NL + " *       <p>" + NL + " *       Allows freeform tagging of scripts for any suitable reason. Test scripts can then be sorted and searched for by the tags they use" + NL + " * " + NL + " * @trace " + NL + " *       <p>" + NL + " *       The @trace tag allows the test scripter to suggest some traces to be enabled when re-running a failed test, to allow easier troubleshooting" + NL + " * " + NL + " * @nodetype" + NL + " *       <p>" + NL + " *       Defines which nodeType this test is meant to run towards for example is it a multistandard node, LTE only" + NL + " * " + NL + " * @softwareVersion " + NL + " *       <p>" + NL + " *        Defines which software version this is supposed to work for." + NL + " */" + NL + "" + NL + "public class ";
  protected final String TEXT_7 = " extends TestBase" + NL + "{" + NL + "\tpublic ";
  protected final String TEXT_8 = "(){" + NL + "\t\tsetTestInfo(\"Constructor of ";
  protected final String TEXT_9 = "\");" + NL + "\t}" + NL + "\t" + NL + "" + NL + "    /**" + NL + "     * Optional method for doing something once, before the first test execution.    " + NL + "     * " + NL + "     * @param context" + NL + "     * @throws Exception" + NL + "     */" + NL + "    @BeforeClass" + NL + "    public void beforeClass() throws Exception {" + NL + "        setTestcase(\"beforeClass\", \"beforeClass\");" + NL + "        setTestInfo(\"****beforeClass\"); " + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * Optional method for doing something once, after the last test execution." + NL + "     * " + NL + "     * AfterClass would skip execution if any dependent methods fails.  " + NL + "     * If some actions are required to execute in AfterClass, e.g., clean up, restore system, " + NL + "     * then set @AfterClass(alwaysRun = true)." + NL + "     * " + NL + "     * @throws Exception" + NL + "     */" + NL + "    @AfterClass" + NL + "    public void afterClass() throws Exception {" + NL + "        setTestcase(\"afterClass\", \"afterClass\");" + NL + "        setTestInfo(\"****afterClass\");" + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * Optional method for doing something before each test execution." + NL + "     * " + NL + "     * @throws Exception" + NL + "     */" + NL + "    @Setup" + NL + "    public void setup() throws Exception {" + NL + "        setTestInfo(\"****setup\");" + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * Optional method for doing something after each test execution." + NL + "     * " + NL + "     * @throws Exception" + NL + "     */" + NL + "    @Teardown" + NL + "    public void teardown() throws Exception {" + NL + "        setTestInfo(\"****teardown\");" + NL + "    }" + NL + "" + NL + "" + NL + "    /**" + NL + "     * Runs a test that uses the {@link ";
  protected final String TEXT_10 = "TestModule} test module. " + NL + "     * Same or different test modules can be used in various test methods." + NL + "     * " + NL + "     * @param  moLdn" + NL + "     *        " + NL + "     * @throws Exception" + NL + "     */" + NL + "    @Test" + NL + "    @Parameters({\"param\"})" + NL + "    public void test(String param)" + NL + "            throws Exception {" + NL + "        setTestcase(\"createMoTestCase\", \"createMoTestCase\");";
  protected final String TEXT_11 = NL + "        ";
  protected final String TEXT_12 = "TestModule testModule = ";
  protected final String TEXT_13 = "TestModule.newBuilder()" + NL + "                .setParam(param)" + NL + "                .build();" + NL + "        testModule.execute();" + NL + "    }" + NL + "" + NL + "}";

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
    stringBuffer.append(className);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
}
