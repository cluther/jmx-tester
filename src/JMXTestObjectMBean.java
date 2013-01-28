import java.util.List;
import java.util.Map;

import javax.management.openmbean.TabularDataSupport;

public interface JMXTestObjectMBean {
	int getAInt();
	double getADouble();
	float getAFloat();
	String getAString();
	String getANumericString();
	List<String> getListOfStrings();
	List<String> callListOfStrings(String param);
	Map<String,Integer> getAMap();
	Map<String,Integer> callAMap();
	public TabularDataSupport getATable();
}
