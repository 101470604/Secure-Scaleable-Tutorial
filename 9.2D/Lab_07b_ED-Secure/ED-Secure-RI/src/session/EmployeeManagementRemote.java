/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Remote;
import entity.AdminEmpDetailsDTO;
import entity.EmpDetailsDTO;
import entity.EmpUpdateDTO;

/**
 *
 * @author elau
 */
@Remote
public interface EmployeeManagementRemote {

    boolean hasEmployee(String empid);
    
    boolean addEmployee(AdminEmpDetailsDTO empDTO);

    boolean updateEmpolyeeDetails(AdminEmpDetailsDTO empDTO);

    boolean updateEmployeePassword(String empid, String newPassword);

    AdminEmpDetailsDTO getEmployeeDetails(String empid);

    boolean deleteEmployee(String empid);
    
    boolean removeEmployee(String empid);

    EmpDetailsDTO employeeLogin(java.lang.String empId, java.lang.String password);

    String updateDetails(entity.EmpUpdateDTO myEmployee);
}
