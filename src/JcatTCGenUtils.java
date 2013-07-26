import jcattestcaegen.wizards.JcatTCGen;
import jcattestcaegen.wizards.JcatTCGenArgs;


public class JcatTCGenUtils {
	public static void main(String[] args){
		JcatTCGenArgs jargs = new JcatTCGenArgs();
		jargs.setClassName("WP2464");
		jargs.setPkgName("com.redarc");
		JcatTCGen gen = new JcatTCGen();
		System.out.println(gen.generate(jargs));
	}
}
