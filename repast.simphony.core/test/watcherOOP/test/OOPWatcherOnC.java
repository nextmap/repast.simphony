/**
 * 
 */
package watcherOOP.test;

import repast.simphony.engine.watcher.Watch;
import repast.simphony.engine.watcher.WatcherTriggerSchedule;

/**
 * @author Nick Collier
 */
public class OOPWatcherOnC {
  
  private int val = -1;
  private String str = "";
  
  @Watch(watcheeClassName="watcherOOP.test.SimpleWatchee", watcheeFieldNames="val, str", 
      whenToTrigger=WatcherTriggerSchedule.IMMEDIATE)
  public void fire(SimpleWatchee watchee) {
    str = watchee.getStr();
    
  }
  
  @Watch(watcheeClassName="watcherOOP.test.WatcheeChildC", watcheeFieldNames="val, cStr", 
      whenToTrigger=WatcherTriggerSchedule.IMMEDIATE)
  public void fire2(WatcheeChildC watchee) {
    val = watchee.getVal();
  }
  
  public int getVal() {
    return val;
  }
  
  public void reset() {
    val = -1;
    str = "";
  }
  
  public String getString() {
    return str;
  }

}
