/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.MyuserDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Tom
 */
@Remote
public interface MyuserFacadeRemote {

    Boolean createRecord(MyuserDTO myuserDTO);

    MyuserDTO getRecord(String userId);

    Boolean updateRecord(MyuserDTO myuserDTO);

    Boolean deleteRecord(String userId);

    ArrayList<MyuserDTO> getRecordsByAddress(String address);
    
}
