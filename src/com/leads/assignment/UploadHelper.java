package com.leads.assignment;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@ManagedBean
@SessionScoped
public class UploadHelper {
	private EmployeeDbUtil employeeDbUtil;
    List<Employee> employees = new ArrayList<Employee>();
	
	public UploadHelper() throws Exception {
		employees = new ArrayList<>();
		employeeDbUtil = EmployeeDbUtil.getInstance();
	}
	
	public String processUpload() throws Exception {
		 //Get Document Builder
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
		Map<String, Object> requestMap = externalContext.getRequestMap();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Load the input XML document, parse it and return an instance of the
        // Document class.
        File file = new File("/xmlfile/employee.xml");
        
        Document document = builder.parse(file);
   
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                 Element elem = (Element) node;
                 String takenId = node.getAttributes().getNamedItem("id").getNodeValue();
                 // Get the value of all sub-elements.
                 String firstname = elem.getElementsByTagName("firstname")
                                     .item(0).getChildNodes().item(0).getNodeValue();
                 System.out.println(firstname);

                 String lastname = elem.getElementsByTagName("lastname").item(0)
                                     .getChildNodes().item(0).getNodeValue();
                 
                 String title = elem.getElementsByTagName("title").item(0)
                         .getChildNodes().item(0).getNodeValue();
                 String division = elem.getElementsByTagName("division").item(0)
                         .getChildNodes().item(0).getNodeValue();
                 String building = elem.getElementsByTagName("building").item(0)
                         .getChildNodes().item(0).getNodeValue();
                 String room = elem.getElementsByTagName("room").item(0)
                         .getChildNodes().item(0).getNodeValue();
                 
                 employees.add(new Employee(takenId, firstname, lastname,title, division, building, room));
             
            }
       }
    
		employeeDbUtil.insertAllXmlDataToDatabase(employees);
		requestMap.put("successMessage", "Data inserted");	
		return "add-student-form.xhtml";

	
	}

	

}