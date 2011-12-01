package repast.simphony.pajek;

import org.apache.commons.lang.SystemUtils;
import repast.simphony.data.analysis.AnalysisPluginRunner;

/**
 * A wizard for executing Pajek on a file outputter's output.
 *
 * @author Michael J. North
 * @author Eric Tatara
 * @author Jerry Vos
 */

public class RunPajekModel extends AnalysisPluginRunner {

  private static String path;

  static {
    if (SystemUtils.IS_OS_WINDOWS)
    	path = "C:\\pajek\\Pajek\\";
    else
    	path = "";
  }

  public RunPajekModel() {
    super("Pajek", path, "license.txt", new PajekWizard());
  }
}
