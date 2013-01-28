import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;

public class JMXTestObject extends NotificationBroadcasterSupport implements JMXTestObjectMBean {
	private int aInt = 2;
	private double aDouble = 1.23;
	private float aFloat = (float) 4.56;
	private Map<String,Integer> aMap = null;
	private TabularDataSupport aTable = null;

	private long sequenceNumber = 1;

	public JMXTestObject() {
		aMap = new HashMap<String,Integer>();
		aMap.put("key1", 1);
		aMap.put("key2", 2);

		buildATable();
	}
	
	private void buildATable() {
		String[] itemNames = new String[] {"Attribute", "DisplayValue", "Sensor", "Type", "Value"};
		String[] itemDesc = {"Attribute", "DisplayValue", "Sensor", "Type", "Value"};
		OpenType[] rowAttrTypes = {SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.DOUBLE};
		String[] rowIndex = new String[] {"Attribute", "Sensor"};
		
		CompositeType compositeType = null;
		TabularType tabularTable = null;
		try {
			compositeType = new CompositeType(
					"Sample Composite Data",
					"blah, blah",
					itemNames,
					itemDesc,
					rowAttrTypes);
			tabularTable = new TabularType(
					"SampleTabularType",
					"Sample Tabular Type",
					compositeType,
					rowIndex);
		} catch (OpenDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		aTable = new TabularDataSupport(tabularTable);
		
		try {
			CompositeDataSupport cd = new CompositeDataSupport(compositeType, itemNames, new Object[] {
					"AverageResponseTime", "227.77", "HttpInputSensor", "DOUBLE", 227.774});
			aTable.put(cd);
			
			CompositeDataSupport cd1 = new CompositeDataSupport(compositeType, itemNames, new Object[] {
					"NumberOfCalls", "123.123", "HttpInputSensor", "LONG", 123.123});
			aTable.put(cd1);
		} catch (OpenDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update() {
		aInt += 1;
		aDouble += 0.03;
		aFloat += 0.02;
		
		Notification n = new Notification("update", this, sequenceNumber++, System.currentTimeMillis(), "updated MBean values");
		sendNotification(n);
	}
	
	public int getAInt() {
		return aInt;
	}

	public synchronized void setAInt(int aInt) {
		int oldInt = this.aInt;
		this.aInt = aInt;
		
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(), "aInt changed", "aInt", "int", oldInt, this.aInt);
		sendNotification(n);
	}
	
	public double getADouble() {
		return aDouble;
	}
	
	public float getAFloat() {
		return aFloat;
	}
	
	public String getAString() {
		return "NONUM";
	}

	public String getANumericString() {
		return "23.56";
	}
	
	public List<String> getListOfStrings() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("some text");
		list.add("67.89");
		list.add("more text");
		list.add("98.76");
		return list;
	}
	
	public List<String> callListOfStrings(String param) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("some text");
		list.add("67.89");
		list.add("more text");
		list.add("98.76");
		return list;		
	}
	
	public Map<String,Integer> getAMap() {
		return aMap;
	}
	
	public Map<String,Integer> callAMap() {
		return aMap;
	}
	
	public TabularDataSupport getATable() {
		return aTable;
	}
}
